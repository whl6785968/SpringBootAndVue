package com.sandalen.jwts.service;
import com.sandalen.jwts.dao.AlgoMapper;
import com.sandalen.jwts.scala.IsoForestByModel;
import com.sandalen.jwts.scala.IsoForestByModel$;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlgoService {
    @Autowired
    private AlgoMapper algoMapper;

    public void calcuErrorDataPoint(String[] args){
        new IsoForestByModel().main(args);
    }

    public int staticsErrorDataPoint(){
        int count = algoMapper.staticsErrorDataPoint();
        return count;
    }
}
