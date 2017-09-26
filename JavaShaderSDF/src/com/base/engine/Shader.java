package com.base.engine;

import static org.lwjgl.opengl.GL20.*;
import java.util.HashMap;

public class Shader {

	private int program;
	private HashMap<String, Integer> uniforms;

	public Shader() {

		program = glCreateProgram();

		uniforms = new HashMap<String, Integer>();

		if (program == 0) {

			System.out.println("Shader - glCreateProgram() - Error: Unable to create!");
			System.exit(1);

		}

	}

	public void setVertexShader(String text) {

		setProgram(text, GL_VERTEX_SHADER);

	}

	public void setFragmentShader(String text) {

		setProgram(text, GL_FRAGMENT_SHADER);

	}

	public void bind() {
		
		glUseProgram(program);
	}

	public void compileShader() {

		glLinkProgram(program);

		if (glGetProgrami(program, GL_LINK_STATUS) == 0) {

			System.out.println("Shader - glLinkProgram() - Error: " + glGetProgramInfoLog(program, 1024));
			System.exit(1);
		}

		glValidateProgram(program);

		if (glGetProgrami(program, GL_VALIDATE_STATUS) == 0) {

			System.out.println("Shader - glValidateProgram() - Error: " + glGetProgramInfoLog(program, 1024));
			System.exit(1);
		}
	}

	private void setProgram(String text, int type) {

		int shader = glCreateShader(type);

		if (shader == 0) {

			System.out.println("Shader - glCreateShader() - Error: Unable to create!");
			System.exit(1);
		}

		glShaderSource(shader, text);
		glCompileShader(shader);

		if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {

			System.out.println("Shader - glCompileShader() - Error: " + glGetShaderInfoLog(shader, 1024));
			System.exit(1);

		}

		glAttachShader(program, shader);
		
		glBindAttribLocation(program, 0, "position");

	}

	public void addUniform(String uniform) {

		int uniformLocation = glGetUniformLocation(program, uniform);

		if (uniformLocation == -1) {

			System.out.println("Shader - glGetUniformLocation() - Error: " + uniform);
			new Exception().printStackTrace();
			System.exit(1);
		}

		uniforms.put(uniform, uniformLocation);
	}

	public void setUniformi(String uniformName, int value) {

		glUniform1i(uniforms.get(uniformName), value);

	}

	public void setUniformf(String uniformName, float value) {

		glUniform1f(uniforms.get(uniformName), value);

	}

	public void setUniformf(String uniformName, Vector3f value) {

		glUniform3f(uniforms.get(uniformName), value.getX(), value.getY(), value.getZ());

	}

	public void updateTime(float seconds) {

		setUniformf("iTime", seconds);
	}

	public void updateResolution(float width, float height) {

		glUniform2f(uniforms.get("iResolution"), width, height);
	}

}
