package cl.mingeso.Resumenes.Services;

import cl.mingeso.Resumenes.Models.EntradaModel;
import cl.mingeso.Resumenes.Models.SalidaModel;
import cl.mingeso.Resumenes.Repositories.ResumenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ResumenService {

    @Autowired
    private ResumenRepository resumenRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    public String getEntradasByDates(String inicio, String fin){
        String entradas = restTemplate.getForObject("http://entradas/entradas/" + "dates/" + inicio + "/" + fin, String.class);
//        System.out.println(entradas);
        return entradas;
    }

    public String getSalidasByDates(String inicio, String fin){
        String salidas = restTemplate.getForObject("http://salidas/salidas/" + "dates/" + inicio + "/" + fin, String.class);
//        System.out.println(salidas.toString());
        return salidas;
    }

    public List<Integer> calcularSaldos(List<EntradaModel> entradas, List<SalidaModel> salidas) throws ParseException {
        List<Integer> saldos = new ArrayList<>();
        Date fechaEntrada;
        Date fechaSalida;

        System.out.println("entradas: " + entradas.toString() + " salidas: " + salidas);

        int iEntrada = 0, iSalida = 0, saldo = 0;

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        while(iEntrada + iSalida < entradas.size() + salidas.size()){
            if (iEntrada < entradas.size() && iSalida < salidas.size()) {
                fechaEntrada = formato.parse(entradas.get(iEntrada).getFecha());
                fechaSalida = formato.parse(salidas.get(iSalida).getFecha());
                if (fechaEntrada.before(fechaSalida)) {
                    saldo += entradas.get(iEntrada).getMonto();
                    iEntrada += 1;
                } else {
                    saldo -= salidas.get(iSalida).getNumero();
                    iSalida += 1;
                }
            }
            else if (iEntrada >= entradas.size()){
                saldo -= salidas.get(iSalida).getNumero();
                iSalida += 1;
            }
            else if (iSalida >= salidas.size()){
                saldo += entradas.get(iEntrada).getMonto();
                iEntrada += 1;
            }

            saldos.add(saldo);
        }
        return saldos;
    }

    public List<Object> []getList(String inicio, String fin) throws ParseException {
        List<Object> objetos[] = new List[2];
        objetos[0] = new ArrayList<>();

        Date fechaEntrada;
        Date fechaSalida;

        String strEntradas = this.getEntradasByDates(inicio, fin);
        String strSalidas = this.getSalidasByDates(inicio, fin);

        List<EntradaModel> entradas = new ArrayList<>();
        List<SalidaModel> salidas = new ArrayList<>();

        try{
            List<LinkedHashMap<String, Object>> linkedHashMapListEntradas = objectMapper.readValue(strEntradas, List.class);

            for(LinkedHashMap<String,Object> linkedHashMap : linkedHashMapListEntradas){
                String fecha = (String) linkedHashMap.get("fecha");
                Integer recibo = (Integer) linkedHashMap.get("recibo");
                Integer monto = (Integer) linkedHashMap.get("monto");
                EntradaModel entradaModel = new EntradaModel(fecha, recibo, monto);
                entradas.add(entradaModel);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        try {
            List<LinkedHashMap<String, Object>> linkedHashMapSalidas = objectMapper.readValue(strSalidas, List.class);

            for (LinkedHashMap<String, Object> linkedHashMap: linkedHashMapSalidas){
                String fecha = (String) linkedHashMap.get("fecha");
                String tipo = (String) linkedHashMap.get("tipo");
                Integer numero = (Integer) linkedHashMap.get("numero");
                String motivo = (String) linkedHashMap.get("motivo");
                SalidaModel salidaModel = new SalidaModel(fecha, tipo, numero, motivo);
                salidas.add(salidaModel);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        List<Integer> saldos = this.calcularSaldos(entradas, salidas);
        System.out.println(saldos.toString());
        objetos[1] = new ArrayList<>(saldos);

        int iEntrada = 0, iSalida = 0;

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        while(iEntrada + iSalida < entradas.size() + salidas.size()){
            if (iEntrada < entradas.size() && iSalida < salidas.size()){
                fechaEntrada = formato.parse(entradas.get(iEntrada).getFecha().toString());
                fechaSalida = formato.parse(salidas.get(iSalida).getFecha().toString());
                if (fechaEntrada.before(fechaSalida)) {
                    objetos[0].add(entradas.get(iEntrada));
                    iEntrada += 1;
                } else {
                    objetos[0].add(salidas.get(iSalida));
                    iSalida += 1;
                }
            }
            else if (iEntrada >= entradas.size()){
                objetos[0].add(salidas.get(iSalida));
                iSalida += 1;
            }else if(iSalida >= salidas.size()){
                objetos[0].add(entradas.get(iSalida));
                iEntrada += 1;
            }
        }

        return objetos;
    }
}