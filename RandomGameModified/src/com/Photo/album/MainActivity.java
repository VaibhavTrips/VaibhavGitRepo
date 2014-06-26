package com.Photo.album;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity  {
	ArrayList<String> list;
	public Button shuffle;
	public TextView text;
	public int max=99;

	public GridView Grid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		Grid=(GridView)findViewById(R.id.gridView);
		shuffle=(Button)findViewById(R.id.Shuffle);
		text=(TextView)findViewById(R.id.num);

		//		Grid.setOnItemClickListener(new OnItemClickListener() {
		//			
		//			@Override
		//			public void onItemClick(AdapterView<?> parent, View view,
		//					int position, long id) {
		//				// TODO Auto-generated method stub
		//				Toast.makeText(getApplicationContext(), " :P "+position, Toast.LENGTH_SHORT).show();
		//				
		//			}
		//		});

		shuffle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Let's start", Toast.LENGTH_SHORT).show();
				ImageAdapter random = new ImageAdapter(null);
				random.callrandom();


			}
		});



		Grid.setAdapter(new ImageAdapter(this));

	}

	public class ImageAdapter extends BaseAdapter
	{
		public Context context;
		ArrayList<Integer> pictures = new ArrayList<Integer>();
		Integer[] pic = {R.drawable.pic1,R.drawable.space,
				R.drawable.pic2,R.drawable.space,
				R.drawable.pic3,R.drawable.space,
				R.drawable.pic4,R.drawable.space,
				R.drawable.pic5,R.drawable.space,
				R.drawable.pic6,R.drawable.pic7,
				R.drawable.space,R.drawable.pic8,
				R.drawable.pic9,R.drawable.space,
				R.drawable.pic10,R.drawable.space,
				R.drawable.pic11,R.drawable.space,
				R.drawable.pic12,R.drawable.space,
				R.drawable.pic1,R.drawable.space,
				R.drawable.pic2,R.drawable.space,
				R.drawable.pic3,R.drawable.space,
				R.drawable.pic4,R.drawable.space,
				R.drawable.pic5,R.drawable.space,
				R.drawable.pic6,R.drawable.space,
				R.drawable.pic7,R.drawable.space,
				R.drawable.pic8,R.drawable.space,
				R.drawable.pic9,R.drawable.space,
				R.drawable.pic10,R.drawable.space,
				R.drawable.pic11,R.drawable.space,
				R.drawable.pic12,R.drawable.space,
				R.drawable.space,R.drawable.space
				,R.drawable.space,R.drawable.space
				,R.drawable.space,R.drawable.space,R.drawable.space
				,R.drawable.space,R.drawable.space,R.drawable.space
				,R.drawable.space,R.drawable.space,R.drawable.space
				,R.drawable.space,R.drawable.space,R.drawable.space
				,R.drawable.space,R.drawable.space,R.drawable.space
				,R.drawable.space,R.drawable.space,R.drawable.space
				,R.drawable.space,R.drawable.space,R.drawable.space};




		public ImageAdapter(Context c) {
			// TODO Auto-generated constructor stub
			context=c;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pic.length ;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return pic[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public void callrandom() {
			// TODO Auto-generated method stub
			Random r= new Random();

			for(int a=0;a<=5;a++)
			{
				text.setText(String.valueOf(r.nextInt(max)));
				System.out.println(r.nextInt(max));


			}


		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub\

			for (int index = 0; index < pic.length; index++)
			{
				pictures.add(pic[index]);
			}

			Collections.shuffle(pictures);

			ImageView imageview;
			if(convertView==null)
			{
				imageview=new ImageView(context);
				imageview.setLayoutParams(new GridView.LayoutParams(100,100));
				imageview.setScaleType(ImageView.ScaleType.CENTER);
				imageview.setPadding(8,8,8,8);
			}
			else
			{
				imageview =(ImageView)convertView;

			}

			imageview.setImageResource(pictures.get(position));
			return imageview;
		}

	}

}


