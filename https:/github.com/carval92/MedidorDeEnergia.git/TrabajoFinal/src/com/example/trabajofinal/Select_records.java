package com.example.trabajofinal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Select_records extends Activity {
	
    private Button Logout1, Blist,Bgraph;
    private RadioButton R1;
    private RadioButton R2;
    //String last;
  //contador de archivos
    int CountFiles=0;
    ListView data1, data2;
	ArrayList<String> myArray1;
	String compare1="",compare2="";
	File file;
	private TextView sd1, sd;
	 ArrayAdapter<String> myAdapter;
	 String Data, temp="",Fecha="";
	 
	 @Override 
	    public boolean onCreateOptionsMenu(Menu menu){
	    	MenuInflater menuInflater = getMenuInflater();
	    	menuInflater.inflate(R.menu.videomenu, menu);
	    	return true;
	    }
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item){
			Intent intent=null;
			switch(item.getItemId()){
				case R.id.Video:
					 intent = new Intent (Select_records.this, VideoView.class);
	    			startActivity(intent);
					return true;
			}
	        return false;
		}
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_records);
		
		R1= (RadioButton) findViewById(R.id.Today);
		R2= (RadioButton) findViewById(R.id.compare);
		Logout1= (Button) findViewById(R.id.Logout1);
		Blist= (Button) findViewById(R.id.Blist1);
		Bgraph= (Button) findViewById(R.id.Bgraph1);
		data1= (ListView) findViewById(R.id.LlistView1);
		data2= (ListView) findViewById(R.id.LlistView2);
		 sd = (TextView) findViewById(R.id.Ldata1);
		 sd1 = (TextView) findViewById(R.id.textView3);
			myArray1=new ArrayList<String>();
			
			 data1.setVisibility(View.INVISIBLE);
			    data2.setVisibility(View.INVISIBLE);
			    sd1.setVisibility(View.INVISIBLE);
			    sd.setVisibility(View.INVISIBLE);
			    
		 ///////////////////////////////////////BUNDLE
		Bundle bundle = getIntent().getExtras();
		if(bundle != null){
			CountFiles = bundle.getInt("CountFiles");
			Data = bundle.getString("DATA");
			Fecha = bundle.getString("FECHA");
		}
	//	Toast.makeText(getApplicationContext(), CountFiles+"", Toast.LENGTH_LONG).show();///////////
		//int x=CountFiles;
		/////////////////////////////////////////
     //   Toast.makeText(getApplicationContext(), Fecha, Toast.LENGTH_SHORT).show();///////////

		ReadData2();
		
		
	    myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myArray1);
		
		/////////////////Appear / disappear listviews ///////////

		OnClickListener first_radio_listener = new OnClickListener (){
			 public void onClick(View v) {
			   //Your Implementaions...
				  data1.setVisibility(View.INVISIBLE);
				    data2.setVisibility(View.INVISIBLE);
				    sd1.setVisibility(View.INVISIBLE);
				    sd.setVisibility(View.INVISIBLE);
			 }
			};R1.setOnClickListener(first_radio_listener);
			
			OnClickListener Second_radio_listener = new OnClickListener (){
				 public void onClick(View v) {
				   //Your Implementaions...
					 	data1.setVisibility(View.VISIBLE);
					    data2.setVisibility(View.VISIBLE);
					    sd1.setVisibility(View.VISIBLE);
					    sd.setVisibility(View.VISIBLE);
					    data1.setAdapter(myAdapter);
					    data2.setAdapter(myAdapter);
				 }
				};R2.setOnClickListener(Second_radio_listener);
				
			   
				
	//ListView onClick/////////////////
		OnItemClickListener registroOnItemClickListener = new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			
				//String color = listaColores.get(position);
				 compare1 = "data_"+ (position+1);
				 String aF=  parent.getItemAtPosition(position).toString();
				Toast.makeText(getApplicationContext(), aF, Toast.LENGTH_LONG).show();  
				
			} 
        };
        data1.setOnItemClickListener(registroOnItemClickListener);
        
        /////////////////LONGCLICK1////////
        data1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                    int position, long arg3) {
                // TODO Auto-generated method stub
                 String str= "data_"+ (position+1);
                 MayMen(str);
                return true;
            }
        }); 
        
        //////////////////////////////////
        
        
        OnItemClickListener registro2OnItemClickListener = new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			
				//String color = listaColores.get(position);
				 compare2 = "data_"+ (position+1);
				 String aF2=  parent.getItemAtPosition(position).toString();
				Toast.makeText(getApplicationContext(), aF2, Toast.LENGTH_LONG).show();  
				
			} 
        };
        data2.setOnItemClickListener(registro2OnItemClickListener);
        
        /////////////////LONGCLICK2////////
        data2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                    int position, long arg3) {
                // TODO Auto-generated method stub
                 String str= "data_"+ (position+1);
                 MayMen(str);
                return true;
            }
        }); 
        
        //////////////////////////////////
        
		//////////////////////////////////////////////////////////////////////////////////

		// go to list
		Blist.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	
              // check today or compare..
            	if(R1.isChecked()){
            		
            		Intent intent1 = new Intent (Select_records.this, List_of_Records.class);
            		intent1.putExtra("CountFiles", CountFiles);
            		intent1.putExtra("Today", CountFiles);
            		intent1.putExtra("last", 1);
            		intent1.putExtra("DATA", Data);
            		intent1.putExtra("FECHA", Fecha);

          			 startActivity(intent1);
            	}else{
            		if(compare1.equals("")||compare2.equals("")){
                		
                	}else{
            		Intent intent1 = new Intent (Select_records.this, List_of_Records.class);
            		intent1.putExtra("CountFiles", CountFiles);
            		intent1.putExtra("Today", 0);
            		intent1.putExtra("compare", compare1);
            		intent1.putExtra("compare2", compare2);
            		intent1.putExtra("DATA", Data);
            		intent1.putExtra("FECHA", Fecha);

            		//intent1.putExtra("last", 1);
          			 startActivity(intent1);
             		
                	}
            	}
            	
            }
        });
		
		// go to graph intent.
	
		Bgraph.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

            	if(R1.isChecked()){
                    Intent graph = new Intent (Select_records.this, Graph_Record.class);
            		graph.putExtra("CountFiles", CountFiles);
            		graph.putExtra("Today", CountFiles);
            		graph.putExtra("Compare", "");
            		graph.putExtra("Compare2", "");
            		graph.putExtra("DATA", Data);
            		graph.putExtra("FECHA", Fecha);

        			 startActivity(graph);

            	}else{
            		if(compare1.equals("")||compare2.equals("")){
                		
                	}else{
                       Intent graph = new Intent (Select_records.this, Graph_Record.class);
                       graph.putExtra("CountFiles", CountFiles);
                       graph.putExtra("Today", 0);
                       graph.putExtra("Compare", compare1);
                       graph.putExtra("Compare2", compare2);
                       graph.putExtra("DATA", Data);
                       graph.putExtra("FECHA", Fecha);

                       startActivity(graph);

                	}
            	}
            	

            }
        });
		
		//logout...
		Logout1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            		myArray1.clear();
            		Intent intent1 = new Intent (Select_records.this, BluetoothChat.class);
          			 startActivity(intent1);
            	
            }
        });
	}

	
	
	public void ReadData2(){
    	BufferedReader reader = new BufferedReader(
    			  new StringReader(Fecha));
    	int flag=0;
    	String line;
    	try {
			 while ((line = reader.readLine()) != null) {
    		//		 Toast.makeText(getApplicationContext(), line, Toast.LENGTH_LONG).show();///////////
    				 myArray1.add(line);
    			  
    	}
    		} catch(IOException e) {
    		  e.printStackTrace();
    		}
    }
	
	 public void MayMen(String myString){
	    	BufferedReader reader = new BufferedReader(
	    			  new StringReader(Data));
	    	String line;
			int mayor=0, menor=999999;
	    	try {
				 while ((line = reader.readLine()) != null) {
	    		 if (line.equals(myString)){
	 			//	Toast.makeText(getApplicationContext(), flag+"", Toast.LENGTH_LONG).show();///////////
					 line = reader.readLine(); 	
	    			 while(!line.equals("EndOfData")){
	    		//		 Toast.makeText(getApplicationContext(), line, Toast.LENGTH_LONG).show();//////////
	    			
	    				 int x=Integer.parseInt(line);
	    				 if(x>mayor){
	    					 mayor=x;
	    				 }
	    				 if(x<menor){
	    					 menor=x;
	    				 }
	    				 line = reader.readLine(); 

	    			 }
	    			 
	    			 
	    		  } 

	    	}	    
				 Toast.makeText(getApplicationContext(), "Greatest: "+mayor+"\n\r"+"smallest: "+menor, Toast.LENGTH_LONG).show();  
	    		} catch(IOException e) {
	    		  e.printStackTrace();
	    		}
	    }
}
