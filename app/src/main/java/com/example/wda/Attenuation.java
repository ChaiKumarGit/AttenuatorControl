package com.example.wda;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class Attenuation extends AppCompatActivity {
    protected EditText[] etPortNoList,etPortValueList;

    int[] etPortNoListRID = new int[] {

    };

    int[] etPortValueListRID = new int[] {

    };

    int[][] btPlusRID   =   new int[][] {

    };

    int[][] btMinusRID  =   new int[][]{

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attenuation);
        String allPV = sendCmd("ATT");
        setInitialValues(allPV);
    }

    public void attenuate(View v){
        Button bt = null;
        EditText tmpET=null;
        EditText tmpPortET = null;
        boolean viewFound = false;

        for(int row=0; row< btPlusRID.length; row++)
            for(int column=0; column<btPlusRID[row].length; column++){
                if(v.getId() == btPlusRID[row][column]){
                    bt = (Button) findViewById(btPlusRID[row][column]);
                    tmpET = (EditText) findViewById(etPortValueListRID[row]);
                    tmpPortET = (EditText) findViewById(etPortNoListRID[row]);
                    viewFound = true;
                }
            }

        if(!viewFound){
            for(int row=0; row< btMinusRID.length; row++)
                for(int column=0; column<btMinusRID[row].length; column++){
                    if(v.getId() == btMinusRID[row][column]){
                        bt = (Button) findViewById(btMinusRID[row][column]);
                        tmpET = (EditText) findViewById(etPortValueListRID[row]);
                        tmpPortET = (EditText) findViewById(etPortNoListRID[row]);
                        viewFound = true;
                    }
                }
        }

        if(viewFound && bt != null && tmpET != null && tmpPortET != null){
            int toSetVal = Integer.parseInt(tmpET.getText().toString())+Integer.parseInt(bt.getText().toString());
            if(0<=toSetVal && toSetVal<=110){
                tmpET.setText(toSetVal+"");
                String attCmd = "ATT "+tmpPortET.getText()+" "+toSetVal;
                MainActivity.telnet.sendCommand(attCmd);
            }
        }

    }

    public String sendCmd(String cmd){
        String response="";

        try {
            response = MainActivity.telnet.getResponse(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

}
