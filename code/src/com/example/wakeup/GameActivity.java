package com.example.wakeup;

import java.io.IOException;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
 
import org.anddev.andengine.engine.options.EngineOptions;
 
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
 
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
 
import org.anddev.andengine.entity.scene.Scene;
 
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
 
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.entity.util.FPSLogger;
 
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.BuildableTexture;
import org.anddev.andengine.opengl.texture.Texture;
 
import org.anddev.andengine.opengl.texture.TextureOptions;
 
import org.anddev.andengine.opengl.texture.builder.BlackPawnTextureBuilder;
import org.anddev.andengine.opengl.texture.builder.ITextureBuilder.TextureSourcePackingException;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
 
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
 
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;

public class GameActivity extends BaseGameActivity
{	
	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;
	private String tag = "Game";
	private int stage = 0;
		
	protected Camera mCamera;
	
	private int leave=0;
	
	//home
	private BuildableTexture BlackTexture;
	private BuildableTexture MessageTexture;
	private BuildableTexture ForestTexture;
	private BuildableTexture HomeTexture;
	private BuildableTexture GardenTexture;
	private BuildableTexture Fork2Texture;
	private BuildableTexture Fork1Texture;
	private BuildableTexture SuburbTexture;
	private BuildableTexture StoreTexture;
	private BuildableTexture PassageTexture;
	private BuildableTexture PubOutTexture;
	private BuildableTexture PubInTexture;
	private BuildableTexture FloristInTexture;
	private BuildableTexture FloristOutTexture;
	private BuildableTexture ParkTexture;
	private BuildableTexture ToolTexture;
	private BuildableTexture WordTexture;
	private BuildableTexture EndTexture;
	private BuildableTexture Pic1Texture;
	private BuildableTexture Pic2Texture;
	private BuildableTexture MosaicTexture;
	private BuildableTexture BloodEyeTexture;
	
	private TextureRegion mHomeRegion;
	private TiledTextureRegion mBoxRegion;
	private TiledTextureRegion mAidBoxRegion;
	private TiledTextureRegion mCupRegion;
	private TextureRegion mLetterRegion;
	private TextureRegion mDoorHomeRegion;
	private TextureRegion mDayOrNightRegion;
	private TextureRegion mBlackRegion;
	private TiledTextureRegion mMessageRegion;
	
	private boolean isSelect = false;
	private boolean isNight = false;
	private boolean isGetCup = false;
	private boolean cupSelect = false;
	private boolean isGetMed = false;
	private boolean medSelect = false;
	private boolean isGetHoe = false;
	private boolean hoeSelect = false;
	private boolean isGetKey1 = false;
	private boolean key1Select = false;
	
	private boolean isCupFull = false;
	
	private boolean seeMessage = false;

	
	private int boxNum =0;
	private int aidBoxNum =0;
	
	//Garden
	private TextureRegion mGardenRegion;
	private TiledTextureRegion mWoodRegion;
	private TiledTextureRegion mSoilRegion;
	private TextureRegion mWaterRegion;
	private TextureRegion mRainRegion;
	private TextureRegion mSunOrRainRegion;
	private TextureRegion mCropRegion;
	
	private boolean isRain = false;
	private boolean isWater = false;
	private boolean isGetSeed = false;
	private boolean seedSelect = false;
	private boolean isGetWood = false;
	private boolean woodSelect = false;
	
	private int soilNum = 0;
	
	//Fork2
	private TextureRegion mFork2Region;
	private TextureRegion mDoorFork2Region;
	
	//Suburb
	private TextureRegion mSuburbRegion;
	private TextureRegion mWarnRegion;
	private TextureRegion mJunkRegion;
	private TextureRegion mKnifeRegion;
	private TiledTextureRegion mPlayerRegion;
	private TiledTextureRegion mMatchRegion;
	
	private boolean disaster = false;
	private boolean needLeavePlayer = false;
	private boolean haveDiamond = false;
	private boolean isGetMatch = false;
	private boolean matchSelect = false;
	private boolean isGetDiamond = false;
	private boolean diamondSelect = false;
	private boolean isGetRing = false;
	private boolean ringSelect = false;
	private boolean isGetKnife = false;
	private boolean knifeSelect = false;
	
	private boolean seeKnife = false;
	private boolean seePlayer = true;

	
	//Fork1
	private TextureRegion mFork1Region;
	private TiledTextureRegion mBirdRegion;
	private TiledTextureRegion mEyeRegion;
	private TextureRegion mBirdDeadRegion;
	private TextureRegion mSeedRegion;
	
	private boolean birdDead=false;
	private boolean isGetCoin = false;
	private boolean coinSelect = false;
	
	private boolean seeCoin = false;
	private boolean seeBird = false;
	private boolean seeSeed = false;
	
	//PubOut
	private TextureRegion mPubOutRegion;
	private TiledTextureRegion mBeggarRegion;
	private TiledTextureRegion mBrickRegion;
	
	private boolean isGetBrick = false;
	private boolean brickSelect = false;
	
	private boolean seeBrick = false;
	private boolean seeBeggar = true;
	
	//PubIn
	private TextureRegion mPubInRegion;
	private TiledTextureRegion mStoveRegion;
	private TiledTextureRegion mCageRegion;
	private TiledTextureRegion mDrawerRegion;
	
	private boolean isFillWood = false;
	private boolean isBirdFree = false;
	private boolean isGetKey2 = false;
	private boolean key2Select = false;
	private boolean isDrawerOpen = false;
	
	//Store
	private TextureRegion mStoreRegion;
	private TiledTextureRegion mGlassRegion;
	private TiledTextureRegion mPickaxeRegion;
	private TextureRegion mRedWordRegion;
	
	private boolean isGetPickaxe = false;
	private boolean pickaxeSelect = false;
	private boolean isGlassBroken = false;
	private boolean seeRedWord = false;
	
	//Passage
	private TextureRegion mPassageRegion;
	private TiledTextureRegion mGirlRegion;
	private TextureRegion mCloudRegion;	
	
	//FloristOut
	private TextureRegion mFloristOutRegion;
	private TiledTextureRegion mOwnerRegion;
	private TiledTextureRegion mBrandRegion;
	private TextureRegion mDoorInFloristRegion;
	private TiledTextureRegion mLostRegion;
	
	private boolean floristOpen = true;
	
	//FloristIn
	private TiledTextureRegion mOwnerInRegion;
	private TextureRegion mFloristInRegion;
	private TextureRegion mBloodRegion;
	private TextureRegion mSickOwnerRegion;
	private TextureRegion mFlowerRegion;
	private TextureRegion mPhotoRegion;
	private TextureRegion mRedWordInRegion;
	
	private int num=0;
	private boolean returnRing=false;
	private boolean isGetFlower = false;
	private boolean flowerSelect = false;
	
	private boolean seeFlower = false;
	private boolean seeSickOwner = false;
	
	//Park
	private TextureRegion mParkRegion;
	private TiledTextureRegion mDogRegion;
	
	private int dogNum=0;
	private boolean isDogDead = false;
	private boolean isGetKey3 = false;
	private boolean key3Select = false;
	
	private boolean seeDog = false;
	
	//forest
	private TextureRegion mForestRegion;
	private int goUpNum=1;
	
	//end
	private TextureRegion mGoodRegion;
	private TextureRegion mBadRegion;
	
	//direction
	private TextureRegion mUpRegion;
	private TextureRegion mDownRegion;
	private TextureRegion mLeftRegion;
	private TextureRegion mRightRegion;
	private TextureRegion mLeftDownRegion;
	private TextureRegion mRightDownRegion;
	
	//tool
	private TiledTextureRegion mMedRegion;
	private TiledTextureRegion mSeed1Region;
	private TiledTextureRegion mHoeRegion;
	private TiledTextureRegion mCoin1Region;
	private TiledTextureRegion mDiamondRegion;
	private TiledTextureRegion mRingRegion;
	private TiledTextureRegion mKnife1Region;
	private TiledTextureRegion mFlower1Region;
	private TiledTextureRegion mKey1Region;
	private TiledTextureRegion mKey2Region;
	private TiledTextureRegion mKey3Region;
	private TiledTextureRegion mLaughRegion;
	
	private boolean seeMed = false;
	private boolean seeToolSeed = false;
	private boolean seeToolCoin = false;
	private boolean seeDiamond = false;
	private boolean seeRing = false;
	private boolean seeToolKnife = false;
	private boolean seeToolFlower = false;
	private boolean seeKey1 = false;
	private boolean seeKey2 = false;
	private boolean seeKey3 = false;
	private boolean seeMatch = false;
	private boolean seePickaxe = false;
	private boolean seeWood = false;
	
	private boolean goodEnd = false;
	private boolean badEnd = false;
	
	//word
	private TiledTextureRegion mWordRegion;
	
	//end
	private TextureRegion mMosaicRegion;
	private TextureRegion mBloodEyeRegion;
	private TextureRegion mPic1Region;
	private TextureRegion mPic2Region;
	private TextureRegion mPic3Region;
	private TextureRegion mPic4Region;
	
	//sound
	static protected Music rainMusic;
	static protected Music forestMusic;
	static protected Music noiseMusic;
	static protected Music backMusic;
	static protected Music floristMusic;
	
	private boolean playRainMusic=false;
	private boolean playForestMusic=false;
	private boolean playNoiseMusic=false;
	private boolean playBackMusic=false;
	private boolean playFloristMusic=false;
	private boolean playRedWordMusic=true;
	private boolean musicPaused=false;
	
	
	static protected Sound getSound;
	static protected Sound selectSound;
	static protected Sound openBoxSound;
	static protected Sound digHoleSound;
	static protected Sound drawerSound;
	static protected Sound fireSound;
	static protected Sound openCageSound;
	static protected Sound glassSound;
	static protected Sound dogSound;
	static protected Sound walkSound;
	static protected Sound doorSound;
	static protected Sound hoeSound;
	static protected Sound digSound;
	static protected Sound redWordSound;
	static protected Sound birdSound;
	static protected Sound bloodGirlSound;
	
