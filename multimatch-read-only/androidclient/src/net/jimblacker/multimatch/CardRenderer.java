package net.jimblacker.multimatch;

import static android.opengl.GLES10.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.os.SystemClock;

public class CardRenderer implements GLSurfaceView.Renderer {

  private static final int NUMBER_TEXTURES = 3;
  private static final int TEXTURE_DIMENSIONS = 2;
  private static final int VERTEX_DIMENSIONS = 3;
  private static final float FRAME_INSET = 0.9f;

  private Context context;

  private FloatBuffer decalVertexBuffer;
  private ShortBuffer indexBuffer;
  private short[] indices = {0, 1, 3, 3, 1, 2};
  private FloatBuffer textureCoordinatesBuffer;

  private int[] textures = new int[NUMBER_TEXTURES];
  private FloatBuffer vertexBuffer;

  float xScale;
  float yScale;
  
  public CardRenderer(Context context) {
    this.context = context;

    
  }

  public void drawFace(GL10 gl, FloatBuffer vertexBuffer) {
    glFrontFace(GL_CCW);
    glVertexPointer(VERTEX_DIMENSIONS, GL_FLOAT, 0, vertexBuffer);
    glEnable(GL_TEXTURE_2D);
    glTexCoordPointer(TEXTURE_DIMENSIONS, GL_FLOAT, 0, textureCoordinatesBuffer);
    glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_SHORT, indexBuffer);
  }

  public void onDrawFrame(GL10 gl) {
    glTexEnvx(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
    glClear(GL_COLOR_BUFFER_BIT);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    GLU.gluLookAt(gl, 0.0f, 0.0f, -4.8f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
    glEnableClientState(GL_VERTEX_ARRAY);
    glEnableClientState(GL_TEXTURE_COORD_ARRAY);
    glActiveTexture(GL_TEXTURE0);

    glRotatef(0.090f * SystemClock.uptimeMillis(), 0.0f, 1.0f, 0.0f);

    glBindTexture(GL_TEXTURE_2D, textures[0]);
    drawFace(gl, vertexBuffer);

    glBindTexture(GL_TEXTURE_2D, textures[1]);
    glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
    drawFace(gl, vertexBuffer);

    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    glBindTexture(GL_TEXTURE_2D, textures[2]);
    drawFace(gl, decalVertexBuffer);
  }

  public void onSurfaceChanged(GL10 gl, int width, int height) {
    glViewport(0, 0, width, height);
    float ratio = (float) width / height;
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glFrustumf(-ratio, ratio, -1.0f, 1.0f, 3.0f, 7.0f);
  }

  public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    // Global settings
    glDisable(GL_DITHER);
    glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    glShadeModel(GL_SMOOTH);
    glEnable(GL_CULL_FACE);
    glEnable(GL_NORMALIZE);
    glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, new float[]{1.0f, 1.0f, 1.0f, 0.f}, 0);

    // Lighting
    glEnable(GL_LIGHTING);
    glLightfv(GL_LIGHT0, GL_POSITION, new float[]{0.f, 50.f, 50.f, 0.0f}, 0);
    glLightfv(GL_LIGHT0, GL_AMBIENT, new float[]{1.0f, 1.0f, 1.0f, 1.0f}, 0);
    glLightfv(GL_LIGHT0, GL_DIFFUSE, new float[]{1.0f, 1.0f, 1.0f, 1.f}, 0);
    glLightfv(GL_LIGHT0, GL_SPECULAR, new float[]{0.0f, 0.0f, 0.0f, 0.f}, 0);
    glEnable(GL_LIGHT0);

    // Textures
    glGenTextures(NUMBER_TEXTURES, textures, 0);

    // 0
    glBindTexture(GL_TEXTURE_2D, textures[0]);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_REPLACE);
    GLUtils.texImage2D(GL_TEXTURE_2D, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
        context.getResources(), R.drawable.cardback), 256, 256, true), 0);

    // 1
    glBindTexture(GL_TEXTURE_2D, textures[1]);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_REPLACE);
    GLUtils.texImage2D(GL_TEXTURE_2D, 0, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
        context.getResources(), R.drawable.cardfront), 256, 256, true), 0);

    // 2
    glBindTexture(GL_TEXTURE_2D, textures[2]);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_REPLACE);
    Bitmap originalDecal = BitmapFactory.decodeResource(context.getResources(), R.drawable.decal2);
    float ratio = originalDecal.getWidth() / originalDecal.getHeight();
    xScale = 1.0f;
    yScale = xScale / (ratio * 1.5f);
    GLUtils.texImage2D(GL_TEXTURE_2D, 0, originalDecal, 0);

    float[][] coordinates = {new float[]{1.0f, 1.5f, 0}, // 0.. top right
        new float[]{-1.0f, 1.5f, 0}, // 1.. top left
        new float[]{-1.0f, -1.5f, 0}, // 2.. bottom left
        new float[]{1.0f, -1.5f, 0}}; // 3.. bottom right

    vertexBuffer = ByteBuffer.allocateDirect(
        coordinates.length * VERTEX_DIMENSIONS * (Float.SIZE / Byte.SIZE)).order(
        ByteOrder.nativeOrder()).asFloatBuffer();

    decalVertexBuffer = ByteBuffer.allocateDirect(
        coordinates.length * VERTEX_DIMENSIONS * (Float.SIZE / Byte.SIZE)).order(
        ByteOrder.nativeOrder()).asFloatBuffer();

    for (int i = 0; i < coordinates.length; i++) {
      float[] vertex = coordinates[i];
      float[] decalVertex = new float[]{vertex[0] * xScale * FRAME_INSET,
          vertex[1] * yScale * FRAME_INSET, vertex[2]};
      for (int j = 0; j < VERTEX_DIMENSIONS; j++) {
        vertexBuffer.put(vertex[j]);
        decalVertexBuffer.put(decalVertex[j]);
      }
    }
    vertexBuffer.position(0);
    decalVertexBuffer.position(0);

    float[] textureCoordinates = {1.0f, 0.0f, // 0.. top right
        0.0f, 0.0f, // 1.. top left
        0.0f, 1.0f, // 2.. bottom left
        1.0f, 1.0f}; // 3.. bottom right

    textureCoordinatesBuffer = ByteBuffer.allocateDirect(
        textureCoordinates.length * (Float.SIZE / Byte.SIZE)).order(ByteOrder.nativeOrder())
        .asFloatBuffer();

    for (int i = 0; i < textureCoordinates.length; i++) {
      textureCoordinatesBuffer.put(textureCoordinates[i]);
    }
    textureCoordinatesBuffer.position(0);

    indexBuffer = ByteBuffer.allocateDirect(indices.length * (Short.SIZE / Byte.SIZE)).order(
        ByteOrder.nativeOrder()).asShortBuffer();;

    for (int i = 0; i < indices.length; i++) {
      indexBuffer.put(indices[i]);
    }
    indexBuffer.position(0);
    
  }

}
