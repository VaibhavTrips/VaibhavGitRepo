package com.example.creatingimagedynamically;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;


public class MainActivity extends ActionBarActivity {

	public ImageView image1,image2;
	RelativeLayout l,d;
	public byte[] img=null;
	public int max= 1500;
	public int posX,posY,pPosX,pPosY,posXlong,posYlong,pPosXlong,pPosYlong;
	public Animation animTranslate,animAlpha,animScale,animRotate,animComplex;

	ArrayList<Integer> pictures = new ArrayList<Integer>();
	Integer[] pic = {R.drawable.pic1,
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





	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		//image1=( ImageView)findViewById(R.id.imageView1);
		//	image2=(ImageView)findViewById(R.id.imageView2);

		///Animations
		animTranslate = AnimationUtils.loadAnimation(this,R.anim.anim_translate);
		animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        animRotate=AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        animComplex=AnimationUtils.loadAnimation(this, R.anim.anim_complex);

		/// To be dropped layout

		l = ((RelativeLayout)(findViewById(R.id.layout)));

		d=(RelativeLayout)(findViewById(R.id.layout2));



		Display display= getWindowManager().getDefaultDisplay();
		int displaywidth= display.getWidth();
		int displayheight=display.getHeight();
		int displaywidthlong= display.getWidth();
		int displayheightlong=display.getHeight();
		Random R = new Random();
		//System.out.println(R.nextInt((displaywidth)-(displaywidth-500))+(displaywidth-500));
		//System.out.println(R.nextInt((displayheight)-(displayheight-500))+(displayheight-500));
		for (int i = 0; i<=pic.length ; i ++) 
		{
			Random r = new Random();
			posX=r.nextInt((displaywidth)-(displaywidth-500))+(displaywidth-500);
			posY=r.nextInt((displayheight)-(displayheight-500))+(displayheight-500);
			if(pPosX!=posX&&pPosY!=posY&&posX<=displaywidth&&posY<=displayheight)
			{
				System.out.println("XX "+posX+" X "+pPosX+" W "+displaywidth);
				System.out.println("YY "+posY+" Y "+pPosY+" H "+displayheight);
				ImageView image = new ImageView(this);
				image.setImageResource(/*pic[i]*/pic[r.nextInt(pic.length)]);
				image.setLayoutParams(new GridView.LayoutParams(90,90));
				image.setScaleType(ImageView.ScaleType.CENTER);
				image.setPadding(5,5,5,5);	
				image.setX(posX);
				image.setY(posY);
				image.setAdjustViewBounds(true);
				image.startAnimation(animScale);
				l.addView(image);
				pPosX=posX;
				pPosY=posY;
			}
			else{ Toast.makeText(getApplicationContext(), "overlapping", Toast.LENGTH_SHORT).show(); }
		}



		//next On drop layout


		System.out.println(R.nextInt((displaywidth)-(500)));
		System.out.println(R.nextInt((displayheight)-(200)));

		for (int j = 0; j<pic.length ; j ++) 
		{
			Random r = new Random();
			posXlong=r.nextInt((displaywidthlong)-(displaywidthlong-500));
			posYlong=r.nextInt((displayheightlong)-(displayheightlong-500));
			if(pPosXlong!=posXlong&&pPosYlong!=posYlong&&posXlong<=displaywidthlong&&posYlong<=displayheightlong)
			{
				System.out.println("XXlong "+posXlong+" Xlong "+pPosXlong+" W "+displaywidthlong);
				System.out.println("YYlong "+posYlong+" Ylong "+pPosYlong+" H "+displayheightlong);
				ImageView imagelong = new ImageView(this);
				imagelong.setImageResource(pic[j]/*pic[r.nextInt(pic.length)]*/);
				imagelong.setLayoutParams(new GridView.LayoutParams(90,90));
				imagelong.setScaleType(ImageView.ScaleType.CENTER);
				imagelong.setPadding(5,5,5,5);	
				imagelong.setX(posXlong);
				imagelong.setY(posYlong);
				imagelong.setAdjustViewBounds(true);
				imagelong.startAnimation(animComplex);
				d.addView(imagelong);

				pPosXlong=posXlong;
				pPosYlong=posYlong;
			}
			else{ Toast.makeText(getApplicationContext(), "overlapping", Toast.LENGTH_SHORT).show(); }
		}
	}
}
