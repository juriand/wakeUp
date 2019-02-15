package com.example.wakeup;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

public class Birdnew extends AnimatedSprite
{
	private PhysicsHandler mPhysicsHandler;
	private int mSpeed=-100;
	
	public Birdnew(float px,float py,TiledTextureRegion pTiledTextrueRegion)
	{
		super(px,py,pTiledTextrueRegion);
		this.mPhysicsHandler = new PhysicsHandler(this);
		registerUpdateHandler(this.mPhysicsHandler);
	}
	
	public void move()
	{
		mPhysicsHandler.setVelocity(mSpeed,0);
		animate(new long[] {100,100,100},1,3,true);
	}

}

