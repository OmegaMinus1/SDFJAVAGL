package com.base.engine;

public class Quaternion {

	private float x;
	private float y;
	private float z;
	private float w;

	public Quaternion(float x, float y, float z, float w) {

		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;

	}

	public String toString() {

		return "( " + x + " " + y + " " + z + " " + w + " )";

	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public float mag(Quaternion v) {

		return (float) Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY() + v.getZ() * v.getZ() + v.getW() * v.getW());
	}

	public float mag() {

		return (float) Math.sqrt(x * x + y * y + z * z + w * w);
	}
	
	public Quaternion normalize(Quaternion v) {

		float d = (float) Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY() + v.getZ() * v.getZ() + v.getW() * v.getW());

		if (d != 0) {

			return new Quaternion(v.getX() / d, v.getY() / d, v.getZ() / d, v.getW() / d);
		}

		return v;

	}
	
	public Quaternion normalize() {

		float d = (float) Math.sqrt(x * x + y * y + z * z + w * w);

		if (d != 0) {

			this.x = x / d;
			this.y = y / d;
			this.z = z / d;
			this.w = w / d;
		}

		return this;
	}
	
	public Quaternion conjugate() {
		
		return new Quaternion(-x, -y, -z, w);
	}
	
	public Quaternion mul(Quaternion r) {
		
		float ww = w * r.getW() - x * r.getX() - y * r.getY() - z * r.getZ();
		float xx = x * r.getW() + w * r.getX() + y * r.getZ() - z * r.getY();
		float yy = y * r.getW() + w * r.getY() + z * r.getX() - x * r.getZ();
		float zz = z * r.getW() + w * r.getZ() + x * r.getY() - y * r.getX();
				
		return new Quaternion(xx, yy, zz, ww);
	}
	
	public Quaternion mul(Vector3f r) {
		
		float ww = -x * r.getX() - y * r.getY() - z * r.getZ();
		float xx =  w * r.getX() + y * r.getZ() - z * r.getY();
		float yy =  w * r.getY() + z * r.getX() - x * r.getZ();
		float zz =  w * r.getZ() + x * r.getY() - y * r.getX();
	
		return new Quaternion(xx, yy, zz, ww);
	}
	
}
