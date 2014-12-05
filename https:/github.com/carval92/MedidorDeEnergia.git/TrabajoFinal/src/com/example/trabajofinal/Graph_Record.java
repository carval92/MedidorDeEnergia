package com.example.trabajofinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Graph_Record extends Activity {    
   private Bundle bundle;
    String compare1, compare2, Data, Fecha;
    int today,CFILES;
	int i1=0;
	int i2=0;
	//String last;
	Button logoutG;
	Button BACK;
	TextView title;
	//private Number[] series1Numbers;// = new Number [24];// =  new Number [24];
	//private Number[] series2Numbers;// = new Number [24];
	
	int[] aux = new int[24];
	int[] aux2 = new int[24];
	
	private View mChart;
	
	// private String[] mMonth = new String[] { "Jan", "Feb" , "Mar", "Apr", "May", "Jun",
	//											 "Jul", "Aug" , "Sep", "Oct", "Nov", "Dec" };
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graph__record);
		
		logoutG = (Button) findViewById(R.id.LogoutG);
		BACK = (Button) findViewById(R.id.bgBACK1);
		title= (TextView) findViewById(R.id.GDataLabel);
	//	mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

		bundle = getIntent().getExtras();
		if(bundle != null){

			today = bundle.getInt("Today");
			 compare1 = bundle.getString("Compare");
			 compare2 = bundle.getString("Compare2");	
			 Data = bundle.getString("DATA");
			 CFILES = bundle.getInt("CountFiles");
			 Fecha = bundle.getString("FECHA");

			// last = bundle.getString("last");
		}
		/*	
		 * Toast.makeText(getApplicationContext(), ""+today, Toast.LENGTH_LONG).show();///////////
		Toast.makeText(getApplicationContext(), Data, Toast.LENGTH_LONG).show();///////////
		Toast.makeText(getApplicationContext(), compare1, Toast.LENGTH_LONG).show();///////////
			 Toast.makeText(getApplicationContext(), compare2, Toast.LENGTH_LONG).show();///////////
			 */
			 
			 if(today != 0){
					ReadData("data_"+today);//add last for 3r
					for(int x=0; x<24; x++){
						aux2[x]=0;
					}
					title.setText("data_"+today);
				}else{
					ReadData(compare1);//add last for 3r
					ReadData2(compare2);//add last for 3r
					title.setText(compare1+" vs "+compare2);

				}

		/*	 // check compare or today...
		if(compare2.equals("")){
			ReadData();//add last for 3r
		}else{
			ReadData();//add last for 3r
			ReadData2();//add last for 3r
			
		}*/

		
		 openChart();

	
        // Logout ...
    	logoutG.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
              	
            		Intent intent1 = new Intent (Graph_Record.this, BluetoothChat.class);
          			 startActivity(intent1);
            	
            }
        });
    	
    	//BACK.....
    	/* today = bundle.getInt("Today");
			 compare = bundle.getString("compare");
			 compare2 = bundle.getString("compare2");
			 Data = bundle.getString("DATA");*/
    	BACK.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
              	
            		Intent GTL = new Intent (Graph_Record.this, List_of_Records.class);
            		GTL.putExtra("CountFiles", CFILES);
            		GTL.putExtra("Today", today);
            		GTL.putExtra("compare", compare1);
            		GTL.putExtra("compare2", compare2);
            		GTL.putExtra("DATA", Data);
            		GTL.putExtra("FECHA", Fecha);

          			 startActivity(GTL);
            	
            }
        });
    	
	}
	
	private void openChart(){
		// int[] x = { 0,1,2,3,4,5,6,7, 8, 9, 10, 11 };
		// int[] income = { 2000,2500,2700,3000,2800,3500,3700,3800, 0,0,0,0};
		// int[] expense = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400, 0, 0, 0, 0 };
		 int mayor;
		 int auxm1=0;//, auxm2=0, mayorY=0;
		 // Creating an XYSeries for Income
		 XYSeries incomeSeries = new XYSeries("Income");
		 // Creating an XYSeries for Expense
		 XYSeries expenseSeries = new XYSeries("Expense");
		 // Adding data to Income and Expense Series
		 
		 for(int i=0;i<i1;i++){
		 incomeSeries.add(i,aux[i]);
		 if (aux[i] > auxm1){
			 auxm1=aux[i];
		 }
		 }
		 for(int i=0;i<i2;i++){
			 expenseSeries.add(i,aux2[i]);
			 if (aux2[i] > auxm1){
				 auxm1=aux2[i];
			 }
		 }
		 
		 if(i2>=i1){
			  mayor = i2;
			  }else{
				  mayor = i1;
			  }
		 
		 // Creating a dataset to hold each series
		 XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		 // Adding Income Series to the dataset
		 dataset.addSeries(incomeSeries);
		 // Adding Expense Series to dataset
		 dataset.addSeries(expenseSeries);

		 // Creating XYSeriesRenderer to customize incomeSeries
		 XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
		 incomeRenderer.setColor(Color.CYAN); //color of the graph set to cyan
		 incomeRenderer.setFillPoints(true);
		 incomeRenderer.setLineWidth(2);
		 incomeRenderer.setDisplayChartValues(true);
		 incomeRenderer.setDisplayChartValuesDistance(10); //setting chart value distance 

		 // Creating XYSeriesRenderer to customize expenseSeries
		 XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
		 expenseRenderer.setColor(Color.GREEN);
		 expenseRenderer.setFillPoints(true);
		 expenseRenderer.setLineWidth(2);
		 expenseRenderer.setDisplayChartValues(true);

		 // Creating a XYMultipleSeriesRenderer to customize the whole chart
		 XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
		 multiRenderer.setXLabels(0);
		 multiRenderer.setChartTitle("Power vs Time");
		 multiRenderer.setXTitle("Year 2014");
		 multiRenderer.setYTitle("Power");

		 /***
		 * Customizing graphs
		 */
		//setting text size of the title
		 multiRenderer.setChartTitleTextSize(28);
		 //setting text size of the axis title
		 multiRenderer.setAxisTitleTextSize(24);
		 //setting text size of the graph lable
		 multiRenderer.setLabelsTextSize(24);
		 //setting zoom buttons visiblity
		 multiRenderer.setZoomButtonsVisible(false);
		 //setting pan enablity which uses graph to move on both axis
		 multiRenderer.setPanEnabled(false, false);
		 //setting click false on graph
		 multiRenderer.setClickEnabled(false);
		 //setting zoom to false on both axis
		 multiRenderer.setZoomEnabled(false, false);
		 //setting lines to display on y axis
		 multiRenderer.setShowGridY(false);
		 //setting lines to display on x axis
		 multiRenderer.setShowGridX(false);
		 //setting legend to fit the screen size
		 multiRenderer.setFitLegend(true);
		 //setting displaying line on grid
		 multiRenderer.setShowGrid(false);
		 //setting zoom to false
		 multiRenderer.setZoomEnabled(false);
		 //setting external zoom functions to false
		 multiRenderer.setExternalZoomEnabled(false);
		 //setting displaying lines on graph to be formatted(like using graphics)
		 multiRenderer.setAntialiasing(true);
		 //setting to in scroll to false
		 multiRenderer.setInScroll(false);
		 //setting to set legend height of the graph
		 multiRenderer.setLegendHeight(30);
		 //setting x axis label align
		 multiRenderer.setXLabelsAlign(Align.CENTER);
		 //setting y axis label to align
		 multiRenderer.setYLabelsAlign(Align.LEFT);
		 //setting text style
		 multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
		 //setting no of values to display in y axis
		 multiRenderer.setYLabels(mayor);
		 // setting y axis max value, Since i'm using static values inside the graph so i'm setting y max value to 4000.
		 // if you use dynamic values then get the max y value and set here
		 multiRenderer.setYAxisMax(auxm1);
		 //setting used to move the graph on xaxiz to .5 to the right
		 multiRenderer.setXAxisMin(-0.5);
		//setting max values to be display in x axis
		 multiRenderer.setXAxisMax(mayor);
		 //setting bar size or space between two bars
		 multiRenderer.setBarSpacing(0.5);
		 //Setting background color of the graph to transparent
		 multiRenderer.setBackgroundColor(Color.TRANSPARENT);
		 //Setting margin color of the graph to transparent
		 multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));
		 multiRenderer.setApplyBackgroundColor(true);

		 //setting the margin size for the graph in the order top, left, bottom, right
		 multiRenderer.setMargins(new int[]{30, 30, 30, 30});
		 
		 
		 for(int i=0; i< mayor;i++){
		// multiRenderer.addXTextLabel(i, mMonth[i]);
		multiRenderer.addXTextLabel(i, i+"");

		 }

		 // Adding incomeRenderer and expenseRenderer to multipleRenderer
		 // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
		 // should be same
		 multiRenderer.addSeriesRenderer(incomeRenderer);
		 multiRenderer.addSeriesRenderer(expenseRenderer);

		 //this part is used to display graph on the xml
		 LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart);
		 //remove any views before u paint the chart
		 chartContainer.removeAllViews();
		 //drawing bar chart
		 mChart = ChartFactory.getBarChartView(Graph_Record.this, dataset, multiRenderer,Type.DEFAULT);
		 //adding the view to the linearlayout
		 chartContainer.addView(mChart);

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
    				 aux2[i2]= Integer.parseInt(line.toString());
 					i2++;
    				 line = reader.readLine(); 	
    			 }
    			 
    			 
    		  } 
    		
    	}
    		} catch(IOException e) {
    		  e.printStackTrace();
    		}
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
  				 aux[i1]= Integer.parseInt(line.toString());
					i1++;
  				 line = reader.readLine(); 	
  			 }
  			 
  			 
  		  } 
  		
  	}
  		} catch(IOException e) {
  		  e.printStackTrace();
  		}
  }
}
