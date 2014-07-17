package com.example.creatingimagedynamically;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends Activity implements OnTouchListener,OnDragListener{

	public ImageView image1,image2;
	ImageButton clickrefresh;
	RelativeLayout l,d;
	public byte[] img=null;
	public String LOGCAT=null;
	public int max= 1500;
	public int posX,posY,pPosX,pPosY,posXlong,posYlong,pPosXlong,pPosYlong;
	public Animation animTranslate,animAlpha,animScale,animRotate,animComplex;

	ArrayList<Integer> pictures = new ArrayList<Integer>();
	Integer[] pic = {R.drawable.a0,
			R.drawable.a1,
			R.drawable.a2,
			R.drawable.a3,
			R.drawable.a4,
			R.drawable.a5,
			R.drawable.a6,
			R.drawable.a7,
			R.drawable.a8,
			R.drawable.a9,
			R.drawable.a10,
			R.drawable.a11,
			R.drawable.a12,
			R.drawable.a13,
			R.drawable.a14,
			R.drawable.a15,
			R.drawable.a16,
			R.drawable.a17,
			R.drawable.a18,
			R.drawable.a19,
			R.drawable.a20,
			R.drawable.a00,
			R.drawable.pic1,
			R.drawable.pic2,
			R.drawable.pic3,
			R.drawable.pic4,
			R.drawable.pic5,
			R.drawable.pic6,
			R.drawable.pic7,
			R.drawable.pic8,
			R.drawable.pic9,
			R.drawable.pic10,
			R.drawable.pic11,
			R.drawable.pic12,
			
	};





	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		//image1=( ImageView)findViewById(R.id.imagevView1);
		//	image2=(ImageView)findViewById(R.id.imageView2);
		clickrefresh =(ImageButton)findViewById(R.id.refresh);
		clickrefresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				refresh();
			}
		});
		
		
		///Animations
		animTranslate = AnimationUtils.loadAnimation(this,R.anim.anim_translate);
		animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
		animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
		animRotate=AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
		animComplex=AnimationUtils.loadAnimation(this, R.anim.anim_complex);

		/// To be dropped layout

		l = ((RelativeLayout)(findViewById(R.id.layout)));
		l.setOnDragListener(this);

		d=(RelativeLayout)(findViewById(R.id.layout2));
		d.setOnDragListener(this);



		Display display= getWindowManager().getDefaultDisplay();
		int displaywidth= display.getWidth();
		int displayheight=display.getHeight();
		int displaywidthlong= display.getWidth();
		int displayheightlong=display.getHeight();
		Random R = new Random();
		//System.out.println(R.nextInt((displaywidth)-(displaywidth-500))+(displaywidth-500));
		//System.out.println(R.nextInt((displayheight)-(displayheight-500))+(displayheight-500));
		for (int i = 0; i<=R.nextInt(10-4)+4                                                                                                                                                       ; i ++) 
		{
			Random r = new Random();
			posX=r.nextInt((displaywidth)-(displaywidth-500))+(displaywidth-500);
			posY=r.nextInt((displayheight)-(displayheight-500))+(displayheight-500);
			if(pPosX!=posX&&pPosY!=posY)
			{
				if(posX<=displaywidth&&posY<=displayheight)
				{
					System.out.println("XX "+posX+" X "+pPosX+" W "+displaywidth);
					System.out.println("YY "+posY+" Y "+pPosY+" H "+displayheight);
					ImageView image = new ImageView(this);
					image.setImageResource(pic[r.nextInt(pic.length)]);
					image.setLayoutParams(new GridView.LayoutParams(70,70));
					image.setScaleType(ImageView.ScaleType.CENTER);
					image.setPadding(3,3,3,3);	
					image.setX(posX);
					image.setY(posY);
					image.setAdjustViewBounds(true);
					image.startAnimation(animScale);
					image.setOnTouchListener(this);
					l.addView(image);

					pPosX=posX;
					pPosY=posY;

				}
				else
				{ 
					Toast.makeText(getApplicationContext(), "crossing dimensions", Toast.LENGTH_SHORT).show();
					posX=posX-((posX-displaywidth)+r.nextInt(50));
					posY=posY-((posY-displayheight)+r.nextInt(50));


					ImageView image = new ImageView(this);
					image.setImageResource(/*pic[i]*/pic[r.nextInt(pic.length)]);
					image.setLayoutParams(new GridView.LayoutParams(70,70));
					image.setScaleType(ImageView.ScaleType.CENTER);
					image.setPadding(3,3,3,3);	
					image.setX(posX);
					image.setY(posY);
					image.setAdjustViewBounds(true);
					image.startAnimation(animScale);
					l.addView(image);

					pPosX=posX;
					pPosY=posY;

					Toast.makeText(getApplicationContext(), "TaskView reBuilded", Toast.LENGTH_SHORT).show();

				}
			}
			else
			{
				Toast.makeText(getApplicationContext(), "axis overlapping", Toast.LENGTH_SHORT).show();
				posX=posX-r.nextInt(100);
				posY=posY-r.nextInt(100);

				if(posX<=displaywidth&&posY<=displayheight)
				{

					ImageView image = new ImageView(this);
					image.setImageResource(/*pic[i]*/pic[r.nextInt(pic.length)]);
					image.setLayoutParams(new GridView.LayoutParams(70,70));
					image.setScaleType(ImageView.ScaleType.CENTER);
					image.setPadding(3,3,3,3);	
					image.setX(posX);
					image.setY(posY);
					image.setAdjustViewBounds(true);
					image.startAnimation(animScale);
					l.addView(image);
					pPosX=posX;
					pPosY=posY;
				}
				else
				{
					posX=posX-((posX-displaywidth)+r.nextInt(50));
					posY=posY-((posY-displayheight)+r.nextInt(50));


					ImageView image = new ImageView(this);
					image.setImageResource(/*pic[i]*/pic[r.nextInt(pic.length)]);
					image.setLayoutParams(new GridView.LayoutParams(70,70));
					image.setScaleType(ImageView.ScaleType.CENTER);
					image.setPadding(3,3,3,3);	
					image.setX(posX);
					image.setY(posY);
					image.setAdjustViewBounds(true);
					image.startAnimation(animScale);
					l.addView(image);

					pPosX=posX;
					pPosY=posY;
					Toast.makeText(getApplicationContext(), "TaskView reBuilded", Toast.LENGTH_SHORT).show();
				}
			}
		}


		//next On drop layout


		System.out.println(R.nextInt((displaywidth)-(500)));
		System.out.println(R.nextInt((displayheight)-(200)));

		for (int j = 0; j<R.nextInt(30-10)+10 ; j ++) 
		{
			Random r = new Random();
			posXlong=r.nextInt((displaywidthlong)-(displaywidthlong-500));
			posYlong=r.nextInt((displayheightlong)-(displayheightlong-500));
			if(pPosXlong!=posXlong&&pPosYlong!=posYlong)
			{
				if(posXlong<=displaywidthlong&&posYlong<=displayheightlong)

				{
					System.out.println("XXlong "+posXlong+" Xlong "+pPosXlong+" W "+displaywidthlong);
					System.out.println("YYlong "+posYlong+" Ylong "+pPosYlong+" H "+displayheightlong);
					ImageView imagelong = new ImageView(this);
					imagelong.setImageResource(pic[j]/*pic[r.nextInt(pic.length)]*/);
					imagelong.setLayoutParams(new GridView.LayoutParams(70,70));
					imagelong.setScaleType(ImageView.ScaleType.CENTER);
					imagelong.setPadding(3,3,3,3);	
					imagelong.setX(posXlong);
					imagelong.setY(posYlong);
					imagelong.setAdjustViewBounds(true);
					imagelong.startAnimation(animComplex);
					d.addView(imagelong);

					pPosXlong=posXlong;
					pPosYlong=posYlong;

				}
				else
				{ 
					Toast.makeText(getApplicationContext(), "crossing dimensionsLong", Toast.LENGTH_SHORT).show();
					posXlong=posXlong-((posXlong-displaywidthlong)+r.nextInt(50));
					posYlong=posYlong-((posYlong-displayheightlong)+r.nextInt(50));

					ImageView imagelong = new ImageView(this);
					imagelong.setImageResource(pic[j]/*pic[r.nextInt(pic.length)]*/);
					imagelong.setLayoutParams(new GridView.LayoutParams(70,70));
					imagelong.setScaleType(ImageView.ScaleType.CENTER);
					imagelong.setPadding(3,3,3,3);	
					imagelong.setX(posXlong);
					imagelong.setY(posYlong);
					imagelong.setAdjustViewBounds(true);
					imagelong.startAnimation(animComplex);
					d.addView(imagelong);

					pPosXlong=posXlong;
					pPosYlong=posYlong;
					Toast.makeText(getApplicationContext(), "long View reBuilded", Toast.LENGTH_SHORT).show();
				}
			}

			else
			{
				Toast.makeText(getApplicationContext(), "axis overlappingLong", Toast.LENGTH_SHORT).show();
				posXlong=posXlong-r.nextInt(100);
				posYlong=posYlong-r.nextInt(100);
				if(posXlong<=displaywidthlong&&posYlong<=displayheightlong)
				{
					ImageView imagelong = new ImageView(this);
					imagelong.setImageResource(pic[j]/*pic[r.nextInt(pic.length)]*/);
					imagelong.setLayoutParams(new GridView.LayoutParams(70,70));
					imagelong.setScaleType(ImageView.ScaleType.CENTER);
					imagelong.setPadding(3,3,3,3);	
					imagelong.setX(posXlong);
					imagelong.setY(posYlong);
					imagelong.setAdjustViewBounds(true);
					imagelong.startAnimation(animComplex);
					d.addView(imagelong);

					pPosXlong=posXlong;
					pPosYlong=posYlong;
				}
				else
				{
					posXlong=posXlong-((posXlong-displaywidthlong)+r.nextInt(50));
					posYlong=posYlong-((posYlong-displayheightlong)+r.nextInt(50));

					ImageView imagelong = new ImageView(this);
					imagelong.setImageResource(pic[j]/*pic[r.nextInt(pic.length)]*/);
					imagelong.setLayoutParams(new GridView.LayoutParams(70,70));
					imagelong.setScaleType(ImageView.ScaleType.CENTER);
					imagelong.setPadding(3,3,3,3);	
					imagelong.setX(posXlong);
					imagelong.setY(posYlong);
					imagelong.setAdjustViewBounds(true);
					imagelong.startAnimation(animComplex);
					d.addView(imagelong);

					pPosXlong=posXlong;
					pPosYlong=posYlong;
				}
				Toast.makeText(getApplicationContext(), "long View reBuilded", Toast.LENGTH_SHORT).show();

			}
		}
	}


	protected void refresh() 
	{
		// TODO Auto-generated method stub
		this.recreate();
	}


	@Override
	public boolean onDrag(View layoutview, DragEvent dragevent) {
		// TODO Auto-generated method stub
		
		int action = dragevent.getAction();
		switch (action) 
		{
		case DragEvent.ACTION_DRAG_STARTED:
			Log.d(LOGCAT, "Drag event started");
			break;
		case DragEvent.ACTION_DRAG_ENTERED:
			Log.d(LOGCAT, "Drag event entered into " + layoutview.toString());
			break;
		case DragEvent.ACTION_DRAG_EXITED:
			Log.d(LOGCAT, "Drag event exited from " + layoutview.toString());
			break;
		case DragEvent.ACTION_DROP:
			Log.d(LOGCAT, "Dropped");
			
			
			View view = (View) dragevent.getLocalState();
			if(view.getId()==pic[view.getId()]&& layoutview.getId()==pic[view.getId()])
			{
				ViewGroup from = (ViewGroup) view.getParent();
			from.removeView(view);
			layoutview.setBackgroundResource(pic[2]);
			}
		}
		return false;
	}


	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
			v.startDrag(null, shadowBuilder, v, 0);
			v.setVisibility(View.INVISIBLE);
			return true;
		} else {
			return false;
		}
	}
}

