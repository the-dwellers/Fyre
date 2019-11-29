// package com.github.thedwellers.fyreplugin.entity;

// import net.minecraft.server.EntityProjectileThrowable;
// import net.minecraft.server.EntitySnowball;
// import net.minecraft.server.EntityTypes;
// import net.minecraft.server.World;
// import net.minecraft.server.Item;
// import net.minecraft.server.Items;
// import net.minecraft.server.MovingObjectPosition;
// import net.minecraft.server.MovingObjectPositionEntity;
// import net.minecraft.server.DamageSource;
// import net.minecraft.server.Entity;

// public class EntityBrick extends EntityProjectileThrowable {

//  public EntityBrick(EntityTypes<? extends EntitySnowball> entitytypes, World world) {
//      super(entitytypes, world);
//  }

//  public EntityBrick(World world, EntityLiving entityliving) {
//      super(EntityTypes.SNOWBALL, entityliving, world);
//  }

//  public EntityBrick(World world, double d0, double d1, double d2) {
//      super(EntityTypes.SNOWBALL, d0, d1, d2, world);
//  }

//  @Override
//  protected Item i() {
//      return Items.BRICK;
//  }

//  @Override
//  protected void a(MovingObjectPosition movingobjectposition) {
//      if (movingobjectposition.getType() == MovingObjectPosition.EnumMovingObjectType.ENTITY) {
//          Entity entity = ((MovingObjectPositionEntity) movingobjectposition).getEntity();

//          entity.damageEntity(DamageSource.projectile(this, this.getShooter()), (float) 2);
//      }

//      if (!this.world.isClientSide) {
//          this.world.broadcastEntityEffect(this, (byte) 3);
//          this.die();
//      }

//  }
// }