	@Override
	public Engine onLoadEngine()
	{
		this.mCamera = new Camera(0,0, CAMERA_WIDTH,CAMERA_HEIGHT);
		final EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera);
        engineOptions.setNeedsMusic(true);
        engineOptions.setNeedsSound(true);
		return new Engine(engineOptions);
	}
	
	@Override
	public void onLoadResources()
	{		
		HomeTexture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		GardenTexture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Fork2Texture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Fork1Texture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		SuburbTexture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		PubInTexture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		PubOutTexture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		StoreTexture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		PassageTexture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		FloristInTexture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		FloristOutTexture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		ParkTexture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		ForestTexture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		MessageTexture = new BuildableTexture(4096,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);	
		BlackTexture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		ToolTexture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		EndTexture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Pic1Texture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Pic2Texture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		MosaicTexture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		BloodEyeTexture = new BuildableTexture(2048,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		
		WordTexture = new BuildableTexture(1024,2048,TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		//home
		mHomeRegion = TextureRegionFactory.createFromAsset(this.HomeTexture,this,"images/home.png");
		mBoxRegion = TextureRegionFactory.createTiledFromAsset(this.HomeTexture ,this, "images/box.png",3,1);
		mAidBoxRegion = TextureRegionFactory.createTiledFromAsset(this.HomeTexture ,this, "images/aidBox.png",3,1);
		mCupRegion = TextureRegionFactory.createTiledFromAsset(this.HomeTexture ,this, "images/cup.png",4,1);
		mLetterRegion = TextureRegionFactory.createFromAsset(this.HomeTexture ,this, "images/letter.png");
		mDoorHomeRegion = TextureRegionFactory.createFromAsset(this.HomeTexture ,this, "images/DoorHome.png");
		mDayOrNightRegion = TextureRegionFactory.createFromAsset(this.HomeTexture ,this, "images/dayOrNight.png");
		mBlackRegion = TextureRegionFactory.createFromAsset(this.BlackTexture ,this, "images/night.png");
		mMessageRegion = TextureRegionFactory.createTiledFromAsset(this.MessageTexture ,this, "images/message.png",3,1);
		
		//Garden
		mGardenRegion = TextureRegionFactory.createFromAsset(this.GardenTexture,this, "images/garden.png");
		mWoodRegion = TextureRegionFactory.createTiledFromAsset(this.GardenTexture,this, "images/wood.png",2,1);
		mSoilRegion = TextureRegionFactory.createTiledFromAsset(this.GardenTexture,this, "images/soil.png",2,1);
		mWaterRegion = TextureRegionFactory.createFromAsset(this.GardenTexture,this, "images/water.png");
		mRainRegion = TextureRegionFactory.createFromAsset(this.BlackTexture,this, "images/rain.png");
		mCropRegion = TextureRegionFactory.createFromAsset(this.GardenTexture,this, "images/crop.png");
		mSunOrRainRegion = TextureRegionFactory.createFromAsset(this.GardenTexture,this, "images/sunOrRain.png");
		
		//Fork2
		mFork2Region = TextureRegionFactory.createFromAsset(this.Fork2Texture,this, "images/fork2.png");
		mDoorFork2Region = TextureRegionFactory.createFromAsset(this.Fork2Texture,this, "images/DoorFork2.png");
		
		//Suburb
		mSuburbRegion = TextureRegionFactory.createFromAsset(this.SuburbTexture,this, "images/suburb.png");
		mWarnRegion = TextureRegionFactory.createFromAsset(this.SuburbTexture,this, "images/warn.png");
		mJunkRegion = TextureRegionFactory.createFromAsset(this.SuburbTexture,this, "images/junk.png");
		mKnifeRegion = TextureRegionFactory.createFromAsset(this.SuburbTexture,this, "images/knife.png");
		mPlayerRegion = TextureRegionFactory.createTiledFromAsset(this.SuburbTexture,this, "images/player.png",2,1);
		mMatchRegion = TextureRegionFactory.createTiledFromAsset(this.SuburbTexture,this, "images/match.png",2,1);
		
				
		//Fork1
		mFork1Region = TextureRegionFactory.createFromAsset(this.Fork1Texture,this, "images/fork1.png");
		mBirdRegion = TextureRegionFactory.createTiledFromAsset(this.Fork1Texture,this, "images/bird.png",4,1);
		mBirdDeadRegion = TextureRegionFactory.createFromAsset(this.Fork1Texture,this, "images/birdDead.png");
		mSeedRegion = TextureRegionFactory.createFromAsset(this.Fork1Texture,this, "images/seed.png");
		mEyeRegion = TextureRegionFactory.createTiledFromAsset(this.Fork1Texture,this, "images/eye.png",3,1);

		//PubOut
		mPubOutRegion = TextureRegionFactory.createFromAsset(this.PubOutTexture,this, "images/pubOut.png");
		mBeggarRegion = TextureRegionFactory.createTiledFromAsset(this.PubOutTexture,this, "images/beggar.png",3,1);
		mBrickRegion = TextureRegionFactory.createTiledFromAsset(this.PubOutTexture,this, "images/brick.png",2,1);
		
		//PubIn
		mPubInRegion = TextureRegionFactory.createFromAsset(this.PubInTexture,this, "images/pubIn.png");
		mStoveRegion = TextureRegionFactory.createTiledFromAsset(this.PubInTexture,this, "images/stove.png",3,1);
		mCageRegion = TextureRegionFactory.createTiledFromAsset(this.PubInTexture,this, "images/cage.png",2,1);
		mDrawerRegion = TextureRegionFactory.createTiledFromAsset(this.PubInTexture,this, "images/drawer.png",3,1);
		
		//Store
		mStoreRegion = TextureRegionFactory.createFromAsset(this.StoreTexture,this, "images/store.png");
		mGlassRegion = TextureRegionFactory.createTiledFromAsset(this.StoreTexture,this, "images/glass.png",2,1);
		mPickaxeRegion = TextureRegionFactory.createTiledFromAsset(this.StoreTexture,this, "images/pickaxe.png",2,1);
		mRedWordRegion = TextureRegionFactory.createFromAsset(this.StoreTexture,this, "images/redword.png");
		
		//Passage
		mPassageRegion = TextureRegionFactory.createFromAsset(this.PassageTexture,this, "images/passage.png");
		mGirlRegion = TextureRegionFactory.createTiledFromAsset(this.PassageTexture,this, "images/girl.png",3,1);
		mCloudRegion = TextureRegionFactory.createFromAsset(this.PassageTexture,this, "images/cloud.png");		
			
		//FloristOut
		mFloristOutRegion = TextureRegionFactory.createFromAsset(this.FloristOutTexture,this, "images/floristOut.png");
		mOwnerRegion = TextureRegionFactory.createTiledFromAsset(this.FloristOutTexture,this, "images/owner.png",2,1);
		mBrandRegion = TextureRegionFactory.createTiledFromAsset(this.FloristOutTexture,this, "images/brand.png",2,1);
		mLostRegion = TextureRegionFactory.createTiledFromAsset(this.FloristOutTexture,this, "images/lostandfound.png",2,1);
		mDoorInFloristRegion = TextureRegionFactory.createFromAsset(this.FloristOutTexture,this, "images/DoorFlorist.png");
		
		//FloristIn
		mFloristInRegion = TextureRegionFactory.createFromAsset(this.FloristInTexture,this, "images/floristIn.png");
		mBloodRegion = TextureRegionFactory.createFromAsset(this.FloristInTexture,this, "images/blood.png");
		mSickOwnerRegion = TextureRegionFactory.createFromAsset(this.FloristInTexture,this, "images/sickOwner.png");
		mFlowerRegion = TextureRegionFactory.createFromAsset(this.FloristInTexture,this, "images/flower.png");
		mPhotoRegion = TextureRegionFactory.createFromAsset(this.FloristInTexture,this, "images/photo.png");
		mRedWordInRegion = TextureRegionFactory.createFromAsset(this.FloristInTexture,this, "images/redWordIn.png");
		mOwnerInRegion = TextureRegionFactory.createTiledFromAsset(this.FloristInTexture,this, "images/ownerIn.png",2,1);
		
		//Park
		mParkRegion = TextureRegionFactory.createFromAsset(this.ParkTexture,this, "images/park.png");
		mDogRegion = TextureRegionFactory.createTiledFromAsset(this.ParkTexture,this, "images/dog.png",3,1);
		
		//forest
		mForestRegion = TextureRegionFactory.createFromAsset(this.ForestTexture,this, "images/forest.png");
		
		//end
		mGoodRegion = TextureRegionFactory.createFromAsset(this.EndTexture,this, "images/endGood.png");
		mBadRegion = TextureRegionFactory.createFromAsset(this.EndTexture,this, "images/endBad.png");
		mMosaicRegion = TextureRegionFactory.createFromAsset(this.MosaicTexture ,this, "images/mosaic.png");
		mBloodEyeRegion = TextureRegionFactory.createFromAsset(this.BloodEyeTexture ,this, "images/bloodEye.png");
		mPic1Region = TextureRegionFactory.createFromAsset(this.Pic1Texture ,this, "images/pic1.png");
		mPic2Region = TextureRegionFactory.createFromAsset(this.Pic1Texture ,this, "images/pic2.png");
		mPic3Region = TextureRegionFactory.createFromAsset(this.Pic2Texture ,this, "images/pic3.png");
		mPic4Region = TextureRegionFactory.createFromAsset(this.Pic2Texture ,this, "images/pic4.png");

		//direction
		mUpRegion = TextureRegionFactory.createFromAsset(this.ToolTexture,this, "images/up.png");
		mDownRegion = TextureRegionFactory.createFromAsset(this.ToolTexture,this, "images/down.png");
		mLeftRegion = TextureRegionFactory.createFromAsset(this.ToolTexture,this, "images/left.png");
		mRightRegion = TextureRegionFactory.createFromAsset(this.ToolTexture,this, "images/right.png");
		mLeftDownRegion = TextureRegionFactory.createFromAsset(this.ToolTexture,this, "images/leftDown.png");
		mRightDownRegion = TextureRegionFactory.createFromAsset(this.ToolTexture,this, "images/rightDown.png");
		
		//tool
		mMedRegion = TextureRegionFactory.createTiledFromAsset(this.ToolTexture,this, "images/medicine.png",2,1);
		mSeed1Region = TextureRegionFactory.createTiledFromAsset(this.ToolTexture,this, "images/seed1.png",2,1);
		mHoeRegion = TextureRegionFactory.createTiledFromAsset(this.ToolTexture,this, "images/hoe.png",2,1);
		mCoin1Region = TextureRegionFactory.createTiledFromAsset(this.ToolTexture,this, "images/coin1.png",4,1);
		mDiamondRegion = TextureRegionFactory.createTiledFromAsset(this.ToolTexture,this, "images/diamond.png",2,1);
		mRingRegion = TextureRegionFactory.createTiledFromAsset(this.ToolTexture,this, "images/ring.png",2,1);
		mKnife1Region = TextureRegionFactory.createTiledFromAsset(this.ToolTexture,this, "images/knife1.png",2,1);
		mFlower1Region = TextureRegionFactory.createTiledFromAsset(this.ToolTexture,this, "images/flower1.png",4,1);
		mKey1Region = TextureRegionFactory.createTiledFromAsset(this.ToolTexture,this, "images/key1.png",2,1);
		mKey2Region = TextureRegionFactory.createTiledFromAsset(this.ToolTexture,this, "images/key2.png",2,1);
		mKey3Region = TextureRegionFactory.createTiledFromAsset(this.ToolTexture,this, "images/key3.png",2,1);
		mLaughRegion = TextureRegionFactory.createTiledFromAsset(this.ToolTexture,this, "images/laugh.png",2,1);

		
		//word
		mWordRegion = TextureRegionFactory.createTiledFromAsset(this.WordTexture,this, "images/word.png",1,36);
		
		try{
			BlackTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.BlackTexture);
				
		try{
			WordTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.WordTexture);
		
		try{
			HomeTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.HomeTexture);
		
		try{
			GardenTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.GardenTexture);
		
		try{
			Fork1Texture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.Fork1Texture);
		
		try{
			Fork2Texture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.Fork2Texture);
		
		try{
			SuburbTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.SuburbTexture);
		
		try{
			PubOutTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.PubOutTexture);
		
		try{
			PubInTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.PubInTexture);
		
		try{
			StoreTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.StoreTexture);
		
		try{
			PassageTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.PassageTexture);
		
		try{
			ParkTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.ParkTexture);
		
		try{
			FloristInTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.FloristInTexture);
		
		try{
			FloristOutTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.FloristOutTexture);
		
		try{
			ToolTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.ToolTexture);
		
		try{
			MosaicTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.MosaicTexture);
		
		try{
			Pic1Texture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.Pic1Texture);
		
		try{
			Pic2Texture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.Pic2Texture);
		
		try{
			EndTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.EndTexture);
		
		try{
			BloodEyeTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.BloodEyeTexture);
		
		try{
			ForestTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.ForestTexture);
		
		try{
			MessageTexture.build(
					new BlackPawnTextureBuilder(2));
		}catch (final TextureSourcePackingException e){
			Log.d(tag,"Sprite won't fit");
		}
		this.mEngine.getTextureManager().loadTexture(this.MessageTexture);
				
		//sound
		SoundFactory.setAssetBasePath("mfx/");
		try{
			GameActivity.getSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), getApplicationContext(), "get.ogg");
			GameActivity.selectSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), getApplicationContext(), "select.ogg");
			GameActivity.openBoxSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), getApplicationContext(), "openBox.ogg");
			GameActivity.digHoleSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), getApplicationContext(), "digHole.ogg");
			GameActivity.drawerSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), getApplicationContext(), "drawer.ogg");
			GameActivity.openCageSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), getApplicationContext(), "openCage.ogg");
			GameActivity.fireSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), getApplicationContext(), "fire.ogg");
			GameActivity.glassSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), getApplicationContext(), "glass.ogg");
			GameActivity.dogSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), getApplicationContext(), "dog.ogg");
			GameActivity.walkSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), getApplicationContext(), "walk.ogg");
			GameActivity.doorSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), getApplicationContext(), "openDoor.ogg");
			GameActivity.hoeSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), getApplicationContext(), "hoeing.ogg");
			GameActivity.digSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), getApplicationContext(), "digHole.ogg");
			GameActivity.redWordSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), getApplicationContext(), "redWord.ogg");
			GameActivity.birdSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), getApplicationContext(), "bird.ogg");
			GameActivity.bloodGirlSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), getApplicationContext(), "bloodGirl.ogg");
		}catch(final IOException e){
			Debug.e(e);
		}
		
		//music
		MusicFactory.setAssetBasePath("mfx/");
		try{
			GameActivity.rainMusic = MusicFactory.createMusicFromAsset(this.mEngine.getMusicManager(),getApplicationContext(),"rain.ogg");
			GameActivity.rainMusic.setLooping(true);
			
			GameActivity.forestMusic = MusicFactory.createMusicFromAsset(this.mEngine.getMusicManager(),getApplicationContext(),"forest.ogg");
			GameActivity.forestMusic.setLooping(true);
			
			GameActivity.noiseMusic = MusicFactory.createMusicFromAsset(this.mEngine.getMusicManager(),getApplicationContext(),"noise.ogg");
			GameActivity.noiseMusic.setLooping(true);
			
			GameActivity.backMusic = MusicFactory.createMusicFromAsset(this.mEngine.getMusicManager(),getApplicationContext(),"background.ogg");
			GameActivity.backMusic.setLooping(true);
			
			GameActivity.floristMusic = MusicFactory.createMusicFromAsset(this.mEngine.getMusicManager(),getApplicationContext(),"bloodFlorist.ogg");
			GameActivity.floristMusic.setLooping(true);
		}catch (final IOException e){
			Debug.e(e);
		}
	}
	
	@Override
	public Scene onLoadScene()
	{		
		this.mEngine.registerUpdateHandler(new FPSLogger());
		final Scene home = new Scene(1);
		final Scene garden = new Scene(1);
		final Scene fork2 = new Scene(1);
		final Scene suburb = new Scene(1);
		final Scene fork1 = new Scene(1);
		final Scene pubOut = new Scene(1);
		final Scene pubIn = new Scene(1);
		final Scene store = new Scene(1);
		final Scene passage = new Scene(1);
		final Scene floristOut = new Scene(1);
		final Scene floristIn = new Scene(1);
		final Scene park = new Scene(1);
		final Scene forest = new Scene(1);
		final Scene endGood = new Scene(1);
		final Scene endBad = new Scene(1);
		
		//back
		backMusic.play();
		playBackMusic=true;
		
		final Sprite Home = new Sprite(0,0,mHomeRegion);
		home.getLastChild().attachChild(Home);
		
		final Sprite Garden = new Sprite(0,0,mGardenRegion);
		garden.getLastChild().attachChild(Garden);
		
		final Sprite Fork2 = new Sprite(0,0,mFork2Region);
		fork2.getLastChild().attachChild(Fork2);
		
		final Sprite Suburb = new Sprite(0,0,mSuburbRegion);
		suburb.getLastChild().attachChild(Suburb);
		
		final Sprite Fork1 = new Sprite(0,0,mFork1Region);
		fork1.getLastChild().attachChild(Fork1);
		
		final Sprite PubOut = new Sprite(0,0,mPubOutRegion);
		pubOut.getLastChild().attachChild(PubOut);
		
		final Sprite PubIn = new Sprite(0,0,mPubInRegion);
		pubIn.getLastChild().attachChild(PubIn);
		
		final Sprite Store = new Sprite(0,0,mStoreRegion);
		store.getLastChild().attachChild(Store);
		
		final Sprite Passage = new Sprite(0,0,mPassageRegion);
		passage.getLastChild().attachChild(Passage);
		
		final Sprite FloristOut = new Sprite(0,0,mFloristOutRegion);
		floristOut.getLastChild().attachChild(FloristOut);
		
		final Sprite FloristIn = new Sprite(0,0,mFloristInRegion);
		floristIn.getLastChild().attachChild(FloristIn);
		
		final Sprite Park = new Sprite(0,0,mParkRegion);
		park.getLastChild().attachChild(Park);
		
		final Sprite Forest = new Sprite(0,0,mForestRegion);
		forest.getLastChild().attachChild(Forest);
		
		final Sprite EndGood = new Sprite(0,0,mGoodRegion);
		endGood.getLastChild().attachChild(EndGood);
		
		final Sprite EndBad = new Sprite(0,0,mBadRegion);
		endBad.getLastChild().attachChild(EndBad);
		
		final Sprite Mosaic = new Sprite(0,0,mMosaicRegion);
		forest.getLastChild().attachChild(Mosaic);
		Mosaic.setVisible(false);
		
		final Sprite Pic1 = new Sprite(0,0,mPic1Region);
		forest.getLastChild().attachChild(Pic1);
		Pic1.setVisible(false);
		
		final Sprite Pic2 = new Sprite(0,0,mPic2Region);
		forest.getLastChild().attachChild(Pic2);
		Pic2.setVisible(false);
		
		final Sprite Pic3 = new Sprite(0,0,mPic3Region);
		forest.getLastChild().attachChild(Pic3);
		Pic3.setVisible(false);
		
		final Sprite Pic4 = new Sprite(0,0,mPic4Region);
		forest.getLastChild().attachChild(Pic4);
		Pic4.setVisible(false);
		
		final TiledSprite Word = new TiledSprite(80,445,mWordRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					this.setVisible(false);
					break;	
				}
				return true;
			}		
		};		
		home.registerTouchArea(Word);
		home.setTouchAreaBindingEnabled(true);
		home.getLastChild().attachChild(Word);
		
		garden.registerTouchArea(Word);
		garden.setTouchAreaBindingEnabled(true);
		garden.getLastChild().attachChild(Word);
		
		fork2.registerTouchArea(Word);
		fork2.setTouchAreaBindingEnabled(true);
		fork2.getLastChild().attachChild(Word);
		
		suburb.registerTouchArea(Word);
		suburb.setTouchAreaBindingEnabled(true);
		suburb.getLastChild().attachChild(Word);
		
		fork1.registerTouchArea(Word);
		fork1.setTouchAreaBindingEnabled(true);
		fork1.getLastChild().attachChild(Word);
		
		pubOut.registerTouchArea(Word);
		pubOut.setTouchAreaBindingEnabled(true);
		pubOut.getLastChild().attachChild(Word);
		
		pubIn.registerTouchArea(Word);
		pubIn.setTouchAreaBindingEnabled(true);
		pubIn.getLastChild().attachChild(Word);
		
		store.registerTouchArea(Word);
		store.setTouchAreaBindingEnabled(true);
		store.getLastChild().attachChild(Word);
		
		passage.registerTouchArea(Word);
		passage.setTouchAreaBindingEnabled(true);
		passage.getLastChild().attachChild(Word);
		
		floristOut.registerTouchArea(Word);
		floristOut.setTouchAreaBindingEnabled(true);
		floristOut.getLastChild().attachChild(Word);
		
		floristIn.registerTouchArea(Word);
		floristIn.setTouchAreaBindingEnabled(true);
		floristIn.getLastChild().attachChild(Word);
		
		park.registerTouchArea(Word);
		park.setTouchAreaBindingEnabled(true);
		park.getLastChild().attachChild(Word);
		
		forest.registerTouchArea(Word);
		forest.setTouchAreaBindingEnabled(true);
		forest.getLastChild().attachChild(Word);
		
		Word.setVisible(false);
		
		
		//tool
		final TiledSprite ToolMed = new TiledSprite(60,15,33,49,mMedRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isGetMed==true && seeMed==true)
					{
						if(medSelect==false && isSelect==false)
						{
							Word.setVisible(true);
							Word.setCurrentTileIndex(20);
							
							selectSound.play();
							this.setCurrentTileIndex(1);
							medSelect=true;
							isSelect=true;
						}
						else if(medSelect==true)
						{
							this.setCurrentTileIndex(0);
							medSelect=false;
							isSelect=false;
						}
					}
					break;	
				}
				return true;
			}		
		};
		home.registerTouchArea(ToolMed);
		home.setTouchAreaBindingEnabled(true);
		ToolMed.setVisible(false);
		home.getLastChild().attachChild(ToolMed);
		
		final TiledSprite ToolKey1 = new TiledSprite(650,15,49,54,mKey1Region){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isGetKey1==true && seeKey1==true)
					{
						if(key1Select==false && isSelect==false)
						{
							Word.setVisible(true);
							Word.setCurrentTileIndex(35);
							
							selectSound.play();
							this.setCurrentTileIndex(1);
							key1Select=true;
							isSelect=true;
						}
						else if(key1Select==true)
						{
							this.setCurrentTileIndex(0);
							key1Select=false;
							isSelect=false;
						}
					}
					break;	
				}
				return true;
			}		
		};
		home.registerTouchArea(ToolKey1);
		home.setTouchAreaBindingEnabled(true);
		ToolKey1.setVisible(false);
		home.getLastChild().attachChild(ToolKey1);
		
		final TiledSprite ToolKey2 = new TiledSprite(690,15,43,51,mKey2Region){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isGetKey2==true && seeKey2==true)
					{
						if(key2Select==false && isSelect==false)
						{
							Word.setVisible(true);
							Word.setCurrentTileIndex(35);
							
							selectSound.play();
							this.setCurrentTileIndex(1);
							key2Select=true;
							isSelect=true;
						}
						else if(key2Select==true)
						{
							this.setCurrentTileIndex(0);
							key2Select=false;
							isSelect=false;
						}
					}
					break;	
				}
				return true;
			}		
		};
		pubIn.registerTouchArea(ToolKey2);
		pubIn.setTouchAreaBindingEnabled(true);
		ToolKey2.setVisible(false);
		pubIn.getLastChild().attachChild(ToolKey2);

		final TiledSprite ToolSeed = new TiledSprite(105,15,58,32,mSeed1Region){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isGetSeed==true && seeToolSeed==true)
					{
						if(seedSelect==false && isSelect==false)
						{
							Word.setVisible(true);
							Word.setCurrentTileIndex(21);
							
							selectSound.play();
							this.setCurrentTileIndex(1);
							seedSelect=true;
							isSelect=true;
						}
						else if(seedSelect==true)
						{
							this.setCurrentTileIndex(0);
							seedSelect=false;
							isSelect=false;
						}
					}
					break;	
				}
				return true;
			}		
		};
		home.registerTouchArea(ToolSeed);
		home.setTouchAreaBindingEnabled(true);
		ToolSeed.setVisible(false);
		home.getLastChild().attachChild(ToolSeed);
	
		final TiledSprite ToolHoe = new TiledSprite(160,15,33,50,mHoeRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isGetHoe==true)
					{
						if(hoeSelect==false && isSelect==false)
						{
							Word.setVisible(true);
							Word.setCurrentTileIndex(22);
							
							selectSound.play();
							this.setCurrentTileIndex(1);
							hoeSelect=true;
							isSelect=true;
						}
						else if(hoeSelect==true)
						{
							this.setCurrentTileIndex(0);
							hoeSelect=false;
							isSelect=false;
						}
					}
					break;	
				}
				return true;
			}		
		};
		home.registerTouchArea(ToolHoe);
		home.setTouchAreaBindingEnabled(true);
		ToolHoe.setVisible(false);
		home.getLastChild().attachChild(ToolHoe);
		
		final TiledSprite ToolDiamond = new TiledSprite(360,15,33,41,mDiamondRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isGetDiamond==true && seeDiamond==true)
					{
						if(diamondSelect==false && isSelect==false)
						{
							Word.setVisible(true);
							Word.setCurrentTileIndex(28);
							
							selectSound.play();
							this.setCurrentTileIndex(1);
							diamondSelect=true;
							isSelect=true;
						}
						else if(diamondSelect==true)
						{
							this.setCurrentTileIndex(0);
							diamondSelect=false;
							isSelect=false;
						}
					}
					break;	
				}
				return true;
			}		
		};
		home.registerTouchArea(ToolDiamond);
		home.setTouchAreaBindingEnabled(true);
		ToolDiamond.setVisible(false);
		home.getLastChild().attachChild(ToolDiamond);
		
		final TiledSprite ToolRing = new TiledSprite(410,15,25,33,mRingRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isGetRing==true && seeRing==true)
					{
						if(ringSelect==false && isSelect==false)
						{
							Word.setVisible(true);
							Word.setCurrentTileIndex(29);
							
							selectSound.play();
							this.setCurrentTileIndex(1);
							ringSelect=true;
							isSelect=true;
						}
						else if(ringSelect==true)
						{
							this.setCurrentTileIndex(0);
							ringSelect=false;
							isSelect=false;
						}
					}
					break;	
				}
				return true;
			}		
		};
		home.registerTouchArea(ToolRing);
		home.setTouchAreaBindingEnabled(true);
		ToolRing.setVisible(false);
		home.getLastChild().attachChild(ToolRing);
		
		final TiledSprite ToolFlower = new TiledSprite(610,10,73,60,mFlower1Region){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isGetFlower==true && seeToolFlower==true)
					{
						if(flowerSelect==false && isSelect==false)
						{
							selectSound.play();
							if(disaster==true)
							{
								Word.setVisible(true);
								Word.setCurrentTileIndex(31);
								this.setCurrentTileIndex(3);
							}
							else
							{
								Word.setVisible(true);
								Word.setCurrentTileIndex(30);
								this.setCurrentTileIndex(2);
							}
							flowerSelect=true;
							isSelect=true;
						}
						else if(flowerSelect==true)
						{
							if(disaster==true)
								this.setCurrentTileIndex(1);
							else
								this.setCurrentTileIndex(0);
							flowerSelect=false;
							isSelect=false;
						}
					}
					break;	
				}
				return true;
			}		
		};
		home.registerTouchArea(ToolFlower);
		home.setTouchAreaBindingEnabled(true);
		ToolFlower.setVisible(false);
		home.getLastChild().attachChild(ToolFlower);
		
		final TiledSprite ToolLaugh = new TiledSprite(750,15,54,56,mLaughRegion);
		ToolLaugh.setVisible(false);
		home.getLastChild().attachChild(ToolLaugh);
		
		final TiledSprite ToolKnife = new TiledSprite(510,10,40,60,mKnife1Region){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isGetKnife==true && seeToolKnife==true)
					{
						if(knifeSelect==false && isSelect==false)
						{
							Word.setVisible(true);
							Word.setCurrentTileIndex(34);
							
							selectSound.play();
							this.setCurrentTileIndex(1);
							knifeSelect=true;
							isSelect=true;
						}
						else if(knifeSelect==true)
						{
							this.setCurrentTileIndex(0);
							knifeSelect=false;
							isSelect=false;
						}
					}
					break;	
				}
				return true;
			}		
		};
		home.registerTouchArea(ToolKnife);
		home.setTouchAreaBindingEnabled(true);
		ToolKnife.setVisible(false);
		home.getLastChild().attachChild(ToolKnife);
		
		final TiledSprite ToolKey3 = new TiledSprite(730,15,39,52,mKey3Region){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isGetKey3==true && seeKey3==true)
					{
						if(key3Select==false && isSelect==false)
						{
							Word.setVisible(true);
							Word.setCurrentTileIndex(35);
							
							selectSound.play();
							this.setCurrentTileIndex(1);
							key3Select=true;
							isSelect=true;
						}
						else if(key3Select==true)
						{
							this.setCurrentTileIndex(0);
							key3Select=false;
							isSelect=false;
						}
					}
					break;	
				}
				return true;
			}		
		};
		home.registerTouchArea(ToolKey3);
		home.setTouchAreaBindingEnabled(true);
		ToolKey3.setVisible(false);
		home.getLastChild().attachChild(ToolKey3);
			
		//home	
		final TiledSprite Cup = new TiledSprite(105,330,37,41,mCupRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isGetCup==true)
					{
						if(cupSelect==false && isSelect==false)
						{						
							selectSound.play();
							if(isCupFull==true)
							{
								this.setCurrentTileIndex(3);
								Word.setVisible(true);
								Word.setCurrentTileIndex(17);
							}
							else
							{
								this.setCurrentTileIndex(2);
								Word.setVisible(true);
								Word.setCurrentTileIndex(19);
							}
							cupSelect=true;
							isSelect=true;
						}
						else if(cupSelect==true)
						{
							if(isCupFull==true)
								this.setCurrentTileIndex(1);
							else
								this.setCurrentTileIndex(0);
							cupSelect=false;
							isSelect=false;
						}
					}
					else if(stage==0)
					{
						getSound.play();
						if(isGetCup==false)
							this.setPosition(10, 20);
						isGetCup=true;
						//join scene
						garden.registerTouchArea(this);
						garden.setTouchAreaBindingEnabled(true);
						garden.getLastChild().attachChild(this);
						
						fork2.registerTouchArea(this);
						fork2.setTouchAreaBindingEnabled(true);
						fork2.getLastChild().attachChild(this);
						
						suburb.registerTouchArea(this);
						suburb.setTouchAreaBindingEnabled(true);
						suburb.getLastChild().attachChild(this);
						
						fork1.registerTouchArea(this);
						fork1.setTouchAreaBindingEnabled(true);
						fork1.getLastChild().attachChild(this);
						
						pubOut.registerTouchArea(this);
						pubOut.setTouchAreaBindingEnabled(true);
						pubOut.getLastChild().attachChild(this);
						
						pubIn.registerTouchArea(this);
						pubIn.setTouchAreaBindingEnabled(true);
						pubIn.getLastChild().attachChild(this);
						
						store.registerTouchArea(this);
						store.setTouchAreaBindingEnabled(true);
						store.getLastChild().attachChild(this);
						
						passage.registerTouchArea(this);
						passage.setTouchAreaBindingEnabled(true);
						passage.getLastChild().attachChild(this);
						
						floristOut.registerTouchArea(this);
						floristOut.setTouchAreaBindingEnabled(true);
						floristOut.getLastChild().attachChild(this);
						
						floristIn.registerTouchArea(this);
						floristIn.setTouchAreaBindingEnabled(true);
						floristIn.getLastChild().attachChild(this);
						
						park.registerTouchArea(this);
						park.setTouchAreaBindingEnabled(true);
						park.getLastChild().attachChild(this);
					}
					break;	
				}
				return true;
			}		
		};
		home.registerTouchArea(Cup);
		home.setTouchAreaBindingEnabled(true);
		home.getLastChild().attachChild(Cup);
		
		final TiledSprite Box = new TiledSprite(530,155,139,189, mBoxRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					boxNum++;
					if(boxNum>=2)
					{
						if(isGetHoe==false)
						{
							getSound.play();
							ToolHoe.setVisible(true);
							isGetHoe=true;
							
							//join scene
							garden.registerTouchArea(ToolHoe);
							garden.setTouchAreaBindingEnabled(true);
							garden.getLastChild().attachChild(ToolHoe);
							
							fork2.registerTouchArea(ToolHoe);
							fork2.setTouchAreaBindingEnabled(true);
							fork2.getLastChild().attachChild(ToolHoe);
							
							suburb.registerTouchArea(ToolHoe);
							suburb.setTouchAreaBindingEnabled(true);
							suburb.getLastChild().attachChild(ToolHoe);
							
							fork1.registerTouchArea(ToolHoe);
							fork1.setTouchAreaBindingEnabled(true);
							fork1.getLastChild().attachChild(ToolHoe);
							
							pubOut.registerTouchArea(ToolHoe);
							pubOut.setTouchAreaBindingEnabled(true);
							pubOut.getLastChild().attachChild(ToolHoe);
							
							pubIn.registerTouchArea(ToolHoe);
							pubIn.setTouchAreaBindingEnabled(true);
							pubIn.getLastChild().attachChild(ToolHoe);
							
							store.registerTouchArea(ToolHoe);
							store.setTouchAreaBindingEnabled(true);
							store.getLastChild().attachChild(ToolHoe);
							
							passage.registerTouchArea(ToolHoe);
							passage.setTouchAreaBindingEnabled(true);
							passage.getLastChild().attachChild(ToolHoe);
							
							floristOut.registerTouchArea(ToolHoe);
							floristOut.setTouchAreaBindingEnabled(true);
							floristOut.getLastChild().attachChild(ToolHoe);
							
							floristIn.registerTouchArea(ToolHoe);
							floristIn.setTouchAreaBindingEnabled(true);
							floristIn.getLastChild().attachChild(ToolHoe);
							
							park.registerTouchArea(ToolHoe);
							park.setTouchAreaBindingEnabled(true);
							park.getLastChild().attachChild(ToolHoe);
						}
					}
					if(boxNum>=3)
					{
						this.setCurrentTileIndex(2);
					}
					else
					{
						if(boxNum==1)
						{
							if(key1Select==true)
							{
								openBoxSound.play();
								isSelect=false;
								key1Select=false;
								ToolKey1.setVisible(false);
								seeKey1=false;
								this.nextTile();
							}
							else
							{
								boxNum--;
								Word.setVisible(true);
								Word.setCurrentTileIndex(1);
							}
						}
						else
							this.nextTile();
					}
					break;
				}
				return true;
			}
		};
		home.registerTouchArea(Box);
		home.setTouchAreaBindingEnabled(true);
		home.getLastChild().attachChild(Box);
		
		final TiledSprite AidBox = new TiledSprite(650,350,75,69, mAidBoxRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					aidBoxNum++;
					if(aidBoxNum==1)
						openBoxSound.play();
					if(aidBoxNum>=2)
					{
						if(isGetMed==false)
						{
							ToolMed.setVisible(true);
							isGetMed=true;
							seeMed=true;
							getSound.play();
							
							//join scene
							garden.registerTouchArea(ToolMed);
							garden.setTouchAreaBindingEnabled(true);
							garden.getLastChild().attachChild(ToolMed);
							
							fork2.registerTouchArea(ToolMed);
							fork2.setTouchAreaBindingEnabled(true);
							fork2.getLastChild().attachChild(ToolMed);
							
							suburb.registerTouchArea(ToolMed);
							suburb.setTouchAreaBindingEnabled(true);
							suburb.getLastChild().attachChild(ToolMed);
							
							fork1.registerTouchArea(ToolMed);
							fork1.setTouchAreaBindingEnabled(true);
							fork1.getLastChild().attachChild(ToolMed);
							
							pubOut.registerTouchArea(ToolMed);
							pubOut.setTouchAreaBindingEnabled(true);
							pubOut.getLastChild().attachChild(ToolMed);
							
							pubIn.registerTouchArea(ToolMed);
							pubIn.setTouchAreaBindingEnabled(true);
							pubIn.getLastChild().attachChild(ToolMed);
							
							store.registerTouchArea(ToolMed);
							store.setTouchAreaBindingEnabled(true);
							store.getLastChild().attachChild(ToolMed);
							
							passage.registerTouchArea(ToolMed);
							passage.setTouchAreaBindingEnabled(true);
							passage.getLastChild().attachChild(ToolMed);
							
							floristOut.registerTouchArea(ToolMed);
							floristOut.setTouchAreaBindingEnabled(true);
							floristOut.getLastChild().attachChild(ToolMed);
							
							floristIn.registerTouchArea(ToolMed);
							floristIn.setTouchAreaBindingEnabled(true);
							floristIn.getLastChild().attachChild(ToolMed);
							
							park.registerTouchArea(ToolMed);
							park.setTouchAreaBindingEnabled(true);
							park.getLastChild().attachChild(ToolMed);
						}

					}
					if(aidBoxNum>=3)
						this.setCurrentTileIndex(2);
					else
						this.nextTile();
					break;
				}
				return true;
			}
		};
		home.registerTouchArea(AidBox);
		home.setTouchAreaBindingEnabled(true);
		home.getLastChild().attachChild(AidBox);
		
		final Sprite DoorHome = new Sprite(362,117,mDoorHomeRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					doorSound.play();
					stage=2;
					mEngine.setScene(fork2);
					break;	
				}
				return true;
			}		
		};
		home.registerTouchArea(DoorHome);
		home.setTouchAreaBindingEnabled(true);
		home.getLastChild().attachChild(DoorHome);
		
		final Sprite HomeDown = new Sprite(360,380,mDownRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					stage=1;
					mEngine.setScene(garden);
					walkSound.play();
					if(isRain==true)
						rainMusic.play();
					break;
				}
				return true;
			}		
		};
		home.registerTouchArea(HomeDown);
		home.setTouchAreaBindingEnabled(true);
		home.getLastChild().attachChild(HomeDown);

		//garden		
		final TiledSprite Wood = new TiledSprite(60,284,21,39,mWoodRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isGetWood==true && seeWood==true)
					{
						if(woodSelect==false && isSelect==false)
						{
							Word.setVisible(true);
							Word.setCurrentTileIndex(32);
							
							selectSound.play();
							this.setCurrentTileIndex(1);
							woodSelect=true;
							isSelect=true;
						}
						else if(woodSelect==true)
						{
							this.setCurrentTileIndex(0);
							woodSelect=false;
							isSelect=false;
						}
					}
					else if(stage==1)
					{
						getSound.play();
						if(isGetWood==false)
							this.setPosition(460, 20);
						isGetWood=true;
						seeWood=true;
						
						//join scene
						home.registerTouchArea(this);
						home.setTouchAreaBindingEnabled(true);
						home.getLastChild().attachChild(this);
						
						fork2.registerTouchArea(this);
						fork2.setTouchAreaBindingEnabled(true);
						fork2.getLastChild().attachChild(this);
						
						suburb.registerTouchArea(this);
						suburb.setTouchAreaBindingEnabled(true);
						suburb.getLastChild().attachChild(this);
						
						fork1.registerTouchArea(this);
						fork1.setTouchAreaBindingEnabled(true);
						fork1.getLastChild().attachChild(this);
						
						pubOut.registerTouchArea(this);
						pubOut.setTouchAreaBindingEnabled(true);
						pubOut.getLastChild().attachChild(this);
						
						pubIn.registerTouchArea(this);
						pubIn.setTouchAreaBindingEnabled(true);
						pubIn.getLastChild().attachChild(this);
						
						store.registerTouchArea(this);
						store.setTouchAreaBindingEnabled(true);
						store.getLastChild().attachChild(this);
						
						passage.registerTouchArea(this);
						passage.setTouchAreaBindingEnabled(true);
						passage.getLastChild().attachChild(this);
						
						floristOut.registerTouchArea(this);
						floristOut.setTouchAreaBindingEnabled(true);
						floristOut.getLastChild().attachChild(this);
						
						floristIn.registerTouchArea(this);
						floristIn.setTouchAreaBindingEnabled(true);
						floristIn.getLastChild().attachChild(this);
						
						park.registerTouchArea(this);
						park.setTouchAreaBindingEnabled(true);
						park.getLastChild().attachChild(this);
					}
					break;
				}
				return true;
			}		
		};
		garden.registerTouchArea(Wood);
		garden.setTouchAreaBindingEnabled(true);
		garden.getLastChild().attachChild(Wood);
		
		final Sprite Water = new Sprite(280,250,mWaterRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(cupSelect==true && isWater==true)
					{
						Cup.setCurrentTileIndex(1);
						isCupFull=true;
						cupSelect=false;
						isSelect=false;
					}
					break;
				}
				return true;
			}		
		};
		garden.registerTouchArea(Water);
		garden.setTouchAreaBindingEnabled(true);
		Water.setVisible(false);
		garden.getLastChild().attachChild(Water);
		
		final Sprite Rain = new Sprite(0,0,mRainRegion);
		Rain.setVisible(false);
		garden.getLastChild().attachChild(Rain);
			
		final Sprite SunOrRain = new Sprite(20,100,mSunOrRainRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isRain==false)
					{
						Rain.setVisible(true);
						rainMusic.play();
						playRainMusic=true;
						isRain = true;
						isWater=true;
						Water.setVisible(true);
					}
					else if(isRain==true)
					{
						Rain.setVisible(false);
						rainMusic.pause();
						playRainMusic=false;
						isRain = false;
					}
					break;
				}
				return true;
			}
		};
		garden.registerTouchArea(SunOrRain);
		garden.setTouchAreaBindingEnabled(true);
		garden.getLastChild().attachChild(SunOrRain);
		
		final TiledSprite Soil = new TiledSprite(300,200,132,42,mSoilRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(soilNum>1)
						this.setCurrentTileIndex(1);
					else if(soilNum==1)
					{
						digSound.play();
						this.nextTile();
						soilNum++;
						getSound.play();
						isGetKey1=true;
						seeKey1=true;
						ToolKey1.setVisible(true);
						
						//join scene
						garden.registerTouchArea(ToolKey1);
						garden.setTouchAreaBindingEnabled(true);
						garden.getLastChild().attachChild(ToolKey1);
						
						fork2.registerTouchArea(ToolKey1);
						fork2.setTouchAreaBindingEnabled(true);
						fork2.getLastChild().attachChild(ToolKey1);
						
						suburb.registerTouchArea(ToolKey1);
						suburb.setTouchAreaBindingEnabled(true);
						suburb.getLastChild().attachChild(ToolKey1);
						
						fork1.registerTouchArea(ToolKey1);
						fork1.setTouchAreaBindingEnabled(true);
						fork1.getLastChild().attachChild(ToolKey1);
						
						pubOut.registerTouchArea(ToolKey1);
						pubOut.setTouchAreaBindingEnabled(true);
						pubOut.getLastChild().attachChild(ToolKey1);
						
						pubIn.registerTouchArea(ToolKey1);
						pubIn.setTouchAreaBindingEnabled(true);
						pubIn.getLastChild().attachChild(ToolKey1);
						
						store.registerTouchArea(ToolKey1);
						store.setTouchAreaBindingEnabled(true);
						store.getLastChild().attachChild(ToolKey1);
						
						passage.registerTouchArea(ToolKey1);
						passage.setTouchAreaBindingEnabled(true);
						passage.getLastChild().attachChild(ToolKey1);
						
						floristOut.registerTouchArea(ToolKey1);
						floristOut.setTouchAreaBindingEnabled(true);
						floristOut.getLastChild().attachChild(ToolKey1);
						
						floristIn.registerTouchArea(ToolKey1);
						floristIn.setTouchAreaBindingEnabled(true);
						floristIn.getLastChild().attachChild(ToolKey1);
						
						park.registerTouchArea(ToolKey1);
						park.setTouchAreaBindingEnabled(true);
						park.getLastChild().attachChild(ToolKey1);
					}
					if(cupSelect==true && soilNum==0 && isCupFull==true)
					{
						this.setVisible(true);
						soilNum++;
						Cup.setCurrentTileIndex(2);
						isCupFull=false;
					}
					break;
				}
				return true;
			}
		};
		garden.registerTouchArea(Soil);
		garden.setTouchAreaBindingEnabled(true);
		Soil.setVisible(false);
		garden.getLastChild().attachChild(Soil);
		
		final Sprite Crop = new Sprite(150,135,mCropRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(hoeSelect==true && isGetSeed==false)
					{
						getSound.play();
						hoeSound.play();
						isGetSeed=true;
						ToolSeed.setVisible(true);
						seeToolSeed=true;

						//join scene
						garden.registerTouchArea(ToolSeed);
						garden.setTouchAreaBindingEnabled(true);
						garden.getLastChild().attachChild(ToolSeed);
						
						fork2.registerTouchArea(ToolSeed);
						fork2.setTouchAreaBindingEnabled(true);
						fork2.getLastChild().attachChild(ToolSeed);
						
						suburb.registerTouchArea(ToolSeed);
						suburb.setTouchAreaBindingEnabled(true);
						suburb.getLastChild().attachChild(ToolSeed);
						
						fork1.registerTouchArea(ToolSeed);
						fork1.setTouchAreaBindingEnabled(true);
						fork1.getLastChild().attachChild(ToolSeed);
						
						pubOut.registerTouchArea(ToolSeed);
						pubOut.setTouchAreaBindingEnabled(true);
						pubOut.getLastChild().attachChild(ToolSeed);
						
						pubIn.registerTouchArea(ToolSeed);
						pubIn.setTouchAreaBindingEnabled(true);
						pubIn.getLastChild().attachChild(ToolSeed);
						
						store.registerTouchArea(ToolSeed);
						store.setTouchAreaBindingEnabled(true);
						store.getLastChild().attachChild(ToolSeed);
						
						passage.registerTouchArea(ToolSeed);
						passage.setTouchAreaBindingEnabled(true);
						passage.getLastChild().attachChild(ToolSeed);
						
						floristOut.registerTouchArea(ToolSeed);
						floristOut.setTouchAreaBindingEnabled(true);
						floristOut.getLastChild().attachChild(ToolSeed);
						
						floristIn.registerTouchArea(ToolSeed);
						floristIn.setTouchAreaBindingEnabled(true);
						floristIn.getLastChild().attachChild(ToolSeed);
						
						park.registerTouchArea(ToolSeed);
						park.setTouchAreaBindingEnabled(true);
						park.getLastChild().attachChild(ToolSeed);
					}
					else if(hoeSelect==false)
					{
						Word.setVisible(true);
						Word.setCurrentTileIndex(5);
					}
					break;
				}
				return true;
			}		
		};
		garden.registerTouchArea(Crop);
		garden.setTouchAreaBindingEnabled(true);
		garden.getLastChild().attachChild(Crop);
		
		final Sprite GardenDown = new Sprite(700,380,mRightDownRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					stage=0;
					mEngine.setScene(home);
					rainMusic.pause();
					playRainMusic=false;
					walkSound.play();
					break;
				}
				return true;
			}		
		};
		garden.registerTouchArea(GardenDown);
		garden.setTouchAreaBindingEnabled(true);
		garden.getLastChild().attachChild(GardenDown);
		
		//fork2		
		final Sprite DoorFork2 = new Sprite(285,284,mDoorFork2Region){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					doorSound.play();
					stage=0;
					mEngine.setScene(home);
					if(needLeavePlayer==true)
						leave++;
					break;
				}
				return true;
			}		
		};
		fork2.registerTouchArea(DoorFork2);
		fork2.setTouchAreaBindingEnabled(true);
		fork2.getLastChild().attachChild(DoorFork2);	
				
		//suburb
		final TiledSprite Pickaxe = new TiledSprite(120,200,32,37,mPickaxeRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isGetPickaxe==true && seePickaxe==true)
					{
						if(pickaxeSelect==false && isSelect==false)
						{
							if(birdDead==false)
							{
								Word.setVisible(true);
								Word.setCurrentTileIndex(26);
							}
							else
							{
								Word.setVisible(true);
								Word.setCurrentTileIndex(27);
							}
							selectSound.play();
							this.setCurrentTileIndex(1);
							pickaxeSelect=true;
							isSelect=true;
						}
						else if(pickaxeSelect==true)
						{
							this.setCurrentTileIndex(0);
							pickaxeSelect=false;
							isSelect=false;
						}
					}
					else if(isGlassBroken==true && stage==7)
					{
						getSound.play();
						if(isGetPickaxe==false)
							this.setPosition(310, 20);
						isGetPickaxe=true;
						seePickaxe=true;
						
						//join scene
						home.registerTouchArea(this);
						home.setTouchAreaBindingEnabled(true);
						home.getLastChild().attachChild(this);
						
						fork2.registerTouchArea(this);
						fork2.setTouchAreaBindingEnabled(true);
						fork2.getLastChild().attachChild(this);
						
						suburb.registerTouchArea(this);
						suburb.setTouchAreaBindingEnabled(true);
						suburb.getLastChild().attachChild(this);
						
						fork1.registerTouchArea(this);
						fork1.setTouchAreaBindingEnabled(true);
						fork1.getLastChild().attachChild(this);
						
						pubOut.registerTouchArea(this);
						pubOut.setTouchAreaBindingEnabled(true);
						pubOut.getLastChild().attachChild(this);
						
						pubIn.registerTouchArea(this);
						pubIn.setTouchAreaBindingEnabled(true);
						pubIn.getLastChild().attachChild(this);
						
						garden.registerTouchArea(this);
						garden.setTouchAreaBindingEnabled(true);
						garden.getLastChild().attachChild(this);
						
						passage.registerTouchArea(this);
						passage.setTouchAreaBindingEnabled(true);
						passage.getLastChild().attachChild(this);
						
						floristOut.registerTouchArea(this);
						floristOut.setTouchAreaBindingEnabled(true);
						floristOut.getLastChild().attachChild(this);
						
						floristIn.registerTouchArea(this);
						floristIn.setTouchAreaBindingEnabled(true);
						floristIn.getLastChild().attachChild(this);
						
						park.registerTouchArea(this);
						park.setTouchAreaBindingEnabled(true);
						park.getLastChild().attachChild(this);
					}
					break;
				}
				return true;
			}		
		};
		store.registerTouchArea(Pickaxe);
		store.setTouchAreaBindingEnabled(true);	
		store.getLastChild().attachChild(Pickaxe);
		
		final Sprite Warn = new Sprite(390,300,mWarnRegion);
		Warn.setVisible(false);
		suburb.getLastChild().attachChild(Warn);
		
		final Sprite Junk = new Sprite(520,210,mJunkRegion);
		Junk.setVisible(false);
		suburb.getLastChild().attachChild(Junk);
		
		final Sprite Knife = new Sprite(550,350,mKnifeRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(seeKnife==true)
					{
						this.setVisible(false);
						
						ToolKnife.setVisible(true);
						isGetKnife=true;
						seeToolKnife=true;
						getSound.play();
						seeDog=true;
						
						//join scene
						garden.registerTouchArea(ToolKnife);
						garden.setTouchAreaBindingEnabled(true);
						garden.getLastChild().attachChild(ToolKnife);
						
						fork2.registerTouchArea(ToolKnife);
						fork2.setTouchAreaBindingEnabled(true);
						fork2.getLastChild().attachChild(ToolKnife);
						
						suburb.registerTouchArea(ToolKnife);
						suburb.setTouchAreaBindingEnabled(true);
						suburb.getLastChild().attachChild(ToolKnife);
						
						fork1.registerTouchArea(ToolKnife);
						fork1.setTouchAreaBindingEnabled(true);
						fork1.getLastChild().attachChild(ToolKnife);
						
						pubOut.registerTouchArea(ToolKnife);
						pubOut.setTouchAreaBindingEnabled(true);
						pubOut.getLastChild().attachChild(ToolKnife);
						
						pubIn.registerTouchArea(ToolKnife);
						pubIn.setTouchAreaBindingEnabled(true);
						pubIn.getLastChild().attachChild(ToolKnife);
						
						store.registerTouchArea(ToolKnife);
						store.setTouchAreaBindingEnabled(true);
						store.getLastChild().attachChild(ToolKnife);
						
						passage.registerTouchArea(ToolKnife);
						passage.setTouchAreaBindingEnabled(true);
						passage.getLastChild().attachChild(ToolKnife);
						
						floristOut.registerTouchArea(ToolKnife);
						floristOut.setTouchAreaBindingEnabled(true);
						floristOut.getLastChild().attachChild(ToolKnife);
						
						floristIn.registerTouchArea(ToolKnife);
						floristIn.setTouchAreaBindingEnabled(true);
						floristIn.getLastChild().attachChild(ToolKnife);
						
						park.registerTouchArea(ToolKnife);
						park.setTouchAreaBindingEnabled(true);
						park.getLastChild().attachChild(ToolKnife);
					}
					break;
				}
				return true;
			}		
		};	
		suburb.registerTouchArea(Knife);
		suburb.setTouchAreaBindingEnabled(true);
		Knife.setVisible(false);
		suburb.getLastChild().attachChild(Knife);
		
		final TiledSprite Player = new TiledSprite(510,130,127,293,mPlayerRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(diamondSelect==true)
					{
						getSound.play();
						isGetRing=true;
						ToolRing.setVisible(true);
						seeRing=true;
						
						ToolDiamond.setVisible(false);
						diamondSelect=false;
						isSelect=false;	
						seeDiamond=false;
						
						//join scene
						garden.registerTouchArea(ToolRing);
						garden.setTouchAreaBindingEnabled(true);
						garden.getLastChild().attachChild(ToolRing);
						
						fork2.registerTouchArea(ToolRing);
						fork2.setTouchAreaBindingEnabled(true);
						fork2.getLastChild().attachChild(ToolRing);
						
						suburb.registerTouchArea(ToolRing);
						suburb.setTouchAreaBindingEnabled(true);
						suburb.getLastChild().attachChild(ToolRing);
						
						fork1.registerTouchArea(ToolRing);
						fork1.setTouchAreaBindingEnabled(true);
						fork1.getLastChild().attachChild(ToolRing);
						
						pubOut.registerTouchArea(ToolRing);
						pubOut.setTouchAreaBindingEnabled(true);
						pubOut.getLastChild().attachChild(ToolRing);
						
						pubIn.registerTouchArea(ToolDiamond);
						pubIn.setTouchAreaBindingEnabled(true);
						pubIn.getLastChild().attachChild(ToolDiamond);
						
						store.registerTouchArea(ToolRing);
						store.setTouchAreaBindingEnabled(true);
						store.getLastChild().attachChild(ToolRing);
						
						passage.registerTouchArea(ToolRing);
						passage.setTouchAreaBindingEnabled(true);
						passage.getLastChild().attachChild(ToolRing);
						
						floristOut.registerTouchArea(ToolRing);
						floristOut.setTouchAreaBindingEnabled(true);
						floristOut.getLastChild().attachChild(ToolRing);
						
						floristIn.registerTouchArea(ToolRing);
						floristIn.setTouchAreaBindingEnabled(true);
						floristIn.getLastChild().attachChild(ToolRing);
						
						park.registerTouchArea(ToolRing);
						park.setTouchAreaBindingEnabled(true);
						park.getLastChild().attachChild(ToolRing);
					}
					if(haveDiamond==true)
					{
						isGetDiamond=true;
						seeDiamond=true;
						getSound.play();
						ToolDiamond.setVisible(true);
						haveDiamond=false;
						needLeavePlayer=false;
						leave=0;

						//join scene
						garden.registerTouchArea(ToolDiamond);
						garden.setTouchAreaBindingEnabled(true);
						garden.getLastChild().attachChild(ToolDiamond);
						
						fork2.registerTouchArea(ToolDiamond);
						fork2.setTouchAreaBindingEnabled(true);
						fork2.getLastChild().attachChild(ToolDiamond);
						
						suburb.registerTouchArea(ToolDiamond);
						suburb.setTouchAreaBindingEnabled(true);
						suburb.getLastChild().attachChild(ToolDiamond);
						
						fork1.registerTouchArea(ToolDiamond);
						fork1.setTouchAreaBindingEnabled(true);
						fork1.getLastChild().attachChild(ToolDiamond);
						
						pubOut.registerTouchArea(ToolDiamond);
						pubOut.setTouchAreaBindingEnabled(true);
						pubOut.getLastChild().attachChild(ToolDiamond);
						
						pubIn.registerTouchArea(ToolDiamond);
						pubIn.setTouchAreaBindingEnabled(true);
						pubIn.getLastChild().attachChild(ToolDiamond);
						
						store.registerTouchArea(ToolDiamond);
						store.setTouchAreaBindingEnabled(true);
						store.getLastChild().attachChild(ToolDiamond);
						
						passage.registerTouchArea(ToolDiamond);
						passage.setTouchAreaBindingEnabled(true);
						passage.getLastChild().attachChild(ToolDiamond);
						
						floristOut.registerTouchArea(ToolDiamond);
						floristOut.setTouchAreaBindingEnabled(true);
						floristOut.getLastChild().attachChild(ToolDiamond);
						
						floristIn.registerTouchArea(ToolDiamond);
						floristIn.setTouchAreaBindingEnabled(true);
						floristIn.getLastChild().attachChild(ToolDiamond);
						
						park.registerTouchArea(ToolDiamond);
						park.setTouchAreaBindingEnabled(true);
						park.getLastChild().attachChild(ToolDiamond);
					}
					if(pickaxeSelect==true)
					{
						seePlayer=false;
						Pickaxe.setVisible(false);
						pickaxeSelect=false;
						isSelect=false;
						
						this.setVisible(false);
						if(birdDead!=true)
							needLeavePlayer=true;
						else
							disaster=true;
					}
					else if(seePlayer==true && isGetDiamond==false)
					{
						Word.setVisible(true);
						Word.setCurrentTileIndex(3);
					}
					break;
				}
				return true;
			}		
		};
		suburb.registerTouchArea(Player);
		suburb.setTouchAreaBindingEnabled(true);	
		suburb.getLastChild().attachChild(Player);
		
		final Sprite Fork2LeftDown = new Sprite(150,400,mLeftDownRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					if(disaster==true)
					{
						Junk.setVisible(true);
						Knife.setVisible(true);
						Warn.setVisible(true);
						seeRedWord=true;
						seeKnife=true;
					}
					if(needLeavePlayer==true)
						leave++;
					if(leave>=2)
					{
						seePlayer=true;
						Player.setVisible(true);
						Player.setCurrentTileIndex(1);
						haveDiamond=true;
					}
					stage=3;
					mEngine.setScene(suburb);
					walkSound.play();
					break;
				}
				return true;
			}		
		};
		fork2.registerTouchArea(Fork2LeftDown);
		fork2.setTouchAreaBindingEnabled(true);
		fork2.getLastChild().attachChild(Fork2LeftDown);
			
		final TiledSprite Match = new TiledSprite(150,360,mMatchRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isGetMatch==true && seeMatch==true)
					{
						if(matchSelect==false && isSelect==false)
						{
							Word.setVisible(true);
							Word.setCurrentTileIndex(33);
							
							selectSound.play();
							this.setCurrentTileIndex(1);
							matchSelect=true;
							isSelect=true;
						}
						else if(matchSelect==true)
						{
							this.setCurrentTileIndex(0);
							matchSelect=false;
							isSelect=false;
						}
					}
					else if(stage==3)
					{
						getSound.play();
						if(isGetMatch==false)
							this.setPosition(560,15);
						isGetMatch=true;
						seeMatch=true;
						
						//join scene
						home.registerTouchArea(this);
						home.setTouchAreaBindingEnabled(true);
						home.getLastChild().attachChild(this);
						
						fork2.registerTouchArea(this);
						fork2.setTouchAreaBindingEnabled(true);
						fork2.getLastChild().attachChild(this);
						
						garden.registerTouchArea(this);
						garden.setTouchAreaBindingEnabled(true);
						garden.getLastChild().attachChild(this);
						
						fork1.registerTouchArea(this);
						fork1.setTouchAreaBindingEnabled(true);
						fork1.getLastChild().attachChild(this);
						
						pubOut.registerTouchArea(this);
						pubOut.setTouchAreaBindingEnabled(true);
						pubOut.getLastChild().attachChild(this);
						
						pubIn.registerTouchArea(this);
						pubIn.setTouchAreaBindingEnabled(true);
						pubIn.getLastChild().attachChild(this);
						
						store.registerTouchArea(this);
						store.setTouchAreaBindingEnabled(true);
						store.getLastChild().attachChild(this);
						
						passage.registerTouchArea(this);
						passage.setTouchAreaBindingEnabled(true);
						passage.getLastChild().attachChild(this);
						
						floristOut.registerTouchArea(this);
						floristOut.setTouchAreaBindingEnabled(true);
						floristOut.getLastChild().attachChild(this);
						
						floristIn.registerTouchArea(this);
						floristIn.setTouchAreaBindingEnabled(true);
						floristIn.getLastChild().attachChild(this);
						
						park.registerTouchArea(this);
						park.setTouchAreaBindingEnabled(true);
						park.getLastChild().attachChild(this);
					}
					break;
				}
				return true;
			}		
		};
		suburb.registerTouchArea(Match);
		suburb.setTouchAreaBindingEnabled(true);
		suburb.getLastChild().attachChild(Match);
				
		final Sprite SuburbRight = new Sprite(650,400,mRightRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					stage=4;
					mEngine.setScene(fork2);
					walkSound.play();
					break;
				}
				return true;
			}		
		};
		suburb.registerTouchArea(SuburbRight);
		suburb.setTouchAreaBindingEnabled(true);
		suburb.getLastChild().attachChild(SuburbRight);
		
		//fork1	
		final Sprite Fork1Up = new Sprite(350,200,mUpRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					if(goodEnd==true || badEnd==true)
					{
						mEngine.setScene(forest);
						walkSound.play();
						
						backMusic.pause();
						playBackMusic=false;
						
						forestMusic.play();
						playForestMusic=true;
					}
					break;
				}
				return true;
			}		
		};
		fork1.registerTouchArea(Fork1Up);
		fork1.setTouchAreaBindingEnabled(true);
		Fork1Up.setVisible(false);
		fork1.getLastChild().attachChild(Fork1Up);
		
		final AnimatedSprite Eye1 = new AnimatedSprite(140,110,37,27,mEyeRegion);
		fork1.getLastChild().attachChild(Eye1);
		Eye1.animate(new long[] {200,100,50},0,2,true);
		
		final AnimatedSprite Eye2 = new AnimatedSprite(460,100,37,27,mEyeRegion);
		fork1.getLastChild().attachChild(Eye2);
		Eye2.animate(new long[] {200,100,50},0,2,true);
		
		final AnimatedSprite Eye3 = new AnimatedSprite(630,150,37,27,mEyeRegion);
		fork1.getLastChild().attachChild(Eye3);
		Eye3.animate(new long[] {200,100,50},0,2,true);
		
		final Sprite BirdDead = new Sprite(350,400,mBirdDeadRegion);
		fork1.getLastChild().attachChild(BirdDead);
		BirdDead.setVisible(false);
		
		final Sprite Seed = new Sprite(340,410,mSeedRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(seedSelect==true)
					{
						this.setVisible(true);
						
						ToolSeed.setVisible(false);
						seeToolSeed=false;
						seedSelect=false;
						isSelect=false;					
						seeSeed=true;
					}
					break;
				}
				return true;
			}		
		};		
		fork1.registerTouchArea(Seed);
		fork1.setTouchAreaBindingEnabled(true);	
		Seed.setVisible(false);
		fork1.getLastChild().attachChild(Seed);
		
		final TiledSprite Coin = new TiledSprite(300,380,29,18,mCoin1Region){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isGetCoin==true && seeToolCoin==true)
					{
						if(coinSelect==false && isSelect==false)
						{
							selectSound.play();
							if(birdDead==true)
							{
								Word.setVisible(true);
								Word.setCurrentTileIndex(24);
								
								this.setCurrentTileIndex(3);
							}
							else
							{
								Word.setVisible(true);
								Word.setCurrentTileIndex(23);
								
								this.setCurrentTileIndex(2);
							}
							coinSelect=true;
							isSelect=true;
						}
						else if(coinSelect==true)
						{
							if(birdDead==true)
								this.setCurrentTileIndex(1);
							else
								this.setCurrentTileIndex(0);
							coinSelect=false;
							isSelect=false;
						}
					}
					else if(seeCoin==true && isGetCoin==false)
					{
						getSound.play();
						this.setPosition(210, 25);
						isGetCoin=true;
						seeCoin=false;
						seeToolCoin=true;
						
						//join scene
						home.registerTouchArea(this);
						home.setTouchAreaBindingEnabled(true);
						home.getLastChild().attachChild(this);
						
						garden.registerTouchArea(this);
						garden.setTouchAreaBindingEnabled(true);
						garden.getLastChild().attachChild(this);
						
						fork2.registerTouchArea(this);
						fork2.setTouchAreaBindingEnabled(true);
						fork2.getLastChild().attachChild(this);
						
						suburb.registerTouchArea(this);
						suburb.setTouchAreaBindingEnabled(true);
						suburb.getLastChild().attachChild(this);
						
						pubOut.registerTouchArea(this);
						pubOut.setTouchAreaBindingEnabled(true);
						pubOut.getLastChild().attachChild(this);
						
						pubIn.registerTouchArea(this);
						pubIn.setTouchAreaBindingEnabled(true);
						pubIn.getLastChild().attachChild(this);
						
						store.registerTouchArea(this);
						store.setTouchAreaBindingEnabled(true);
						store.getLastChild().attachChild(this);
						
						passage.registerTouchArea(this);
						passage.setTouchAreaBindingEnabled(true);
						passage.getLastChild().attachChild(this);
						
						floristOut.registerTouchArea(this);
						floristOut.setTouchAreaBindingEnabled(true);
						floristOut.getLastChild().attachChild(this);
						
						floristIn.registerTouchArea(this);
						floristIn.setTouchAreaBindingEnabled(true);
						floristIn.getLastChild().attachChild(this);
						
						park.registerTouchArea(this);
						park.setTouchAreaBindingEnabled(true);
						park.getLastChild().attachChild(this);
					}										
					break;
				}
				return true;
			}		
		};
		fork1.registerTouchArea(Coin);
		fork1.setTouchAreaBindingEnabled(true);	
		Coin.setVisible(false);
		fork1.getLastChild().attachChild(Coin);
		
		final TiledSprite ForkBird = new TiledSprite(350,80,62,39,mBirdRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(hoeSelect==true && seeBird==true)
					{
						Word.setVisible(true);
						Word.setCurrentTileIndex(14);
						
						seeCoin=true;
						Coin.setVisible(true);
						Coin.setCurrentTileIndex(1);
						
						this.setVisible(false);
						birdDead=true;
						BirdDead.setVisible(true);
						seeBird=false;
					}
					break;
				}
				return true;
			}		
		};
		fork1.registerTouchArea(ForkBird);
		fork1.setTouchAreaBindingEnabled(true);
		ForkBird.setVisible(false);
		fork1.getLastChild().attachChild(ForkBird);
		
		final Sprite Fork1LeftDown = new Sprite(100,400,mLeftDownRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					stage=2;
					mEngine.setScene(fork2);
					walkSound.play();
					if(seeSeed==true && seeBird==true)
					{
						Seed.setVisible(false);
						ForkBird.setVisible(false);
						Coin.setVisible(true);
						seeCoin=true;
						seeSeed=false;
						seeBird=false;
					}
					break;
				}
				return true;
			}		
		};
		fork1.registerTouchArea(Fork1LeftDown);
		fork1.setTouchAreaBindingEnabled(true);
		fork1.getLastChild().attachChild(Fork1LeftDown);
		
		//PubOut
		final TiledSprite Beggar = new TiledSprite(57,210,118,203,mBeggarRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(coinSelect==true)
					{
						if(birdDead==true)
							this.setCurrentTileIndex(2);
						else
							this.setCurrentTileIndex(1);
						
						Coin.setVisible(false);
						coinSelect=false;
						isSelect=false;
						seeBrick=true;
						seeCoin=false;
						seeToolCoin=false;
					}
					else if(seeBeggar==true)
					{
						Word.setVisible(true);
						Word.setCurrentTileIndex(7);
					}				
					break;
				}
				return true;
			}		
		};
		pubOut.registerTouchArea(Beggar);
		pubOut.setTouchAreaBindingEnabled(true);
		pubOut.getLastChild().attachChild(Beggar);		
		
		final TiledSprite Brick = new TiledSprite(80,400,34,43,mBrickRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isGetBrick==true && seeBrick==true)
					{
						if(brickSelect==false && isSelect==false)
						{
							Word.setVisible(true);
							Word.setCurrentTileIndex(25);
							
							selectSound.play();
							this.setCurrentTileIndex(1);
							brickSelect=true;
							isSelect=true;
							seeBrick=true;
						}
						else if(brickSelect==true)
						{
							this.setCurrentTileIndex(0);
							brickSelect=false;
							isSelect=false;
						}
					}
					else if(seeBrick==true)
					{
						getSound.play();
						if(isGetBrick==false)
							this.setPosition(260, 15);
						isGetBrick=true;
						
						//join scene
						garden.registerTouchArea(this);
						garden.setTouchAreaBindingEnabled(true);
						garden.getLastChild().attachChild(this);
						
						fork2.registerTouchArea(this);
						fork2.setTouchAreaBindingEnabled(true);
						fork2.getLastChild().attachChild(this);
						
						suburb.registerTouchArea(this);
						suburb.setTouchAreaBindingEnabled(true);
						suburb.getLastChild().attachChild(this);
						
						fork1.registerTouchArea(this);
						fork1.setTouchAreaBindingEnabled(true);
						fork1.getLastChild().attachChild(this);
						
						home.registerTouchArea(this);
						home.setTouchAreaBindingEnabled(true);
						home.getLastChild().attachChild(this);
						
						pubIn.registerTouchArea(this);
						pubIn.setTouchAreaBindingEnabled(true);
						pubIn.getLastChild().attachChild(this);
						
						store.registerTouchArea(this);
						store.setTouchAreaBindingEnabled(true);
						store.getLastChild().attachChild(this);
						
						passage.registerTouchArea(this);
						passage.setTouchAreaBindingEnabled(true);
						passage.getLastChild().attachChild(this);
						
						floristOut.registerTouchArea(this);
						floristOut.setTouchAreaBindingEnabled(true);
						floristOut.getLastChild().attachChild(this);
						
						floristIn.registerTouchArea(this);
						floristIn.setTouchAreaBindingEnabled(true);
						floristIn.getLastChild().attachChild(this);
						
						park.registerTouchArea(this);
						park.setTouchAreaBindingEnabled(true);
						park.getLastChild().attachChild(this);
					}
					break;
				}
				return true;
			}		
		};
		pubOut.registerTouchArea(Brick);
		pubOut.setTouchAreaBindingEnabled(true);
		Brick.setVisible(false);
		pubOut.getLastChild().attachChild(Brick);
		
		final Sprite Fork1RightDown = new Sprite(500,400,mRightDownRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					stage=5;
					mEngine.setScene(pubOut);
					walkSound.play();
					if(seeBrick==true)
					{
						Brick.setVisible(true);
						Beggar.setVisible(false);
						seeBeggar=false;
					}
					if(seeSeed==true && seeBird==true)
					{
						Seed.setVisible(false);
						ForkBird.setVisible(false);
						Coin.setVisible(true);
						seeCoin=true;
						seeSeed=false;
						seeBird=false;
					}
					break;
				}
				return true;
			}		
		};
		fork1.registerTouchArea(Fork1RightDown);
		fork1.setTouchAreaBindingEnabled(true);
		fork1.getLastChild().attachChild(Fork1RightDown);
		
		final Sprite PubOutLeft = new Sprite(210,200,mLeftRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					stage=6;
					mEngine.setScene(pubIn);
					walkSound.play();					
					break;
				}
				return true;
			}		
		};
		pubOut.registerTouchArea(PubOutLeft);
		pubOut.setTouchAreaBindingEnabled(true);
		pubOut.getLastChild().attachChild(PubOutLeft);
		
		final TiledSprite Glass = new TiledSprite(100,180,234,216,mGlassRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(brickSelect==true)
					{
						glassSound.play();
						Brick.setVisible(false);
						brickSelect=false;
						isSelect=false;
						seeBrick=false;
						
						this.setCurrentTileIndex(1);
						isGlassBroken=true;
					}
					else if(isGlassBroken==true && seeRedWord==false)
					{
						Word.setVisible(true);
						Word.setCurrentTileIndex(9);
					}
					break;
				}
				return true;
			}		
		};		
		store.registerTouchArea(Glass);
		store.setTouchAreaBindingEnabled(true);	
		store.getLastChild().attachChild(Glass);
				
		final Sprite RedWord = new Sprite(100,200,mRedWordRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					break;
				}
				return true;
			}		
		};
		store.registerTouchArea(RedWord);
		store.setTouchAreaBindingEnabled(true);	
		RedWord.setVisible(false);
		store.getLastChild().attachChild(RedWord);
		
		final Sprite PubOutUp = new Sprite(350,100,mUpRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					if(seeRedWord==true)
					{
						Glass.setCurrentTileIndex(0);
						RedWord.setVisible(true);
						if(playRedWordMusic==true)
						{
							redWordSound.play();
							playRedWordMusic=false;
						}
					}
					stage=7;
					mEngine.setScene(store);
					walkSound.play();
					break;
				}
				return true;
			}		
		};		
		pubOut.registerTouchArea(PubOutUp);
		pubOut.setTouchAreaBindingEnabled(true);
		pubOut.getLastChild().attachChild(PubOutUp);
		
		final Sprite Fork2RightDown = new Sprite(500,400,mRightDownRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					stage=4;
					mEngine.setScene(fork1);
					walkSound.play();
					if(needLeavePlayer==true)
						leave++;
					if(seeCoin==true && isBirdFree==true)
					{
						Seed.setVisible(false);
						ForkBird.setVisible(false);
						Coin.setVisible(true);
					}
					break;
				}
				return true;
			}		
		};
		fork2.registerTouchArea(Fork2RightDown);
		fork2.setTouchAreaBindingEnabled(true);
		fork2.getLastChild().attachChild(Fork2RightDown);
		
		final Sprite PubOutDown = new Sprite(350,370,mDownRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					stage=4;
					mEngine.setScene(fork1);
					walkSound.play();
					if(seeBird==true)
					{
						ForkBird.setVisible(true);
					}
					break;
				}
				return true;
			}		
		};
		pubOut.registerTouchArea(PubOutDown);
		pubOut.setTouchAreaBindingEnabled(true);
		pubOut.getLastChild().attachChild(PubOutDown);
		
		//pubIn				
		final TiledSprite Drawer = new TiledSprite(150,180,78,51,mDrawerRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isDrawerOpen==true)
					{
						this.setCurrentTileIndex(2);						
						if(isGetKey2==false)
						{
							ToolKey2.setVisible(true);
							getSound.play();
							seeKey2=true;
							isGetKey2=true;
						}			
						
						//join scene
						home.registerTouchArea(ToolKey2);
						home.setTouchAreaBindingEnabled(true);
						home.getLastChild().attachChild(ToolKey2);
						
						fork2.registerTouchArea(ToolKey2);
						fork2.setTouchAreaBindingEnabled(true);
						fork2.getLastChild().attachChild(ToolKey2);
						
						garden.registerTouchArea(ToolKey2);
						garden.setTouchAreaBindingEnabled(true);
						garden.getLastChild().attachChild(ToolKey2);
						
						fork1.registerTouchArea(ToolKey2);
						fork1.setTouchAreaBindingEnabled(true);
						fork1.getLastChild().attachChild(ToolKey2);
						
						pubOut.registerTouchArea(ToolKey2);
						pubOut.setTouchAreaBindingEnabled(true);
						pubOut.getLastChild().attachChild(ToolKey2);						
						
						store.registerTouchArea(ToolKey2);
						store.setTouchAreaBindingEnabled(true);
						store.getLastChild().attachChild(ToolKey2);
						
						passage.registerTouchArea(ToolKey2);
						passage.setTouchAreaBindingEnabled(true);
						passage.getLastChild().attachChild(ToolKey2);
						
						floristOut.registerTouchArea(ToolKey2);
						floristOut.setTouchAreaBindingEnabled(true);
						floristOut.getLastChild().attachChild(ToolKey2);
						
						floristIn.registerTouchArea(ToolKey2);
						floristIn.setTouchAreaBindingEnabled(true);
						floristIn.getLastChild().attachChild(ToolKey2);
						
						park.registerTouchArea(ToolKey2);
						park.setTouchAreaBindingEnabled(true);
						park.getLastChild().attachChild(ToolKey2);						
					}
					break;
				}
				return true;
			}		
		};
		pubIn.registerTouchArea(Drawer);
		pubIn.setTouchAreaBindingEnabled(true);
		pubIn.getLastChild().attachChild(Drawer);
		
		final TiledSprite Stove = new TiledSprite(150,240,84,90,mStoveRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(matchSelect==true && isFillWood==true)
					{
						fireSound.play();
						this.setCurrentTileIndex(2);
						matchSelect=false;
						isSelect=false;
						Match.setVisible(false);
						seeMatch=false;
						
						Drawer.setCurrentTileIndex(1);
						drawerSound.play();
						isDrawerOpen=true;
					}
					if(woodSelect==true)
					{
						this.setCurrentTileIndex(1);
						woodSelect=false;
						isSelect=false;
						isFillWood=true;
						Wood.setVisible(false);
						seeWood=false;
					}
					else
					{
						if(isDrawerOpen==false)
						{
							Word.setVisible(true);
							Word.setCurrentTileIndex(4);
						}
					}
					break;
				}
				return true;
			}		
		};
		pubIn.registerTouchArea(Stove);
		pubIn.setTouchAreaBindingEnabled(true);	
		pubIn.getLastChild().attachChild(Stove);
		
		final Birdnew PubBird = new Birdnew(510,235,mBirdRegion);
		pubIn.getLastChild().attachChild(PubBird);
		
		final TiledSprite Cage = new TiledSprite(490,150,67,135,mCageRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(key2Select==true)
					{
						this.setCurrentTileIndex(1);
						openCageSound.play();
						
						ToolKey2.setVisible(false);
						key2Select=false;
						isSelect=false;
						seeKey2=false;
						
						PubBird.move();
						isBirdFree=true;
						birdSound.play();
						seeBird=true;
					}
					else
					{
						if(isBirdFree==false)
						{
							Word.setVisible(true);
							Word.setCurrentTileIndex(0);
						}
					}
					break;
				}
				return true;
			}		
		};
		pubIn.registerTouchArea(Cage);
		pubIn.setTouchAreaBindingEnabled(true);	
		pubIn.getLastChild().attachChild(Cage);
				
		final Sprite PubInDown = new Sprite(350,370,mDownRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					stage=5;
					mEngine.setScene(pubOut);
					walkSound.play();
					if(seeBrick==true)
					{
						Brick.setVisible(true);
						Beggar.setVisible(false);
						seeBeggar=false;
					}
					if(isBirdFree==true)
						PubBird.setVisible(false);
					break;
				}
				return true;
			}		
		};
		pubIn.registerTouchArea(PubInDown);
		pubIn.setTouchAreaBindingEnabled(true);
		pubIn.getLastChild().attachChild(PubInDown);
		
		//Store					
		final Sprite StoreUp = new Sprite(480,270,mUpRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					stage=8;
					mEngine.setScene(passage);
					walkSound.play();
					break;
				}
				return true;
			}		
		};
		store.registerTouchArea(StoreUp);
		store.setTouchAreaBindingEnabled(true);
		store.getLastChild().attachChild(StoreUp);
		
		final Sprite StoreLeft = new Sprite(60,400,mLeftRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					stage=5;
					mEngine.setScene(pubOut);
					walkSound.play();
					if(goodEnd || badEnd==true)
						Fork1Up.setVisible(true);
					if(seeBrick==true)
					{
						Brick.setVisible(true);
						Beggar.setVisible(false);
						seeBeggar=false;
					}
					break;
				}
				return true;
			}		
		};
		store.registerTouchArea(StoreLeft);
		store.setTouchAreaBindingEnabled(true);
		store.getLastChild().attachChild(StoreLeft);
		
		final TiledSprite Lost = new TiledSprite(90,170,161,161,mLostRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					break;
				}
				return true;
			}		
		};
		floristOut.registerTouchArea(Lost);
		floristOut.setTouchAreaBindingEnabled(true);	
		floristOut.getLastChild().attachChild(Lost);
		
		final TiledSprite OwnerIn = new TiledSprite(510,145,82,264,mOwnerInRegion);
		OwnerIn.setVisible(false);
		floristIn.getLastChild().attachChild(OwnerIn);	
		
		final TiledSprite Owner = new TiledSprite(460,180,120,295,mOwnerRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(ringSelect==true)
					{
						this.setCurrentTileIndex(1);
						OwnerIn.setCurrentTileIndex(1);
						Lost.setCurrentTileIndex(1);
						
						ringSelect=false;
						isSelect=false;
						ToolRing.setVisible(false);
						returnRing=true;
						seeRing=false;
					}
					break;
				}
				return true;
			}		
		};
		floristOut.registerTouchArea(Owner);
		floristOut.setTouchAreaBindingEnabled(true);	
		floristOut.getLastChild().attachChild(Owner);
				
		final TiledSprite Brand = new TiledSprite(310,240,115,59,mBrandRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					break;
				}
				return true;
			}		
		};
		floristOut.registerTouchArea(Brand);
		floristOut.setTouchAreaBindingEnabled(true);	
		floristOut.getLastChild().attachChild(Brand);	
		
		final Sprite StoreRight = new Sprite(590,400,mRightRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					stage=9;
					mEngine.setScene(floristOut);
					walkSound.play();
					if(seeDog==true)
					{
						Owner.setVisible(false);
						Brand.setCurrentTileIndex(1);
						floristOpen=false;
					}
					break;
				}
				return true;
			}		
		};
		store.registerTouchArea(StoreRight);
		store.setTouchAreaBindingEnabled(true);
		store.getLastChild().attachChild(StoreRight);
		
		//passage
		final Sprite Cloud = new Sprite(160,90,mCloudRegion);	
		passage.getLastChild().attachChild(Cloud);
		
		final TiledSprite Girl = new TiledSprite(320,210,72,99,mGirlRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(flowerSelect==true)
					{
						Cloud.setVisible(false);
						
						ToolFlower.setVisible(false);
						isSelect=false;
						flowerSelect=false;
						seeToolFlower=false;
						
						if(disaster==false)
						{
							getSound.play();
							this.setCurrentTileIndex(1);
							goodEnd=true;
							ToolLaugh.setVisible(true);
							
							//join scene
							garden.registerTouchArea(ToolLaugh);
							garden.setTouchAreaBindingEnabled(true);
							garden.getLastChild().attachChild(ToolLaugh);
							
							fork2.registerTouchArea(ToolLaugh);
							fork2.setTouchAreaBindingEnabled(true);
							fork2.getLastChild().attachChild(ToolLaugh);
							
							suburb.registerTouchArea(ToolLaugh);
							suburb.setTouchAreaBindingEnabled(true);
							suburb.getLastChild().attachChild(ToolLaugh);
							
							fork1.registerTouchArea(ToolLaugh);
							fork1.setTouchAreaBindingEnabled(true);
							fork1.getLastChild().attachChild(ToolLaugh);
							
							pubOut.registerTouchArea(ToolLaugh);
							pubOut.setTouchAreaBindingEnabled(true);
							pubOut.getLastChild().attachChild(ToolLaugh);
							
							pubIn.registerTouchArea(ToolLaugh);
							pubIn.setTouchAreaBindingEnabled(true);
							pubIn.getLastChild().attachChild(ToolLaugh);
							
							store.registerTouchArea(ToolLaugh);
							store.setTouchAreaBindingEnabled(true);
							store.getLastChild().attachChild(ToolLaugh);
							
							passage.registerTouchArea(ToolLaugh);
							passage.setTouchAreaBindingEnabled(true);
							passage.getLastChild().attachChild(ToolLaugh);
							
							floristOut.registerTouchArea(ToolLaugh);
							floristOut.setTouchAreaBindingEnabled(true);
							floristOut.getLastChild().attachChild(ToolLaugh);
							
							floristIn.registerTouchArea(ToolLaugh);
							floristIn.setTouchAreaBindingEnabled(true);
							floristIn.getLastChild().attachChild(ToolLaugh);
							
							park.registerTouchArea(ToolLaugh);
							park.setTouchAreaBindingEnabled(true);
							park.getLastChild().attachChild(ToolLaugh);
						}
						else
						{
							this.setCurrentTileIndex(2);
							bloodGirlSound.play();
							badEnd=true;
						}
					}
					break;
				}
				return true;
			}		
		};
		passage.registerTouchArea(Girl);
		passage.setTouchAreaBindingEnabled(true);	
		passage.getLastChild().attachChild(Girl);
		
		final Sprite PassageDown = new Sprite(340,350,mDownRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					stage=7;
					mEngine.setScene(store);
					walkSound.play();
					break;
				}
				return true;
			}		
		};
		passage.registerTouchArea(PassageDown);
		passage.setTouchAreaBindingEnabled(true);
		passage.getLastChild().attachChild(PassageDown);
		
		//floristOut
		final Sprite Blood = new Sprite(0,0,mBloodRegion);
		Blood.setVisible(false);
		floristIn.getLastChild().attachChild(Blood);
		
		final Sprite RedWordIn = new Sprite(240,140,mRedWordInRegion);
		floristIn.getLastChild().attachChild(RedWordIn);
		RedWordIn.setVisible(false);
		
		final Sprite Flower = new Sprite(120,370,mFlowerRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isGetFlower==false && seeFlower==true)
					{
						getSound.play();
						this.setVisible(false);
						isGetFlower=true;
						ToolFlower.setVisible(true);
						ToolFlower.setCurrentTileIndex(1);
						seeToolFlower=true;
						
						//join scene
						garden.registerTouchArea(ToolFlower);
						garden.setTouchAreaBindingEnabled(true);
						garden.getLastChild().attachChild(ToolFlower);
						
						fork2.registerTouchArea(ToolFlower);
						fork2.setTouchAreaBindingEnabled(true);
						fork2.getLastChild().attachChild(ToolFlower);
						
						suburb.registerTouchArea(ToolFlower);
						suburb.setTouchAreaBindingEnabled(true);
						suburb.getLastChild().attachChild(ToolFlower);
						
						fork1.registerTouchArea(ToolFlower);
						fork1.setTouchAreaBindingEnabled(true);
						fork1.getLastChild().attachChild(ToolFlower);
						
						pubOut.registerTouchArea(ToolFlower);
						pubOut.setTouchAreaBindingEnabled(true);
						pubOut.getLastChild().attachChild(ToolFlower);
						
						pubIn.registerTouchArea(ToolFlower);
						pubIn.setTouchAreaBindingEnabled(true);
						pubIn.getLastChild().attachChild(ToolFlower);
						
						store.registerTouchArea(ToolFlower);
						store.setTouchAreaBindingEnabled(true);
						store.getLastChild().attachChild(ToolFlower);
						
						passage.registerTouchArea(ToolFlower);
						passage.setTouchAreaBindingEnabled(true);
						passage.getLastChild().attachChild(ToolFlower);
						
						floristOut.registerTouchArea(ToolFlower);
						floristOut.setTouchAreaBindingEnabled(true);
						floristOut.getLastChild().attachChild(ToolFlower);
						
						floristIn.registerTouchArea(ToolFlower);
						floristIn.setTouchAreaBindingEnabled(true);
						floristIn.getLastChild().attachChild(ToolFlower);
						
						park.registerTouchArea(ToolFlower);
						park.setTouchAreaBindingEnabled(true);
						park.getLastChild().attachChild(ToolFlower);
					}
					
					break;
				}
				return true;
			}		
		};
		floristIn.registerTouchArea(Flower);
		floristIn.setTouchAreaBindingEnabled(true);
		Flower.setVisible(false);
		floristIn.getLastChild().attachChild(Flower);
		
		final Sprite BloodEye = new Sprite(0,0,mBloodEyeRegion);
		floristIn.getLastChild().attachChild(BloodEye);
		BloodEye.setVisible(false);
		
		final Sprite DoorFlorist = new Sprite(270,200,mDoorInFloristRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					if(floristOpen==true)
					{
						mEngine.setScene(floristIn);
						doorSound.play();
						
						if(returnRing==true)
						{
							if(num==0)
							{
								OwnerIn.setVisible(true);
							}
							num++;
						}
					}
					else if(floristOpen==false && key3Select==true)
					{
						backMusic.pause();
						playBackMusic=false;
						
						floristMusic.play();
						playFloristMusic=true;
						
						mEngine.setScene(floristIn);
						Blood.setVisible(true);
						BloodEye.setVisible(true);
						RedWordIn.setVisible(true);
						Flower.setVisible(true);
						seeFlower=true;
						doorSound.play();
					}
					break;
				}
				return true;
			}		
		};
		
		floristOut.registerTouchArea(DoorFlorist);
		floristOut.setTouchAreaBindingEnabled(true);	
		floristOut.getLastChild().attachChild(DoorFlorist);
		
		final Sprite FloristOutDown = new Sprite(670,320,mRightDownRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					stage=7;
					mEngine.setScene(store);
					walkSound.play();
					break;
				}
				return true;
			}		
		};
		floristOut.registerTouchArea(FloristOutDown);
		floristOut.setTouchAreaBindingEnabled(true);	
		floristOut.getLastChild().attachChild(FloristOutDown);
		
		final TiledSprite Dog = new TiledSprite(470,296,118,163,mDogRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isDogDead==true)
					{
						dogNum++;
						if(dogNum>=1)
						{
							this.setCurrentTileIndex(2);							
							if(dogNum==1)
							{
								getSound.play();
								ToolKey3.setVisible(true);
								isGetKey3=true;
								seeKey3=true;
								
								//join scene
								garden.registerTouchArea(ToolKey3);
								garden.setTouchAreaBindingEnabled(true);
								garden.getLastChild().attachChild(ToolKey3);
								
								fork2.registerTouchArea(ToolKey3);
								fork2.setTouchAreaBindingEnabled(true);
								fork2.getLastChild().attachChild(ToolKey3);
								
								suburb.registerTouchArea(ToolKey3);
								suburb.setTouchAreaBindingEnabled(true);
								suburb.getLastChild().attachChild(ToolKey3);
								
								fork1.registerTouchArea(ToolKey3);
								fork1.setTouchAreaBindingEnabled(true);
								fork1.getLastChild().attachChild(ToolKey3);
								
								pubOut.registerTouchArea(ToolKey3);
								pubOut.setTouchAreaBindingEnabled(true);
								pubOut.getLastChild().attachChild(ToolKey3);
								
								pubIn.registerTouchArea(ToolKey3);
								pubIn.setTouchAreaBindingEnabled(true);
								pubIn.getLastChild().attachChild(ToolKey3);
								
								store.registerTouchArea(ToolKey3);
								store.setTouchAreaBindingEnabled(true);
								store.getLastChild().attachChild(ToolKey3);
								
								passage.registerTouchArea(ToolKey3);
								passage.setTouchAreaBindingEnabled(true);
								passage.getLastChild().attachChild(ToolKey3);
								
								floristOut.registerTouchArea(ToolKey3);
								floristOut.setTouchAreaBindingEnabled(true);
								floristOut.getLastChild().attachChild(ToolKey3);
								
								floristIn.registerTouchArea(ToolKey3);
								floristIn.setTouchAreaBindingEnabled(true);
								floristIn.getLastChild().attachChild(ToolKey3);
								
								park.registerTouchArea(ToolKey3);
								park.setTouchAreaBindingEnabled(true);
								park.getLastChild().attachChild(ToolKey3);
							}
			
						}
					}
					else if(knifeSelect==true && seeDog==true)
					{
						this.setCurrentTileIndex(1);
						isDogDead=true;
						dogSound.play();
					}
					else if(seeDog==true)
					{
						Word.setVisible(true);
						Word.setCurrentTileIndex(15);
					}
					break;
				}
				return true;
			}		
		};
		
		park.registerTouchArea(Dog);
		park.setTouchAreaBindingEnabled(true);
		Dog.setVisible(false);
		park.getLastChild().attachChild(Dog);
		
		final Sprite SickOwner = new Sprite(400,310,mSickOwnerRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(medSelect==true && seeSickOwner==true)
					{
						ToolMed.setVisible(false);
						medSelect=false;
						isSelect=false;
						seeMed=false;
						
						this.setVisible(false);
						OwnerIn.setVisible(true);
						
						getSound.play();
						ToolFlower.setVisible(true);
						isGetFlower=true;
						seeToolFlower=true;
						
						//join scene
						garden.registerTouchArea(ToolFlower);
						garden.setTouchAreaBindingEnabled(true);
						garden.getLastChild().attachChild(ToolFlower);
						
						fork2.registerTouchArea(ToolFlower);
						fork2.setTouchAreaBindingEnabled(true);
						fork2.getLastChild().attachChild(ToolFlower);
						
						suburb.registerTouchArea(ToolFlower);
						suburb.setTouchAreaBindingEnabled(true);
						suburb.getLastChild().attachChild(ToolFlower);
						
						fork1.registerTouchArea(ToolFlower);
						fork1.setTouchAreaBindingEnabled(true);
						fork1.getLastChild().attachChild(ToolFlower);
						
						pubOut.registerTouchArea(ToolFlower);
						pubOut.setTouchAreaBindingEnabled(true);
						pubOut.getLastChild().attachChild(ToolFlower);
						
						pubIn.registerTouchArea(ToolFlower);
						pubIn.setTouchAreaBindingEnabled(true);
						pubIn.getLastChild().attachChild(ToolFlower);
						
						store.registerTouchArea(ToolFlower);
						store.setTouchAreaBindingEnabled(true);
						store.getLastChild().attachChild(ToolFlower);
						
						passage.registerTouchArea(ToolFlower);
						passage.setTouchAreaBindingEnabled(true);
						passage.getLastChild().attachChild(ToolFlower);
						
						floristOut.registerTouchArea(ToolFlower);
						floristOut.setTouchAreaBindingEnabled(true);
						floristOut.getLastChild().attachChild(ToolFlower);
						
						floristIn.registerTouchArea(ToolFlower);
						floristIn.setTouchAreaBindingEnabled(true);
						floristIn.getLastChild().attachChild(ToolFlower);
						
						park.registerTouchArea(ToolFlower);
						park.setTouchAreaBindingEnabled(true);
						park.getLastChild().attachChild(ToolFlower);
					}
					else
					{
						Word.setVisible(true);
						Word.setCurrentTileIndex(11);
					}
					break;
				}
				return true;
			}		
		};
		floristIn.registerTouchArea(SickOwner);
		floristIn.setTouchAreaBindingEnabled(true);
		SickOwner.setVisible(false);
		floristIn.getLastChild().attachChild(SickOwner);
		
		final Sprite SickOwnerPark = new Sprite(30,370,mSickOwnerRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setCurrentTileIndex(11);			
					break;
				}
				return true;
			}		
		};
		park.registerTouchArea(SickOwnerPark);
		park.setTouchAreaBindingEnabled(true);
		SickOwnerPark.setVisible(false);
		park.getLastChild().attachChild(SickOwnerPark);
		
		final Sprite FloristOutLeft = new Sprite(30,370,mLeftRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					stage=11;
					mEngine.setScene(park);
					if(floristOpen==false)
					{
						Dog.setVisible(true);
						SickOwnerPark.setVisible(true);					
					}
					walkSound.play();
					break;
				}
				return true;
			}		
		};
		floristOut.registerTouchArea(FloristOutLeft);
		floristOut.setTouchAreaBindingEnabled(true);
		floristOut.getLastChild().attachChild(FloristOutLeft);
		
		//floristIn			
		final Sprite FloristInDown = new Sprite(370,380,mDownRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					stage=9;
					mEngine.setScene(floristOut);
					if(playFloristMusic==true)
					{
						floristMusic.pause();
						backMusic.resume();
					}
					walkSound.play();
					if(num==1)
					{
						Owner.setVisible(false);
						OwnerIn.setVisible(false);
						SickOwner.setVisible(true);
						seeSickOwner=true;
					}
					break;
				}
				return true;
			}		
		};
		floristIn.registerTouchArea(FloristInDown);
		floristIn.setTouchAreaBindingEnabled(true);	
		floristIn.getLastChild().attachChild(FloristInDown);				
		
		final Sprite Photo = new Sprite(180,200,mPhotoRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(true);
					Word.setCurrentTileIndex(18);
					break;
				}
				return true;
			}		
		};
		floristIn.registerTouchArea(Photo);
		floristIn.setTouchAreaBindingEnabled(true);	
		floristIn.getLastChild().attachChild(Photo);	
		
		//park
		final Sprite ParkRight = new Sprite(610,390,mRightRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					stage=9;
					mEngine.setScene(floristOut);
					walkSound.play();
					break;
				}
				return true;
			}		
		};
		park.registerTouchArea(ParkRight);
		park.setTouchAreaBindingEnabled(true);
		park.getLastChild().attachChild(ParkRight);
		
		final Sprite Black = new Sprite(0,0,mBlackRegion);
		Black.setVisible(false);
		home.getLastChild().attachChild(Black);
		pubIn.getLastChild().attachChild(Black);
		floristIn.getLastChild().attachChild(Black);
		
		final Sprite DayOrNight = new Sprite(20,100,mDayOrNightRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					if(isNight==false)
					{
						Black.setVisible(true);
						isNight=true;
					}
					else
					{
						Black.setVisible(false);
						isNight=false;
					}
					break;
				}
				return true;
			}
		};
		home.registerTouchArea(DayOrNight);
		home.setTouchAreaBindingEnabled(true);
		home.getLastChild().attachChild(DayOrNight);
		pubIn.registerTouchArea(DayOrNight);
		pubIn.setTouchAreaBindingEnabled(true);
		pubIn.getLastChild().attachChild(DayOrNight);
		floristIn.registerTouchArea(DayOrNight);
		floristIn.setTouchAreaBindingEnabled(true);
		floristIn.getLastChild().attachChild(DayOrNight);	
		
		//end
		final TiledSprite Message = new TiledSprite(0,0,800,480,mMessageRegion);
		Message.setVisible(false);
		home.getLastChild().attachChild(Message);
		endGood.getLastChild().attachChild(Message);
		endBad.getLastChild().attachChild(Message);
		
		final Sprite HomeLeft = new Sprite(40,380,mLeftRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Message.setVisible(false);
					this.setVisible(false);
					seeMessage=true;
					break;
				}
				return true;
			}		
		};
		HomeLeft.setVisible(false);
		home.registerTouchArea(HomeLeft);
		home.setTouchAreaBindingEnabled(true);
		home.getLastChild().attachChild(HomeLeft);
					
		final Sprite Letter = new Sprite(380,300,mLetterRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					if(seeMessage==false)
					{
						Message.setVisible(true);
						if(goodEnd==true)
							Message.setCurrentTileIndex(2);
						if(badEnd==true)
							Message.setCurrentTileIndex(1);
						this.setVisible(false);
						HomeLeft.setVisible(true);
					}
					break;
				}
				return true;
			}		
		};
		home.registerTouchArea(Letter);
		home.setTouchAreaBindingEnabled(true);
		home.getLastChild().attachChild(Letter);
		
		//forest
		final Sprite ForestUp = new Sprite(300,350,mUpRegion){
			@Override
			public boolean onAreaTouched(
					final TouchEvent pAreaTouchEvent,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY){
				switch(pAreaTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					Word.setVisible(false);
					playForestMusic=false;
					forestMusic.pause();
					if(goodEnd==true)
					{
						if(goUpNum==5)
						{
							mEngine.setScene(endGood);
							endGood.registerTouchArea(Letter);
							endGood.setTouchAreaBindingEnabled(true);
							endGood.getLastChild().attachChild(Letter);
							Letter.setVisible(true);
							seeMessage=false;
						}
						if(goUpNum==4)
						{
							noiseMusic.pause();
							Mosaic.setVisible(false);
							goUpNum=5;
							Word.setVisible(false);
						}
						if(goUpNum==3)
						{
							noiseMusic.play();
							Mosaic.setVisible(true);
							goUpNum=4;
							Word.setVisible(true);
							Word.setCurrentTileIndex(13);
						}
						if(goUpNum==2)
						{
							noiseMusic.pause();
							Mosaic.setVisible(false);
							goUpNum=3;
							Word.setVisible(false);
						}
						if(goUpNum==1)
						{
							noiseMusic.play();
							Mosaic.setVisible(true);
							goUpNum=2;
							Word.setVisible(true);
							Word.setCurrentTileIndex(12);
						}
					}
					else if(badEnd==true)
					{

						if(goUpNum==5)
						{
							noiseMusic.pause();
							mEngine.setScene(endBad);
							endBad.registerTouchArea(Letter);
							endBad.setTouchAreaBindingEnabled(true);
							endBad.getLastChild().attachChild(Letter);
							Letter.setVisible(true);
							seeMessage=false;
							Pic4.setVisible(false);
						}
						if(goUpNum==4)
						{
							noiseMusic.play();
							Pic4.setVisible(true);
							Pic3.setVisible(false);
							goUpNum=5;
						}
						if(goUpNum==3)
						{
							noiseMusic.play();
							Pic3.setVisible(true);
							Pic2.setVisible(false);
							goUpNum=4;
						}
						if(goUpNum==2)
						{
							noiseMusic.play();
							Pic2.setVisible(true);
							Pic1.setVisible(false);
							goUpNum=3;
						}
						if(goUpNum==1)
						{
							noiseMusic.play();
							Pic1.setVisible(true);
							goUpNum=2;
						}
					}
					break;
				}
				return true;
			}
		};
		forest.registerTouchArea(ForestUp);
		forest.setTouchAreaBindingEnabled(true);
		forest.getLastChild().attachChild(ForestUp);
								
		return home;
	}
	
	@Override
	public void onLoadComplete()
	{
		
	}
	
	@Override
	protected void onPause() 
	{
		super.onPause();
		if(playRainMusic==true)
		{
			rainMusic.pause();
			playRainMusic=false;
			musicPaused = true;
		}
		if(playForestMusic==true)
		{
			forestMusic.pause();
			playForestMusic=false;
			musicPaused = true;
		}
		if(playFloristMusic==true)
		{
			floristMusic.pause();
			playFloristMusic=false;
			musicPaused = true;
		}
		if(playBackMusic==true)
		{
			backMusic.pause();
			playBackMusic=false;
			musicPaused = true;
		}
		if(playNoiseMusic==true)
		{
			noiseMusic.pause();
			playNoiseMusic=false;
			musicPaused = true;
		}
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(playRainMusic==false && musicPaused==true)
		{
			rainMusic.resume();
			playRainMusic=true;
			musicPaused = false;
		}
		if(playForestMusic==false && musicPaused==true)
		{
			forestMusic.resume();
			playForestMusic=true;
			musicPaused = false;
		}
		if(playBackMusic==false && musicPaused==true)
		{
			backMusic.resume();
			playBackMusic=true;
			musicPaused = false;
		}
		if(playFloristMusic==false && musicPaused==true)
		{
			floristMusic.resume();
			playFloristMusic=true;
			musicPaused = false;
		}
		if(playNoiseMusic==false && musicPaused==true)
		{
			noiseMusic.resume();
			playNoiseMusic=true;
			musicPaused = false;
		}
	}
	
}
