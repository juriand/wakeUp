package com.example.wakeup;

import java.io.IOException;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.modifier.ScaleModifier;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.animator.SlideMenuAnimator;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.anddev.andengine.entity.scene.menu.item.TextMenuItem;
import org.anddev.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.BuildableTexture;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.builder.BlackPawnTextureBuilder;
import org.anddev.andengine.opengl.texture.builder.ITextureBuilder.TextureSourcePackingException;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainMenuActivity extends BaseGameActivity
{
	protected Handler mHandler;
	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;
	
	protected Camera mCamera;
	
	private BuildableTexture mMenuBackTexture;
	private TextureRegion mMenuBackTextureRegion;
	private TextureRegion mAboutBackTextureRegion;
	
	private TextureRegion mPlayRegion;
	private TextureRegion mAboutRegion;
	private TextureRegion mQuitRegion;
	private TextureRegion mLeftRegion;
	
	protected Scene mMainScene;
	protected Scene mAboutScene;
	
	static protected Music mMusic;
	private boolean musicPaused;	
	
	@Override
	public Engine onLoadEngine()
	{
		mHandler = new Handler();
		this.mCamera = new Camera(0,0,CAMERA_WIDTH,CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true,ScreenOrientation.LANDSCAPE,new RatioResolutionPolicy(CAMERA_WIDTH,CAMERA_HEIGHT),this.mCamera).setNeedsMusic(true));
	}
	
	@Override
	public void onLoadResources()
	{	
		//º”‘ÿ≤Àµ•±≥æ∞
		this.mMenuBackTexture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mMenuBackTextureRegion = TextureRegionFactory.createFromAsset(this.mMenuBackTexture,this,"images/title.png");
		this.mAboutBackTextureRegion = TextureRegionFactory.createFromAsset(this.mMenuBackTexture,this,"images/info.png");
		
		this.mAboutRegion = TextureRegionFactory.createFromAsset(this.mMenuBackTexture,this,"images/about.png");
		this.mPlayRegion = TextureRegionFactory.createFromAsset(this.mMenuBackTexture,this,"images/play.png");
		this.mQuitRegion = TextureRegionFactory.createFromAsset(this.mMenuBackTexture,this,"images/quit.png");
		this.mLeftRegion = TextureRegionFactory.createFromAsset(this.mMenuBackTexture,this,"images/left.png");
		this.mEngine.getTextureManager().loadTexture(this.mMenuBackTexture);
		
		try{
			mMenuBackTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d("menu","Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.mMenuBackTexture);
		
		MusicFactory.setAssetBasePath("mfx/");
		try{
			MainMenuActivity.mMusic = MusicFactory.createMusicFromAsset(this.mEngine.getMusicManager(),getApplicationContext(),"op.ogg");
			MainMenuActivity.mMusic.setLooping(true);
		}catch (final IOException e){
			Debug.e(e);
		}
		
	}
	
	@Override
	public Scene onLoadScene()
	{
		this.mEngine.registerUpdateHandler(new FPSLogger());
		
		this.mMainScene = new Scene(1);
		this.mAboutScene = new Scene(1);
		mMainScene.setBackground(new ColorBackground(1.0f,1.0f,1.0f));
		
		mMusic.play();
		
		final Sprite MainBack = new Sprite(0,0,mMenuBackTextureRegion);
		mMainScene.getLastChild().attachChild(MainBack);
		
		final Sprite AboutBack = new Sprite(0,0,mAboutBackTextureRegion);
		mAboutScene.getLastChild().attachChild(AboutBack);
		
		final Sprite Play = new Sprite(353,230,mPlayRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					mHandler.post(mLaunchGameTask);
					break;
				}
				return true;
			}		
		};
		mMainScene.registerTouchArea(Play);
		mMainScene.setTouchAreaBindingEnabled(true);
		mMainScene.getLastChild().attachChild(Play);
		
		final Sprite Quit = new Sprite(345,300,mQuitRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					finish();
					break;
				}
				return true;
			}		
		};
		mMainScene.registerTouchArea(Quit);
		mMainScene.setTouchAreaBindingEnabled(true);
		mMainScene.getLastChild().attachChild(Quit);
		
		final Sprite About = new Sprite(337,370,mAboutRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					mEngine.setScene(mAboutScene);
					break;
				}
				return true;
			}		
		};
		mMainScene.registerTouchArea(About);
		mMainScene.setTouchAreaBindingEnabled(true);
		mMainScene.getLastChild().attachChild(About);
		
		final Sprite Back = new Sprite(30,310,mLeftRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					mEngine.setScene(mMainScene);
					break;
				}
				return true;
			}		
		};
		mAboutScene.registerTouchArea(Back);
		mAboutScene.setTouchAreaBindingEnabled(true);
		mAboutScene.getLastChild().attachChild(Back);
	
		return mMainScene;	
	}
	
	@Override
	public void onLoadComplete()
	{

	}
	
	@Override
	protected void onPause() 
	{
		super.onPause();
		if(mMusic!=null)
		{
			mMusic.pause();
			musicPaused = true;
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(mMusic!=null && musicPaused)
		{
			mMusic.resume();
			musicPaused = false;
		}
	}
	
	private Runnable mLaunchGameTask = new Runnable(){
		public void run()
		{
			Intent myIntent = new Intent(MainMenuActivity.this,GameActivity.class);
			MainMenuActivity.this.startActivity(myIntent);
		}
	};
	
	
}