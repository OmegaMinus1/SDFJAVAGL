package com.base.engine;

public class Vector3f {

	private float x;
	private float y;
	private float z;

	public Vector3f(float x, float y, float z) {

		this.x = x;
		this.y = y;
		this.z = z;

	}

	public String toString() {

		return "( " + x + " " + y + " " + z + " )";

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

	// Called on external.///////////////////////////////////////////////
	public float mag(Vector3f v) {

		return (float) Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY() + v.getZ() * v.getZ());
	}

	public Vector3f subtract(Vector3f v1, Vector3f v2) {

		return new Vector3f(v1.getX() - v2.getX(), v1.getY() - v2.getY(), v1.getZ() - v2.getZ());
	}

	public Vector3f add(Vector3f v1, Vector3f v2) {

		return new Vector3f(v1.getX() + v2.getX(), v1.getY() + v2.getY(), v1.getZ() + v2.getZ());
	}

	public Vector3f cross(Vector3f v1, Vector3f v2) {

		return new Vector3f(v1.getY() * v2.getZ() - v2.getY() * v1.getZ(), v1.getZ() * v2.getX() - v2.getZ() * v1.getX(), v1.getX() * v2.getY() - v2.getX() * v1.getY());

	}

	public Vector3f divide(Vector3f v, float number) {

		if (number != 0) {

			return new Vector3f(v.getX() / number, v.getY() / number, v.getZ() / number);

		}

		return v;

	}

	public Vector3f normalize(Vector3f v) {

		float d = (float) Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY() + v.getZ() * v.getZ());

		if (d != 0) {

			return new Vector3f(v.getX() / d, v.getY() / d, v.getZ() / d);
		}

		return v;

	}

	public float dot(Vector3f v1, Vector3f v2) {

		return v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();
	}

	public Vector3f RotateX(Vector3f v, float degree) {

		float angle = (float) Math.toRadians(degree);
		return new Vector3f(v.getX(), (v.getY() * (float) Math.cos(angle)) + (v.getZ() * (float) -Math.sin(angle)),
				(v.getY() * (float) Math.sin(angle)) + (v.getZ() * (float) Math.cos(angle)));
	}

	public Vector3f RotateY(Vector3f v, float degree) {

		float angle = (float) Math.toRadians(degree);
		return new Vector3f((v.getX() * (float) Math.cos(angle)) + (v.getZ() * (float) Math.sin(angle)), v.getY(),
				(v.getX() * (float) -Math.sin(angle)) + (v.getZ() * (float) Math.cos(angle)));
	}

	public Vector3f RotateZ(Vector3f v, float degree) {

		float angle = (float) Math.toRadians(degree);
		return new Vector3f((v.getX() * (float) Math.cos(angle)) + (v.getY() * (float) -Math.sin(angle)),
				(v.getX() * (float) Math.sin(angle)) + (v.getY() * (float) Math.cos(angle)), v.getZ());
	}

	// Called on this.///////////////////////////////////////////////
	public float mag() {

		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public Vector3f subtract(Vector3f v2) {

		this.x -= v2.getX();
		this.y -= v2.getY();
		this.z -= v2.getZ();

		return this;
	}

	public Vector3f subtract(float f) {

		this.x -= f;
		this.y -= f;
		this.z -= f;

		return this;
	}

	public Vector3f add(Vector3f v2) {

		this.x += v2.getX();
		this.y += v2.getY();
		this.z += v2.getZ();

		return this;
	}

	public Vector3f add(float f) {

		this.x += f;
		this.y += f;
		this.z += f;

		return this;
	}

	public Vector3f mul(Vector3f v2) {

		this.x *= v2.getX();
		this.y *= v2.getY();
		this.z *= v2.getZ();

		return this;
	}

	public Vector3f mul(float f) {

		this.x *= f;
		this.y *= f;
		this.z *= f;

		return this;
	}

	public Vector3f cross(Vector3f v2) {

		float xx = y * v2.getZ() - v2.getY() * z;
		float yy = z * v2.getX() - v2.getZ() * x;
		float zz = x * v2.getY() - v2.getX() * y;

		this.x = xx;
		this.y = yy;
		this.z = zz;

		return this;
	}

	public Vector3f divide(Vector3f v2) {

		if (v2.getX() != 0) {

			this.x /= v2.getX();

		}

		if (v2.getY() != 0) {

			this.y /= v2.getY();

		}

		if (v2.getZ() != 0) {

			this.z /= v2.getZ();
		}

		return this;
	}

	public Vector3f divide(float number) {

		if (number != 0) {

			this.x /= number;
			this.y /= number;
			this.z /= number;
		}

		return this;
	}

	public Vector3f normalize() {

		float d = (float) Math.sqrt(x * x + y * y + z * z);

		if (d != 0) {

			this.x = x / d;
			this.y = y / d;
			this.z = z / d;
		}

		return this;
	}

	public float dot(Vector3f v2) {

		return x * v2.getX() + y * v2.getY() + z * v2.getZ();
	}

	public Vector3f RotateX(float degree) {

		float angle = (float) Math.toRadians(degree);
		// this.x = x;
		this.y = (y * (float) Math.cos(angle)) + (z * (float) -Math.sin(angle));
		this.z = (y * (float) Math.sin(angle)) + (z * (float) Math.cos(angle));

		return this;
	}

	public Vector3f RotateY(float degree) {

		float angle = (float) Math.toRadians(degree);
		this.x = (x * (float) Math.cos(angle)) + (z * (float) Math.sin(angle));
		// this.y = y;
		this.z = (x * (float) -Math.sin(angle)) + (z * (float) Math.cos(angle));

		return this;
	}

	public Vector3f RotateZ(float degree) {

		float angle = (float) Math.toRadians(degree);
		this.x = (x * (float) Math.cos(angle)) + (y * (float) -Math.sin(angle));
		this.y = (x * (float) Math.sin(angle)) + (y * (float) Math.cos(angle));
		// this.z = z

		return this;
	}

}
