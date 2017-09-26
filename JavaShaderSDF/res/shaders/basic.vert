#version 450

layout (location = 0) out vec2 texcoord;

vec2 vertices[3];

out gl_PerVertex { 

    vec4 gl_Position;
};

void main() {

    vertices[0] = vec2( -1.0, -1.0);
    vertices[1] = vec2( 3.0,  -1.0);
    vertices[2] = vec2( -1.0,  3.0);
    gl_Position = vec4(vertices[gl_VertexID % 3], 0.0, 1.0);
    texcoord = vertices[gl_VertexID % 3];
}