package com.example.trabajofinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class List_of_Records extends Activity {
	Bundle bundle;
	int today;
	String last, compare,compare2;
	int CFILES;
	ListView data1, data2;
	Button logout2,Bgraph,back;
	StringBuilder result =  new StringBuilder();
	StringBuilder result2 =  new StringBuilder();
	ArrayList<String> myArray1;
	ArrayList<String> myArray2;
	String Data, graph1="", graph2="", Fecha;
	TextView TV1,TV2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_of__records);
		
		logout2 = (Button) findViewById(R.id.BLlogout);
		 myArray1 = new ArrayList<String>();
		 myArray2=new ArrayList<String>();

		 back = (Button) findViewById(R.id.BLBack);

		 Bgraph = (Button) findViewById(R.id.BLGraph);

		data1= (ListView) findViewById(R.id.LlistView1);
		data2= (ListView) findViewById(R.id.LlistView2);
		
		TV1= (TextView) findViewById(R.id.Ldata1);
		TV2= (TextView) findViewById(R.id.Ldata2);


		bundle = getIntent().getExtras();
		if(bundle != null){
			 today = bundle.getInt("Today");
			 compare = bundle.getString("compare");
			 compare2 = bundle.getString("compare2");
			 CFILES = bundle.getInt("CountFiles");
			 Data = bundle.getString("DATA");
			 Fecha = bundle.getString("FECHA");

			// last = bundle.getString("last");
		}
		
		//check if compared or today.
		if(today != 0){
			ReadData("data_"+today);//add last for 3r
		    data2.setVisibility(View.INVISIBLE);
		    TV1.setText("Today");
		    TV2.setVisibility(View.INVISIBLE);
		}else{
		    data2.setVisibility(View.VISIBLE);
			ReadData(compare);//add last for 3r
			ReadData2(compare2);//add last for 3r
			TV1.setText(compare);
		    TV2.setText(compare2);
			
		}
		
		// add adapter to listView
		ArrayAdapter<String> myAdapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,myArray1);
		data1.setAdapter(myAdapter);
		
		// add adapter to listView2
			ArrayAdapter<String> myAdapter2 =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,myArray2);
			data2.setAdapter(myAdapter2);
		
		// logout....
		logout2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	myArray2.clear();
            	myArray1.clear();
            		Intent intent1 = new Intent (List_of_Records.this, BluetoothChat.class);
          			 startActivity(intent1);
            	
            }
        });
		
		// go to graph intent.
				Bgraph.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) {   	
		            		
		            		Intent graph = new Intent (List_of_Records.this, Graph_Record.class);
		            		graph.putExtra("CountFiles", CFILES	);

		            		if (today != 0){
		            			graph.putExtra("Today", today);
			            		graph.putExtra("Compare", "");
			            		graph.putExtra("Compare2", "");
			            		graph.putExtra("DATA", Data);
			            		graph.putExtra("FECHA", Fecha);

		            		}
		            		else{
		            			graph.putExtra("Today", 0);
		            			graph.putExtra("Compare", compare);
		            			graph.putExtra("Compare2", compare2);
		            			graph.putExtra("DATA", Data);
		                		graph.putExtra("FECHA", Fecha);

		            		}
		            		
		       //     		graph.putExtra("Today", today);

		            	//	graph.putExtra("Graph1", graph1);
		            	//	graph.putExtra("Graph2", graph2);
		          			 startActivity(graph);            	
		            	
		            }
		        });
				
		// go back
				back.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) { 
		            	myArray2.clear();
		            	myArray1.clear();
		            		Intent back = new Intent (List_of_Records.this, Select_records.class);
		            		back.putExtra("CountFiles", CFILES	);
		            		back.putExtra("DATA", Data);
		            		back.putExtra("FECHA", Fecha);

		          			 startActivity(back);            		
		            }
		        });
	}
	
	 
   
    
    public void ReadData(String myString){
    	BufferedReader reader = new BufferedReader(
    			  new StringReader(Data));
    	int flag=0;
    	String line;
    	try {
			 while ((line = reader.readLine()) != null) {
    		 if (line.equals(myString)){
    		//	 flag=1;
 			//	Toast.makeText(getApplicationContext(), flag+"", Toast.LENGTH_LONG).show();///////////
				 line = reader.readLine(); 	
    			 while(!line.equals("EndOfData")){
    		//		 Toast.makeText(getApplicationContext(), line, Toast.LENGTH_LONG).show();///////////
    				 graph1 = graph1 + line + "\n\r";
    				 myArray1.add(line);
    				 line = reader.readLine(); 	
    			 }
    			 
    			 
    		  } 
    		 /* while ((line.substring(0))!="d"){
    			  line = reader.readLine();
    				Toast.makeText(getApplicationContext(), line, Toast.LENGTH_LONG).show();///////////
  				//myArray1.add(line);
    		  }*/
    	}
    		} catch(IOException e) {
    		  e.printStackTrace();
    		}
    }
    
    public void ReadData2(String myString){
    	BufferedReader reader = new BufferedReader(
    			  new StringReader(Data));
    	int flag=0;
    	String line;
    	try {
			 while ((line = reader.readLine()) != null) {
    		 if (line.equals(myString)){
    		//	 flag=1;
 			//	Toast.makeText(getApplicationContext(), flag+"", Toast.LENGTH_LONG).show();///////////
				 line = reader.readLine(); 	
    			 while(!line.equals("EndOfData")){
    		//		 Toast.makeText(getApplicationContext(), line, Toast.LENGTH_LONG).show();///////////
    				 graph2 = graph2 + line.toString() + "\n\r";
    				 myArray2.add(line);
    				 line = reader.readLine(); 	
    			 }
    			 
    			 
    		  } 
    		 /* while ((line.substring(0))!="d"){
    			  line = reader.readLine();
    				Toast.makeText(getApplicationContext(), line, Toast.LENGTH_LONG).show();///////////
  				//myArray1.add(line);
    		  }*/
    	}
    		} catch(IOException e) {
    		  e.printStackTrace();
    		}
    }
}
