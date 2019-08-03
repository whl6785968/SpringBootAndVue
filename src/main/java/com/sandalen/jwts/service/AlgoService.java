package com.sandalen.jwts.service;
import com.sandalen.jwts.dao.AlgoMapper;
import com.sandalen.jwts.entity.DataPoint;
import com.sandalen.jwts.scala.IsoForestByModel;
import com.sandalen.jwts.scala.IsoForestByModel$;
import com.sandalen.jwts.scala.RandomForestModelForIris;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<DataPoint> getErrDataPoint(){
        List<DataPoint> errDataPoint = algoMapper.getErrDataPoint();
        return errDataPoint;
    }

    public double PredictLabelByRF(String[] args){
//        new RandomForestModelForIris().main(args);
        double accurate = new RandomForestModelForIris().main(args);
        return accurate;

    }
}
