package com.example.creatingimagedynamically;

import java.util.ArrayList;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
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
import android.widget.AbsoluteLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends Activity implements OnTouchListener,OnDragListener{

	ImageView[] image=new ImageView[100];
	ImageView[] imagelong=new ImageView[100];

	int PicIDlong,PicID;
	ImageButton clickrefresh;
	AbsoluteLayout mainlayout;
	int tag1,tag2,k;
	public byte[] img=null;
	public String LOGCAT=null;
	public int max= 1500;
	public int posX,posY,pPosX,pPosY,posXlong,posYlong,pPosXlong,pPosYlong;
	//public Animation animTranslate,animAlpha,animScale,animRotate,animComplex,animFastCircle;
	public Animation[] animation;

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
			R.drawable.a21,
			R.drawable.a22,
			R.drawable.a23,
			R.drawable.a24,
			R.drawable.a25,
			R.drawable.a26,
			R.drawable.a27,
			R.drawable.a28,
			R.drawable.a29,
			R.drawable.a30,
			R.drawable.a31,
			R.drawable.a32,
			R.drawable.a33,
			R.drawable.a34,
			R.drawable.a35,
			R.drawable.a36,
			R.drawable.a37,
			R.drawable.a38,
			R.drawable.a39,
			R.drawable.a40,
			R.drawable.a41,
			R.drawable.a42,
			R.drawable.a43,
			R.drawable.a44,
			R.drawable.a45,
			R.drawable.a46,
			R.drawable.a47,
			R.drawable.a48,
			R.drawable.a49,
			R.drawable.a50,
			R.drawable.a51,
			R.drawable.a52,
			R.drawable.a53,
			R.drawable.a54,
			R.drawable.a55,
			R.drawable.a56,
			R.drawable.a57,
			R.drawable.a58,
			R.drawable.a59,
			R.drawable.a60,
			R.drawable.a61,
			R.drawable.a62,
			R.drawable.a63,
			R.drawable.a64,
			R.drawable.a65,
			R.drawable.a66,
			R.drawable.a67,
			R.drawable.a68,
			R.drawable.a69,
			R.drawable.a70,
			R.drawable.a71,
			R.drawable.a72,
			R.drawable.a73,
			R.drawable.a74,
			R.drawable.a75,
			R.drawable.a76,
			R.drawable.a77,
			R.drawable.a78,
			R.drawable.a79,
			R.drawable.a80,
			R.drawable.a81,
			R.drawable.a82,
			R.drawable.a83,
			R.drawable.a84,
			R.drawable.a85,
			R.drawable.a86,
			R.drawable.a87,
			R.drawable.a88,
			R.drawable.a89,
			R.drawable.a90,
			R.drawable.a91,
			R.drawable.a92,
			R.drawable.a93,
			R.drawable.a94,
			R.drawable.a95,
			R.drawable.a96,
			R.drawable.a97,
			R.drawable.a98,
			R.drawable.a99
			
			
			

	};





	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		clickrefresh =(ImageButton)findViewById(R.id.refresh);
		clickrefresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				refresh();
			}
		});

		///Animations
		/*animTranslate = AnimationUtils.loadAnimation(this,R.anim.anim_translate);
		animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
		animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
		animRotate=AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
		animComplex=AnimationUtils.loadAnimation(this, R.anim.anim_complex);
		animFastCircle=AnimationUtils.loadAnimation(this, R.anim.anim_fastcircle);*/

		//AnimationRandomizingArray
		animation =new Animation[4];
		animation[0] = AnimationUtils.loadAnimation(this,R.anim.anim_translate);
		animation[1]= AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
		animation[2] = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
		animation[3]=AnimationUtils.loadAnimation(this, R.anim.anim_rotate);



		/// To be dropped layout
		mainlayout = ((AbsoluteLayout)(findViewById(R.id.layout)));
		mainlayout.setOnTouchListener(this);



		Display display= getWindowManager().getDefaultDisplay();
		int displaywidth= display.getWidth();
		int displayheight=display.getHeight();
		int displaywidthlong= display.getWidth();
		int displayheightlong=display.getHeight();
		Random R = new Random();
		for (int i = 0; i<5/*R.nextInt(10-4)+4*/; i ++) 
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
					image[i] = new ImageView(this);
					int PicID=r.nextInt(pic.length)-1;
					if(PicID==-1)
					{
						PicID++;
					}
					image[i].setImageResource(pic[PicID]);
					image[i].setTag(PicID);
					int tag = (Integer) image[i].getTag();
					System.out.println("PicTagTask"+tag);
					image[i].setLayoutParams(new GridView.LayoutParams(70,70));
					image[i].setScaleType(ImageView.ScaleType.CENTER);
					image[i].setPadding(3,3,3,3);	
					image[i].setX(posX);
					image[i].setY(posY);
					image[i].setAdjustViewBounds(true);
					image[i].startAnimation(animation[R.nextInt(4)]/*animScale*/);
					image[i].setOnTouchListener(this);
					System.out.println("ImageViewID ="+image[i].getId());
					mainlayout.addView(image[i]);

					pPosX=posX;
					pPosY=posY;

				}
				else
				{ 
					Toast.makeText(getApplicationContext(), "crossing dimensions", Toast.LENGTH_SHORT).show();
					posX=posX-((posX-displaywidth)+r.nextInt(50));
					posY=posY-((posY-displayheight)+r.nextInt(50));

					image[i].setOnTouchListener(this);
					image[i] = new ImageView(this);
					int PicID=r.nextInt(pic.length)-1;
					if(PicID==-1)
					{
						PicID++;
					}
					image[i].setImageResource(pic[PicID]);
					image[i].setTag(PicID);
					int tag = (Integer) image[i].getTag();
					System.out.println("PicTagTask"+tag);
					image[i].setLayoutParams(new GridView.LayoutParams(70,70));
					image[i].setScaleType(ImageView.ScaleType.CENTER);
					image[i].setPadding(3,3,3,3);	
					image[i].setX(posX);
					image[i].setY(posY);
					image[i].setAdjustViewBounds(true);
					image[i].startAnimation(animation[R.nextInt(4)]/*animScale*/);
					mainlayout.addView(image[i]);

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

					image[i].setOnTouchListener(this);
					image[i] = new ImageView(this);
					int PicID=r.nextInt(pic.length)-1;
					if(PicID==-1)
					{
						PicID++;
					}
					image[i].setImageResource(pic[PicID]);
					image[i].setTag(PicID);
					int tag = (Integer) image[i].getTag();
					System.out.println("PicTagTask"+tag);
					image[i].setLayoutParams(new GridView.LayoutParams(70,70));
					image[i].setScaleType(ImageView.ScaleType.CENTER);
					image[i].setPadding(3,3,3,3);	
					image[i].setX(posX);
					image[i].setY(posY);
					image[i].setAdjustViewBounds(true);
					image[i].startAnimation(animation[R.nextInt(4)]/*animScale*/);
					mainlayout.addView(image[i]);
					pPosX=posX;
					pPosY=posY;
				}
				else
				{
					posX=posX-((posX-displaywidth)+r.nextInt(50));
					posY=posY-((posY-displayheight)+r.nextInt(50));

					image[i].setOnTouchListener(this);
					image[i] = new ImageView(this);
					int PicID=r.nextInt(pic.length)-1;
					if(PicID==-1)
					{
						PicID++;
					}
					image[i].setImageResource(pic[PicID]);
					image[i].setTag(PicID);
					int tag = (Integer) image[i].getTag();
					System.out.println("PicTagTask"+tag);
					image[i].setLayoutParams(new GridView.LayoutParams(70,70));
					image[i].setScaleType(ImageView.ScaleType.CENTER);
					image[i].setPadding(3,3,3,3);	
					image[i].setX(posX);
					image[i].setY(posY);
					image[i].setAdjustViewBounds(true);
					image[i].startAnimation(animation[R.nextInt(4)]/*animScale*/);
					mainlayout.addView(image[i]);

					pPosX=posX;
					pPosY=posY;
					Toast.makeText(getApplicationContext(), "TaskView reBuilded", Toast.LENGTH_SHORT).show();
				}
			}
		}


		//next On drop layout


		System.out.println(R.nextInt((displaywidth)-(500)));
		System.out.println(R.nextInt((displayheight)-(200)));

		for (int j = 0; j<10/*R.nextInt(30-10)+10*/ ; j ++) 
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
					imagelong[j] = new ImageView(this);
					int PicIDlong=r.nextInt(pic.length)-1;
					if(PicIDlong==-1)
					{
						PicIDlong++;
					}
					imagelong[j].setImageResource(pic[PicIDlong]);
					imagelong[j].setTag(PicIDlong);
					int tag = (Integer) imagelong[j].getTag();
					System.out.println("PiclongTagTask"+tag);
					//System.out.println("PicIDDrop"+PicIDlong);
					//imagelong.setImageResource(pic[j]/*pic[r.nextInt(pic.length)]*/);
					imagelong[j].setLayoutParams(new GridView.LayoutParams(70,70));
					imagelong[j].setScaleType(ImageView.ScaleType.CENTER);
					imagelong[j].setPadding(3,3,3,3);	
					imagelong[j].setX(posXlong);
					imagelong[j].setY(posYlong);
					imagelong[j].setAdjustViewBounds(true);
					imagelong[j].startAnimation(animation[R.nextInt(4)]/*animComplex*/);
					mainlayout.addView(imagelong[j]);

					pPosXlong=posXlong;
					pPosYlong=posYlong;
					System.out.println("ImageViewlongID ="+imagelong[j].getId());
				}
				else
				{ 
					Toast.makeText(getApplicationContext(), "crossing dimensionsLong", Toast.LENGTH_SHORT).show();
					posXlong=posXlong-((posXlong-displaywidthlong)+r.nextInt(50));
					posYlong=posYlong-((posYlong-displayheightlong)+r.nextInt(50));

					imagelong[j] = new ImageView(this);
					int PicIDlong=r.nextInt(pic.length)-1;;
					if(PicIDlong==-1)
					{
						PicIDlong++;
					}
					imagelong[j].setImageResource(pic[PicIDlong]);
					imagelong[j].setTag(PicIDlong);
					int tag = (Integer) imagelong[j].getTag();
					System.out.println("PiclongTagTask"+tag);
					imagelong[j].setLayoutParams(new GridView.LayoutParams(70,70));
					imagelong[j].setScaleType(ImageView.ScaleType.CENTER);
					imagelong[j].setPadding(3,3,3,3);	
					imagelong[j].setX(posXlong);
					imagelong[j].setY(posYlong);
					imagelong[j].setAdjustViewBounds(true);
					imagelong[j].startAnimation(animation[R.nextInt(4)]/*animComplex*/);
					mainlayout.addView(imagelong[j]);

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
					imagelong[j] = new ImageView(this);
					int PicIDlong=r.nextInt(pic.length)-1;
					if(PicIDlong==-1)
					{
						PicIDlong++;
					}
					imagelong[j].setImageResource(pic[PicIDlong]);
					imagelong[j].setTag(PicIDlong);
					int tag = (Integer) imagelong[j].getTag();
					System.out.println("PiclongTagTask"+tag);
					imagelong[j].setLayoutParams(new GridView.LayoutParams(70,70));
					imagelong[j].setScaleType(ImageView.ScaleType.CENTER);
					imagelong[j].setPadding(3,3,3,3);	
					imagelong[j].setX(posXlong);
					imagelong[j].setY(posYlong);
					imagelong[j].setAdjustViewBounds(true);
					imagelong[j].startAnimation(animation[R.nextInt(4)]/*animComplex*/);
					mainlayout.addView(imagelong[j]);

					pPosXlong=posXlong;
					pPosYlong=posYlong;
				}
				else
				{
					posXlong=posXlong-((posXlong-displaywidthlong)+r.nextInt(50));
					posYlong=posYlong-((posYlong-displayheightlong)+r.nextInt(50));

					imagelong[j] = new ImageView(this);
					int PicIDlong=r.nextInt(pic.length)-1;
					if(PicIDlong==-1)
					{
						PicIDlong++;
					}
					imagelong[j].setImageResource(pic[PicIDlong]);
					imagelong[j].setTag(PicIDlong);
					int tag = (Integer) imagelong[j].getTag();
					System.out.println("PiclongTagTask"+tag);
					imagelong[j].setLayoutParams(new GridView.LayoutParams(70,70));
					imagelong[j].setScaleType(ImageView.ScaleType.CENTER);
					imagelong[j].setPadding(3,3,3,3);	
					imagelong[j].setX(posXlong);
					imagelong[j].setY(posYlong);
					imagelong[j].setAdjustViewBounds(true);
					imagelong[j].startAnimation(animation[R.nextInt(4)]/*animComplex*/);
					mainlayout.addView(imagelong[j]);

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
			//System.out.println("view"+view.getId()+""+"imageinview"+image.getId()+""+"layout"+layoutview.getId()+""+"layoutimage"+imagelong.getId());
			//if(view.getId() == image.getId() || layoutview.getId()==imagelong.getId())
			//{
			//System.out.println("view"+view.getId()+""+"imageinview"+image.getId()+""+"layout"+layoutview.getId()+""+"layoutimage"+imagelong.getId());
			if(view.getId()==layoutview.getId())
			{
				System.out.println("condition check");
				ViewGroup from = (ViewGroup) view.getParent();
				from.removeView(view);
				layoutview.setBackgroundResource(R.drawable.a10);
				return true;
			}else{
				Toast.makeText(getApplicationContext(), "wrong Drop", Toast.LENGTH_SHORT).show();
			}
			//}else{
			//return false;
			//}

		case DragEvent.ACTION_DRAG_ENDED:
			Log.d(LOGCAT, "Drag ended");
			break;
		default:
			break;
		}
		return true;

	}
	private boolean dragging = false;
	private Rect hitRect = new Rect();

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {

		System.out.println("Touched");
		boolean eventConsumed = true;
		int x = (int)event.getX();
		int y = (int)event.getY();
		int action = event.getAction();
		if (event.getAction() == MotionEvent.ACTION_DOWN) 
		{
			for (int i=0;i<100;i++)
			{
				if (v == image[i]){
					System.out.println("ivalue"+image[i]);
					tag1= (Integer) image[i].getTag();
					Toast.makeText(getApplicationContext(), "tag1 fetching= "+tag1, Toast.LENGTH_SHORT).show();
					k=i;
					dragging = true;
					eventConsumed = false;
				}
			}
		}
		else if (action == MotionEvent.ACTION_MOVE) {
			System.out.println("action moving");


		}
		else if (action == MotionEvent.ACTION_UP) 
		{
			System.out.println("000000000000000000000000000000000000");
			if (dragging) {
				for (int j=0;j<29;j++)
				{
					imagelong[j].getHitRect(hitRect);
					if (hitRect.contains(x, y)) {
						tag2=(Integer) imagelong[j].getTag();
						Toast.makeText(getApplicationContext(), "tag2 fetching= "+tag2, Toast.LENGTH_SHORT).show();

						if (tag1==tag2)
						{
							System.out.println("hello");
							imagelong[j].setImageDrawable(null);
							image[k].setImageDrawable(null);
							Toast.makeText(getApplicationContext(), "tag matched", Toast.LENGTH_SHORT).show();
							break;
						}else{
							System.out.println("toast");

						}

					}else{
						System.out.println("voice command");
					}
				}
			}
			dragging = false;
			eventConsumed = false;
		}
		else if (action == MotionEvent.ACTION_CANCEL) {

			System.out.println("ACTION_CANCEL");
		}

		return eventConsumed;
	}


	private void compare(ImageView imagelong, ImageView image) {
		// TODO Auto-generated method stub
		int imagelong_Tag=(Integer) imagelong.getTag();
		int image_Tag=(Integer) image.getTag();
		System.out.println("imagelong_id"+imagelong_Tag);
		System.out.println("image_id"+image_Tag);


	}
}

