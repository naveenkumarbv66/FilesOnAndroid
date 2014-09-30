package com.naveen.naveenfilesonandroid;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	Button CreateFileButton,ReadFileButton;
	EditText et;
	TextView tv,tv1;
	String Path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        CreateFileButton=(Button)findViewById(R.id.button1);
        ReadFileButton=(Button)findViewById(R.id.button2);
        et=(EditText)findViewById(R.id.editText1);
        tv=(TextView)findViewById(R.id.textView1);
        tv1=(TextView)findViewById(R.id.textView2);
        
        CreateFileButton.setOnClickListener(this);
        ReadFileButton.setOnClickListener(this);
        
        
        File FileDir=getFilesDir();
        Path =FileDir.getAbsolutePath();
        tv.setText(Path);
    }
    
    void CreateFile(View v) throws IOException{
    	
    	FileOutputStream file=openFileOutput("Naveen.txt", MODE_PRIVATE);
        if(et.length()<1){
        	file.write(("").toString().getBytes());
        }
        else{
        	file.write(et.getText().toString().getBytes());
        }
    	
    	
    	file.close();
    	
    	et.setText("");
    	tv.setText("File updated....");
    	Toast.makeText(this,"File has been created and updated",Toast.LENGTH_LONG).show();
    }
    
     void ReadFile(View v) throws IOException{
    	
    	FileInputStream file=openFileInput("Naveen.txt");
    	
    	BufferedInputStream Buffer=new BufferedInputStream(file);
    	StringBuffer BufferString=new StringBuffer();
    	
    	while (Buffer.available()!=0) {
			char c=(char) Buffer.read();
			BufferString.append(c);
		}
    	
    	Buffer.close();
    	file.close();
    	
    	tv1.setText(BufferString.toString());
    	//et.setText(BufferString.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		
		case R.id.button1:
			try {
				CreateFile(v);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
			break;
		case R.id.button2:
			try {
				ReadFile(v);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
		
	}
    
}
