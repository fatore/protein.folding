package javax.media.opengl;

import java.io.*;
import com.sun.gluegen.runtime.*;

/** <P> Composable pipeline which wraps an underlying {@link GL} implementation,
    providing error checking after each OpenGL method call. If an error occurs,
    causes a {@link GLException} to be thrown at exactly the point of failure.
    Sample code which installs this pipeline: </P>

<PRE>
     drawable.setGL(new DebugGL(drawable.getGL()));
</PRE>
*/
public class DebugGL implements GL
{
  public DebugGL(GL downstreamGL)
  {
    if (downstreamGL == null) {
      throw new IllegalArgumentException("null downstreamGL");
    }
    this.downstreamGL = downstreamGL;
    // Fetch GLContext object for better error checking (if possible)
    // FIXME: should probably put this method in GL rather than GLImpl
    if (downstreamGL instanceof com.sun.opengl.impl.GLImpl) {
      _context = ((com.sun.opengl.impl.GLImpl) downstreamGL).getContext();
    }
  }

  public  void glAccum(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glAccum(arg0,arg1);
    checkGLGetError("glAccum");
  }
  public  void glActiveStencilFaceEXT(int arg0)
  {
        checkContext();
downstreamGL.glActiveStencilFaceEXT(arg0);
    checkGLGetError("glActiveStencilFaceEXT");
  }
  public  void glActiveTexture(int arg0)
  {
        checkContext();
downstreamGL.glActiveTexture(arg0);
    checkGLGetError("glActiveTexture");
  }
  public  void glActiveVaryingNV(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glActiveVaryingNV(arg0,arg1);
    checkGLGetError("glActiveVaryingNV");
  }
  public  void glActiveVaryingNV(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glActiveVaryingNV(arg0,arg1,arg2);
    checkGLGetError("glActiveVaryingNV");
  }
  public  void glAlphaFragmentOp1ATI(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
        checkContext();
downstreamGL.glAlphaFragmentOp1ATI(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glAlphaFragmentOp1ATI");
  }
  public  void glAlphaFragmentOp2ATI(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8)
  {
        checkContext();
downstreamGL.glAlphaFragmentOp2ATI(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    checkGLGetError("glAlphaFragmentOp2ATI");
  }
  public  void glAlphaFragmentOp3ATI(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,int arg10,int arg11)
  {
        checkContext();
downstreamGL.glAlphaFragmentOp3ATI(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11);
    checkGLGetError("glAlphaFragmentOp3ATI");
  }
  public  void glAlphaFunc(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glAlphaFunc(arg0,arg1);
    checkGLGetError("glAlphaFunc");
  }
  public  void glApplyTextureEXT(int arg0)
  {
        checkContext();
downstreamGL.glApplyTextureEXT(arg0);
    checkGLGetError("glApplyTextureEXT");
  }
  public  boolean glAreProgramsResidentNV(int arg0,java.nio.IntBuffer arg1,java.nio.ByteBuffer arg2)
  {
        checkContext();
boolean _res = downstreamGL.glAreProgramsResidentNV(arg0,arg1,arg2);
    checkGLGetError("glAreProgramsResidentNV");
    return _res;
  }
  public  boolean glAreProgramsResidentNV(int arg0,int[] arg1,int arg2,byte[] arg3,int arg4)
  {
        checkContext();
boolean _res = downstreamGL.glAreProgramsResidentNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glAreProgramsResidentNV");
    return _res;
  }
  public  boolean glAreTexturesResident(int arg0,java.nio.IntBuffer arg1,java.nio.ByteBuffer arg2)
  {
        checkContext();
boolean _res = downstreamGL.glAreTexturesResident(arg0,arg1,arg2);
    checkGLGetError("glAreTexturesResident");
    return _res;
  }
  public  boolean glAreTexturesResident(int arg0,int[] arg1,int arg2,byte[] arg3,int arg4)
  {
        checkContext();
boolean _res = downstreamGL.glAreTexturesResident(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glAreTexturesResident");
    return _res;
  }
  public  void glArrayElement(int arg0)
  {
        checkContext();
downstreamGL.glArrayElement(arg0);
    checkGLGetError("glArrayElement");
  }
  public  void glArrayObjectATI(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
        checkContext();
downstreamGL.glArrayObjectATI(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glArrayObjectATI");
  }
  public  void glAsyncMarkerSGIX(int arg0)
  {
        checkContext();
downstreamGL.glAsyncMarkerSGIX(arg0);
    checkGLGetError("glAsyncMarkerSGIX");
  }
  public  void glAttachObjectARB(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glAttachObjectARB(arg0,arg1);
    checkGLGetError("glAttachObjectARB");
  }
  public  void glAttachShader(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glAttachShader(arg0,arg1);
    checkGLGetError("glAttachShader");
  }
  public  void glBegin(int arg0)
  {
        checkContext();
downstreamGL.glBegin(arg0);
    insideBeginEndPair = true;
    // NOTE: can't check glGetError(); it's not allowed inside glBegin/glEnd pair
  }
  public  void glBeginFragmentShaderATI()
  {
        checkContext();
downstreamGL.glBeginFragmentShaderATI();
    checkGLGetError("glBeginFragmentShaderATI");
  }
  public  void glBeginOcclusionQueryNV(int arg0)
  {
        checkContext();
downstreamGL.glBeginOcclusionQueryNV(arg0);
    checkGLGetError("glBeginOcclusionQueryNV");
  }
  public  void glBeginQuery(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glBeginQuery(arg0,arg1);
    checkGLGetError("glBeginQuery");
  }
  public  void glBeginQueryARB(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glBeginQueryARB(arg0,arg1);
    checkGLGetError("glBeginQueryARB");
  }
  public  void glBeginTransformFeedbackNV(int arg0)
  {
        checkContext();
downstreamGL.glBeginTransformFeedbackNV(arg0);
    checkGLGetError("glBeginTransformFeedbackNV");
  }
  public  void glBeginVertexShaderEXT()
  {
        checkContext();
downstreamGL.glBeginVertexShaderEXT();
    checkGLGetError("glBeginVertexShaderEXT");
  }
  public  void glBindAttribLocation(int arg0,int arg1,java.lang.String arg2)
  {
        checkContext();
downstreamGL.glBindAttribLocation(arg0,arg1,arg2);
    checkGLGetError("glBindAttribLocation");
  }
  public  void glBindAttribLocationARB(int arg0,int arg1,java.lang.String arg2)
  {
        checkContext();
downstreamGL.glBindAttribLocationARB(arg0,arg1,arg2);
    checkGLGetError("glBindAttribLocationARB");
  }
  public  void glBindBuffer(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glBindBuffer(arg0,arg1);
    checkGLGetError("glBindBuffer");
  }
  public  void glBindBufferARB(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glBindBufferARB(arg0,arg1);
    checkGLGetError("glBindBufferARB");
  }
  public  void glBindBufferBaseNV(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glBindBufferBaseNV(arg0,arg1,arg2);
    checkGLGetError("glBindBufferBaseNV");
  }
  public  void glBindBufferOffsetNV(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glBindBufferOffsetNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glBindBufferOffsetNV");
  }
  public  void glBindBufferRangeNV(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glBindBufferRangeNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glBindBufferRangeNV");
  }
  public  void glBindFragDataLocationEXT(int arg0,int arg1,java.nio.ByteBuffer arg2)
  {
        checkContext();
downstreamGL.glBindFragDataLocationEXT(arg0,arg1,arg2);
    checkGLGetError("glBindFragDataLocationEXT");
  }
  public  void glBindFragDataLocationEXT(int arg0,int arg1,byte[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glBindFragDataLocationEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glBindFragDataLocationEXT");
  }
  public  void glBindFragmentShaderATI(int arg0)
  {
        checkContext();
downstreamGL.glBindFragmentShaderATI(arg0);
    checkGLGetError("glBindFragmentShaderATI");
  }
  public  void glBindFramebufferEXT(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glBindFramebufferEXT(arg0,arg1);
    checkGLGetError("glBindFramebufferEXT");
  }
  public  int glBindLightParameterEXT(int arg0,int arg1)
  {
        checkContext();
int _res = downstreamGL.glBindLightParameterEXT(arg0,arg1);
    checkGLGetError("glBindLightParameterEXT");
    return _res;
  }
  public  int glBindMaterialParameterEXT(int arg0,int arg1)
  {
        checkContext();
int _res = downstreamGL.glBindMaterialParameterEXT(arg0,arg1);
    checkGLGetError("glBindMaterialParameterEXT");
    return _res;
  }
  public  int glBindParameterEXT(int arg0)
  {
        checkContext();
int _res = downstreamGL.glBindParameterEXT(arg0);
    checkGLGetError("glBindParameterEXT");
    return _res;
  }
  public  void glBindProgramARB(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glBindProgramARB(arg0,arg1);
    checkGLGetError("glBindProgramARB");
  }
  public  void glBindProgramNV(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glBindProgramNV(arg0,arg1);
    checkGLGetError("glBindProgramNV");
  }
  public  void glBindRenderbufferEXT(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glBindRenderbufferEXT(arg0,arg1);
    checkGLGetError("glBindRenderbufferEXT");
  }
  public  int glBindTexGenParameterEXT(int arg0,int arg1,int arg2)
  {
        checkContext();
int _res = downstreamGL.glBindTexGenParameterEXT(arg0,arg1,arg2);
    checkGLGetError("glBindTexGenParameterEXT");
    return _res;
  }
  public  void glBindTexture(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glBindTexture(arg0,arg1);
    checkGLGetError("glBindTexture");
  }
  public  int glBindTextureUnitParameterEXT(int arg0,int arg1)
  {
        checkContext();
int _res = downstreamGL.glBindTextureUnitParameterEXT(arg0,arg1);
    checkGLGetError("glBindTextureUnitParameterEXT");
    return _res;
  }
  public  void glBindVertexArrayAPPLE(int arg0)
  {
        checkContext();
downstreamGL.glBindVertexArrayAPPLE(arg0);
    checkGLGetError("glBindVertexArrayAPPLE");
  }
  public  void glBindVertexShaderEXT(int arg0)
  {
        checkContext();
downstreamGL.glBindVertexShaderEXT(arg0);
    checkGLGetError("glBindVertexShaderEXT");
  }
  public  void glBitmap(int arg0,int arg1,float arg2,float arg3,float arg4,float arg5,java.nio.ByteBuffer arg6)
  {
        checkContext();
downstreamGL.glBitmap(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glBitmap");
  }
  public  void glBitmap(int arg0,int arg1,float arg2,float arg3,float arg4,float arg5,byte[] arg6,int arg7)
  {
        checkContext();
downstreamGL.glBitmap(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glBitmap");
  }
  public  void glBitmap(int arg0,int arg1,float arg2,float arg3,float arg4,float arg5,long arg6)
  {
        checkContext();
downstreamGL.glBitmap(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glBitmap");
  }
  public  void glBlendColor(float arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glBlendColor(arg0,arg1,arg2,arg3);
    checkGLGetError("glBlendColor");
  }
  public  void glBlendEquation(int arg0)
  {
        checkContext();
downstreamGL.glBlendEquation(arg0);
    checkGLGetError("glBlendEquation");
  }
  public  void glBlendEquationSeparate(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glBlendEquationSeparate(arg0,arg1);
    checkGLGetError("glBlendEquationSeparate");
  }
  public  void glBlendEquationSeparateEXT(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glBlendEquationSeparateEXT(arg0,arg1);
    checkGLGetError("glBlendEquationSeparateEXT");
  }
  public  void glBlendFunc(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glBlendFunc(arg0,arg1);
    checkGLGetError("glBlendFunc");
  }
  public  void glBlendFuncSeparate(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glBlendFuncSeparate(arg0,arg1,arg2,arg3);
    checkGLGetError("glBlendFuncSeparate");
  }
  public  void glBlendFuncSeparateEXT(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glBlendFuncSeparateEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glBlendFuncSeparateEXT");
  }
  public  void glBlendFuncSeparateINGR(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glBlendFuncSeparateINGR(arg0,arg1,arg2,arg3);
    checkGLGetError("glBlendFuncSeparateINGR");
  }
  public  void glBlitFramebufferEXT(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9)
  {
        checkContext();
downstreamGL.glBlitFramebufferEXT(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    checkGLGetError("glBlitFramebufferEXT");
  }
  public  void glBufferData(int arg0,int arg1,java.nio.Buffer arg2,int arg3)
  {
        checkContext();
downstreamGL.glBufferData(arg0,arg1,arg2,arg3);
    checkGLGetError("glBufferData");
  }
  public  void glBufferDataARB(int arg0,int arg1,java.nio.Buffer arg2,int arg3)
  {
        checkContext();
downstreamGL.glBufferDataARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glBufferDataARB");
  }
  public  void glBufferParameteriAPPLE(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glBufferParameteriAPPLE(arg0,arg1,arg2);
    checkGLGetError("glBufferParameteriAPPLE");
  }
  public  int glBufferRegionEnabled()
  {
        checkContext();
int _res = downstreamGL.glBufferRegionEnabled();
    checkGLGetError("glBufferRegionEnabled");
    return _res;
  }
  public  void glBufferSubData(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
        checkContext();
downstreamGL.glBufferSubData(arg0,arg1,arg2,arg3);
    checkGLGetError("glBufferSubData");
  }
  public  void glBufferSubDataARB(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
        checkContext();
downstreamGL.glBufferSubDataARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glBufferSubDataARB");
  }
  public  void glCallList(int arg0)
  {
        checkContext();
downstreamGL.glCallList(arg0);
    checkGLGetError("glCallList");
  }
  public  void glCallLists(int arg0,int arg1,java.nio.Buffer arg2)
  {
        checkContext();
downstreamGL.glCallLists(arg0,arg1,arg2);
    checkGLGetError("glCallLists");
  }
  public  int glCheckFramebufferStatusEXT(int arg0)
  {
        checkContext();
int _res = downstreamGL.glCheckFramebufferStatusEXT(arg0);
    checkGLGetError("glCheckFramebufferStatusEXT");
    return _res;
  }
  public  void glClampColorARB(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glClampColorARB(arg0,arg1);
    checkGLGetError("glClampColorARB");
  }
  public  void glClear(int arg0)
  {
        checkContext();
downstreamGL.glClear(arg0);
    checkGLGetError("glClear");
  }
  public  void glClearAccum(float arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glClearAccum(arg0,arg1,arg2,arg3);
    checkGLGetError("glClearAccum");
  }
  public  void glClearColor(float arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glClearColor(arg0,arg1,arg2,arg3);
    checkGLGetError("glClearColor");
  }
  public  void glClearColorIiEXT(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glClearColorIiEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glClearColorIiEXT");
  }
  public  void glClearColorIuiEXT(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glClearColorIuiEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glClearColorIuiEXT");
  }
  public  void glClearDepth(double arg0)
  {
        checkContext();
downstreamGL.glClearDepth(arg0);
    checkGLGetError("glClearDepth");
  }
  public  void glClearDepthdNV(double arg0)
  {
        checkContext();
downstreamGL.glClearDepthdNV(arg0);
    checkGLGetError("glClearDepthdNV");
  }
  public  void glClearIndex(float arg0)
  {
        checkContext();
downstreamGL.glClearIndex(arg0);
    checkGLGetError("glClearIndex");
  }
  public  void glClearStencil(int arg0)
  {
        checkContext();
downstreamGL.glClearStencil(arg0);
    checkGLGetError("glClearStencil");
  }
  public  void glClientActiveTexture(int arg0)
  {
        checkContext();
downstreamGL.glClientActiveTexture(arg0);
    checkGLGetError("glClientActiveTexture");
  }
  public  void glClientActiveVertexStreamATI(int arg0)
  {
        checkContext();
downstreamGL.glClientActiveVertexStreamATI(arg0);
    checkGLGetError("glClientActiveVertexStreamATI");
  }
  public  void glClipPlane(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glClipPlane(arg0,arg1);
    checkGLGetError("glClipPlane");
  }
  public  void glClipPlane(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glClipPlane(arg0,arg1,arg2);
    checkGLGetError("glClipPlane");
  }
  public  void glColor3b(byte arg0,byte arg1,byte arg2)
  {
        checkContext();
downstreamGL.glColor3b(arg0,arg1,arg2);
    checkGLGetError("glColor3b");
  }
  public  void glColor3bv(java.nio.ByteBuffer arg0)
  {
        checkContext();
downstreamGL.glColor3bv(arg0);
    checkGLGetError("glColor3bv");
  }
  public  void glColor3bv(byte[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor3bv(arg0,arg1);
    checkGLGetError("glColor3bv");
  }
  public  void glColor3d(double arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glColor3d(arg0,arg1,arg2);
    checkGLGetError("glColor3d");
  }
  public  void glColor3dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glColor3dv(arg0);
    checkGLGetError("glColor3dv");
  }
  public  void glColor3dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor3dv(arg0,arg1);
    checkGLGetError("glColor3dv");
  }
  public  void glColor3f(float arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glColor3f(arg0,arg1,arg2);
    checkGLGetError("glColor3f");
  }
  public  void glColor3fVertex3fSUN(float arg0,float arg1,float arg2,float arg3,float arg4,float arg5)
  {
        checkContext();
downstreamGL.glColor3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glColor3fVertex3fSUN");
  }
  public  void glColor3fVertex3fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glColor3fVertex3fvSUN(arg0,arg1);
    checkGLGetError("glColor3fVertex3fvSUN");
  }
  public  void glColor3fVertex3fvSUN(float[] arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glColor3fVertex3fvSUN(arg0,arg1,arg2,arg3);
    checkGLGetError("glColor3fVertex3fvSUN");
  }
  public  void glColor3fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glColor3fv(arg0);
    checkGLGetError("glColor3fv");
  }
  public  void glColor3fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor3fv(arg0,arg1);
    checkGLGetError("glColor3fv");
  }
  public  void glColor3hNV(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glColor3hNV(arg0,arg1,arg2);
    checkGLGetError("glColor3hNV");
  }
  public  void glColor3hvNV(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glColor3hvNV(arg0);
    checkGLGetError("glColor3hvNV");
  }
  public  void glColor3hvNV(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor3hvNV(arg0,arg1);
    checkGLGetError("glColor3hvNV");
  }
  public  void glColor3i(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glColor3i(arg0,arg1,arg2);
    checkGLGetError("glColor3i");
  }
  public  void glColor3iv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glColor3iv(arg0);
    checkGLGetError("glColor3iv");
  }
  public  void glColor3iv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor3iv(arg0,arg1);
    checkGLGetError("glColor3iv");
  }
  public  void glColor3s(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glColor3s(arg0,arg1,arg2);
    checkGLGetError("glColor3s");
  }
  public  void glColor3sv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glColor3sv(arg0);
    checkGLGetError("glColor3sv");
  }
  public  void glColor3sv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor3sv(arg0,arg1);
    checkGLGetError("glColor3sv");
  }
  public  void glColor3ub(byte arg0,byte arg1,byte arg2)
  {
        checkContext();
downstreamGL.glColor3ub(arg0,arg1,arg2);
    checkGLGetError("glColor3ub");
  }
  public  void glColor3ubv(java.nio.ByteBuffer arg0)
  {
        checkContext();
downstreamGL.glColor3ubv(arg0);
    checkGLGetError("glColor3ubv");
  }
  public  void glColor3ubv(byte[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor3ubv(arg0,arg1);
    checkGLGetError("glColor3ubv");
  }
  public  void glColor3ui(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glColor3ui(arg0,arg1,arg2);
    checkGLGetError("glColor3ui");
  }
  public  void glColor3uiv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glColor3uiv(arg0);
    checkGLGetError("glColor3uiv");
  }
  public  void glColor3uiv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor3uiv(arg0,arg1);
    checkGLGetError("glColor3uiv");
  }
  public  void glColor3us(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glColor3us(arg0,arg1,arg2);
    checkGLGetError("glColor3us");
  }
  public  void glColor3usv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glColor3usv(arg0);
    checkGLGetError("glColor3usv");
  }
  public  void glColor3usv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor3usv(arg0,arg1);
    checkGLGetError("glColor3usv");
  }
  public  void glColor4b(byte arg0,byte arg1,byte arg2,byte arg3)
  {
        checkContext();
downstreamGL.glColor4b(arg0,arg1,arg2,arg3);
    checkGLGetError("glColor4b");
  }
  public  void glColor4bv(java.nio.ByteBuffer arg0)
  {
        checkContext();
downstreamGL.glColor4bv(arg0);
    checkGLGetError("glColor4bv");
  }
  public  void glColor4bv(byte[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor4bv(arg0,arg1);
    checkGLGetError("glColor4bv");
  }
  public  void glColor4d(double arg0,double arg1,double arg2,double arg3)
  {
        checkContext();
downstreamGL.glColor4d(arg0,arg1,arg2,arg3);
    checkGLGetError("glColor4d");
  }
  public  void glColor4dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glColor4dv(arg0);
    checkGLGetError("glColor4dv");
  }
  public  void glColor4dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor4dv(arg0,arg1);
    checkGLGetError("glColor4dv");
  }
  public  void glColor4f(float arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glColor4f(arg0,arg1,arg2,arg3);
    checkGLGetError("glColor4f");
  }
  public  void glColor4fNormal3fVertex3fSUN(float arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7,float arg8,float arg9)
  {
        checkContext();
downstreamGL.glColor4fNormal3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    checkGLGetError("glColor4fNormal3fVertex3fSUN");
  }
  public  void glColor4fNormal3fVertex3fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glColor4fNormal3fVertex3fvSUN(arg0,arg1,arg2);
    checkGLGetError("glColor4fNormal3fVertex3fvSUN");
  }
  public  void glColor4fNormal3fVertex3fvSUN(float[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glColor4fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glColor4fNormal3fVertex3fvSUN");
  }
  public  void glColor4fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glColor4fv(arg0);
    checkGLGetError("glColor4fv");
  }
  public  void glColor4fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor4fv(arg0,arg1);
    checkGLGetError("glColor4fv");
  }
  public  void glColor4hNV(short arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glColor4hNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glColor4hNV");
  }
  public  void glColor4hvNV(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glColor4hvNV(arg0);
    checkGLGetError("glColor4hvNV");
  }
  public  void glColor4hvNV(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor4hvNV(arg0,arg1);
    checkGLGetError("glColor4hvNV");
  }
  public  void glColor4i(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glColor4i(arg0,arg1,arg2,arg3);
    checkGLGetError("glColor4i");
  }
  public  void glColor4iv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glColor4iv(arg0);
    checkGLGetError("glColor4iv");
  }
  public  void glColor4iv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor4iv(arg0,arg1);
    checkGLGetError("glColor4iv");
  }
  public  void glColor4s(short arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glColor4s(arg0,arg1,arg2,arg3);
    checkGLGetError("glColor4s");
  }
  public  void glColor4sv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glColor4sv(arg0);
    checkGLGetError("glColor4sv");
  }
  public  void glColor4sv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor4sv(arg0,arg1);
    checkGLGetError("glColor4sv");
  }
  public  void glColor4ub(byte arg0,byte arg1,byte arg2,byte arg3)
  {
        checkContext();
downstreamGL.glColor4ub(arg0,arg1,arg2,arg3);
    checkGLGetError("glColor4ub");
  }
  public  void glColor4ubVertex2fSUN(byte arg0,byte arg1,byte arg2,byte arg3,float arg4,float arg5)
  {
        checkContext();
downstreamGL.glColor4ubVertex2fSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glColor4ubVertex2fSUN");
  }
  public  void glColor4ubVertex2fvSUN(java.nio.ByteBuffer arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glColor4ubVertex2fvSUN(arg0,arg1);
    checkGLGetError("glColor4ubVertex2fvSUN");
  }
  public  void glColor4ubVertex2fvSUN(byte[] arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glColor4ubVertex2fvSUN(arg0,arg1,arg2,arg3);
    checkGLGetError("glColor4ubVertex2fvSUN");
  }
  public  void glColor4ubVertex3fSUN(byte arg0,byte arg1,byte arg2,byte arg3,float arg4,float arg5,float arg6)
  {
        checkContext();
downstreamGL.glColor4ubVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glColor4ubVertex3fSUN");
  }
  public  void glColor4ubVertex3fvSUN(java.nio.ByteBuffer arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glColor4ubVertex3fvSUN(arg0,arg1);
    checkGLGetError("glColor4ubVertex3fvSUN");
  }
  public  void glColor4ubVertex3fvSUN(byte[] arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glColor4ubVertex3fvSUN(arg0,arg1,arg2,arg3);
    checkGLGetError("glColor4ubVertex3fvSUN");
  }
  public  void glColor4ubv(java.nio.ByteBuffer arg0)
  {
        checkContext();
downstreamGL.glColor4ubv(arg0);
    checkGLGetError("glColor4ubv");
  }
  public  void glColor4ubv(byte[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor4ubv(arg0,arg1);
    checkGLGetError("glColor4ubv");
  }
  public  void glColor4ui(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glColor4ui(arg0,arg1,arg2,arg3);
    checkGLGetError("glColor4ui");
  }
  public  void glColor4uiv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glColor4uiv(arg0);
    checkGLGetError("glColor4uiv");
  }
  public  void glColor4uiv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor4uiv(arg0,arg1);
    checkGLGetError("glColor4uiv");
  }
  public  void glColor4us(short arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glColor4us(arg0,arg1,arg2,arg3);
    checkGLGetError("glColor4us");
  }
  public  void glColor4usv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glColor4usv(arg0);
    checkGLGetError("glColor4usv");
  }
  public  void glColor4usv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glColor4usv(arg0,arg1);
    checkGLGetError("glColor4usv");
  }
  public  void glColorFragmentOp1ATI(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6)
  {
        checkContext();
downstreamGL.glColorFragmentOp1ATI(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glColorFragmentOp1ATI");
  }
  public  void glColorFragmentOp2ATI(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9)
  {
        checkContext();
downstreamGL.glColorFragmentOp2ATI(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    checkGLGetError("glColorFragmentOp2ATI");
  }
  public  void glColorFragmentOp3ATI(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,int arg10,int arg11,int arg12)
  {
        checkContext();
downstreamGL.glColorFragmentOp3ATI(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12);
    checkGLGetError("glColorFragmentOp3ATI");
  }
  public  void glColorMask(boolean arg0,boolean arg1,boolean arg2,boolean arg3)
  {
        checkContext();
downstreamGL.glColorMask(arg0,arg1,arg2,arg3);
    checkGLGetError("glColorMask");
  }
  public  void glColorMaskIndexedEXT(int arg0,boolean arg1,boolean arg2,boolean arg3,boolean arg4)
  {
        checkContext();
downstreamGL.glColorMaskIndexedEXT(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glColorMaskIndexedEXT");
  }
  public  void glColorMaterial(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glColorMaterial(arg0,arg1);
    checkGLGetError("glColorMaterial");
  }
  public  void glColorPointer(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
        checkContext();
downstreamGL.glColorPointer(arg0,arg1,arg2,arg3);
    checkGLGetError("glColorPointer");
  }
  public  void glColorPointer(int arg0,int arg1,int arg2,long arg3)
  {
        checkContext();
downstreamGL.glColorPointer(arg0,arg1,arg2,arg3);
    checkGLGetError("glColorPointer");
  }
  public  void glColorSubTable(int arg0,int arg1,int arg2,int arg3,int arg4,java.nio.Buffer arg5)
  {
        checkContext();
downstreamGL.glColorSubTable(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glColorSubTable");
  }
  public  void glColorSubTable(int arg0,int arg1,int arg2,int arg3,int arg4,long arg5)
  {
        checkContext();
downstreamGL.glColorSubTable(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glColorSubTable");
  }
  public  void glColorTable(int arg0,int arg1,int arg2,int arg3,int arg4,java.nio.Buffer arg5)
  {
        checkContext();
downstreamGL.glColorTable(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glColorTable");
  }
  public  void glColorTable(int arg0,int arg1,int arg2,int arg3,int arg4,long arg5)
  {
        checkContext();
downstreamGL.glColorTable(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glColorTable");
  }
  public  void glColorTableEXT(int arg0,int arg1,int arg2,int arg3,int arg4,java.nio.Buffer arg5)
  {
        checkContext();
downstreamGL.glColorTableEXT(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glColorTableEXT");
  }
  public  void glColorTableParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glColorTableParameterfv(arg0,arg1,arg2);
    checkGLGetError("glColorTableParameterfv");
  }
  public  void glColorTableParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glColorTableParameterfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glColorTableParameterfv");
  }
  public  void glColorTableParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glColorTableParameteriv(arg0,arg1,arg2);
    checkGLGetError("glColorTableParameteriv");
  }
  public  void glColorTableParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glColorTableParameteriv(arg0,arg1,arg2,arg3);
    checkGLGetError("glColorTableParameteriv");
  }
  public  void glCombinerInputNV(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
        checkContext();
downstreamGL.glCombinerInputNV(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glCombinerInputNV");
  }
  public  void glCombinerOutputNV(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,boolean arg7,boolean arg8,boolean arg9)
  {
        checkContext();
downstreamGL.glCombinerOutputNV(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    checkGLGetError("glCombinerOutputNV");
  }
  public  void glCombinerParameterfNV(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glCombinerParameterfNV(arg0,arg1);
    checkGLGetError("glCombinerParameterfNV");
  }
  public  void glCombinerParameterfvNV(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glCombinerParameterfvNV(arg0,arg1);
    checkGLGetError("glCombinerParameterfvNV");
  }
  public  void glCombinerParameterfvNV(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glCombinerParameterfvNV(arg0,arg1,arg2);
    checkGLGetError("glCombinerParameterfvNV");
  }
  public  void glCombinerParameteriNV(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glCombinerParameteriNV(arg0,arg1);
    checkGLGetError("glCombinerParameteriNV");
  }
  public  void glCombinerParameterivNV(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glCombinerParameterivNV(arg0,arg1);
    checkGLGetError("glCombinerParameterivNV");
  }
  public  void glCombinerParameterivNV(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glCombinerParameterivNV(arg0,arg1,arg2);
    checkGLGetError("glCombinerParameterivNV");
  }
  public  void glCombinerStageParameterfvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glCombinerStageParameterfvNV(arg0,arg1,arg2);
    checkGLGetError("glCombinerStageParameterfvNV");
  }
  public  void glCombinerStageParameterfvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glCombinerStageParameterfvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glCombinerStageParameterfvNV");
  }
  public  void glCompileShader(int arg0)
  {
        checkContext();
downstreamGL.glCompileShader(arg0);
    checkGLGetError("glCompileShader");
  }
  public  void glCompileShaderARB(int arg0)
  {
        checkContext();
downstreamGL.glCompileShaderARB(arg0);
    checkGLGetError("glCompileShaderARB");
  }
  public  void glCompressedTexImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,java.nio.Buffer arg6)
  {
        checkContext();
downstreamGL.glCompressedTexImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glCompressedTexImage1D");
  }
  public  void glCompressedTexImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,long arg6)
  {
        checkContext();
downstreamGL.glCompressedTexImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glCompressedTexImage1D");
  }
  public  void glCompressedTexImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,java.nio.Buffer arg7)
  {
        checkContext();
downstreamGL.glCompressedTexImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glCompressedTexImage2D");
  }
  public  void glCompressedTexImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,long arg7)
  {
        checkContext();
downstreamGL.glCompressedTexImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glCompressedTexImage2D");
  }
  public  void glCompressedTexImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,java.nio.Buffer arg8)
  {
        checkContext();
downstreamGL.glCompressedTexImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    checkGLGetError("glCompressedTexImage3D");
  }
  public  void glCompressedTexImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,long arg8)
  {
        checkContext();
downstreamGL.glCompressedTexImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    checkGLGetError("glCompressedTexImage3D");
  }
  public  void glCompressedTexSubImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,java.nio.Buffer arg6)
  {
        checkContext();
downstreamGL.glCompressedTexSubImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glCompressedTexSubImage1D");
  }
  public  void glCompressedTexSubImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,long arg6)
  {
        checkContext();
downstreamGL.glCompressedTexSubImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glCompressedTexSubImage1D");
  }
  public  void glCompressedTexSubImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,java.nio.Buffer arg8)
  {
        checkContext();
downstreamGL.glCompressedTexSubImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    checkGLGetError("glCompressedTexSubImage2D");
  }
  public  void glCompressedTexSubImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,long arg8)
  {
        checkContext();
downstreamGL.glCompressedTexSubImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    checkGLGetError("glCompressedTexSubImage2D");
  }
  public  void glCompressedTexSubImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,java.nio.Buffer arg10)
  {
        checkContext();
downstreamGL.glCompressedTexSubImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    checkGLGetError("glCompressedTexSubImage3D");
  }
  public  void glCompressedTexSubImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,long arg10)
  {
        checkContext();
downstreamGL.glCompressedTexSubImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    checkGLGetError("glCompressedTexSubImage3D");
  }
  public  void glConvolutionFilter1D(int arg0,int arg1,int arg2,int arg3,int arg4,java.nio.Buffer arg5)
  {
        checkContext();
downstreamGL.glConvolutionFilter1D(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glConvolutionFilter1D");
  }
  public  void glConvolutionFilter1D(int arg0,int arg1,int arg2,int arg3,int arg4,long arg5)
  {
        checkContext();
downstreamGL.glConvolutionFilter1D(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glConvolutionFilter1D");
  }
  public  void glConvolutionFilter2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,java.nio.Buffer arg6)
  {
        checkContext();
downstreamGL.glConvolutionFilter2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glConvolutionFilter2D");
  }
  public  void glConvolutionFilter2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,long arg6)
  {
        checkContext();
downstreamGL.glConvolutionFilter2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glConvolutionFilter2D");
  }
  public  void glConvolutionParameterf(int arg0,int arg1,float arg2)
  {
        checkContext();
downstreamGL.glConvolutionParameterf(arg0,arg1,arg2);
    checkGLGetError("glConvolutionParameterf");
  }
  public  void glConvolutionParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glConvolutionParameterfv(arg0,arg1,arg2);
    checkGLGetError("glConvolutionParameterfv");
  }
  public  void glConvolutionParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glConvolutionParameterfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glConvolutionParameterfv");
  }
  public  void glConvolutionParameteri(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glConvolutionParameteri(arg0,arg1,arg2);
    checkGLGetError("glConvolutionParameteri");
  }
  public  void glConvolutionParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glConvolutionParameteriv(arg0,arg1,arg2);
    checkGLGetError("glConvolutionParameteriv");
  }
  public  void glConvolutionParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glConvolutionParameteriv(arg0,arg1,arg2,arg3);
    checkGLGetError("glConvolutionParameteriv");
  }
  public  void glCopyColorSubTable(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glCopyColorSubTable(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glCopyColorSubTable");
  }
  public  void glCopyColorTable(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glCopyColorTable(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glCopyColorTable");
  }
  public  void glCopyConvolutionFilter1D(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glCopyConvolutionFilter1D(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glCopyConvolutionFilter1D");
  }
  public  void glCopyConvolutionFilter2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
        checkContext();
downstreamGL.glCopyConvolutionFilter2D(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glCopyConvolutionFilter2D");
  }
  public  void glCopyPixels(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glCopyPixels(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glCopyPixels");
  }
  public  void glCopyTexImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6)
  {
        checkContext();
downstreamGL.glCopyTexImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glCopyTexImage1D");
  }
  public  void glCopyTexImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7)
  {
        checkContext();
downstreamGL.glCopyTexImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glCopyTexImage2D");
  }
  public  void glCopyTexSubImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
        checkContext();
downstreamGL.glCopyTexSubImage1D(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glCopyTexSubImage1D");
  }
  public  void glCopyTexSubImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7)
  {
        checkContext();
downstreamGL.glCopyTexSubImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glCopyTexSubImage2D");
  }
  public  void glCopyTexSubImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8)
  {
        checkContext();
downstreamGL.glCopyTexSubImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    checkGLGetError("glCopyTexSubImage3D");
  }
  public  int glCreateProgram()
  {
        checkContext();
int _res = downstreamGL.glCreateProgram();
    checkGLGetError("glCreateProgram");
    return _res;
  }
  public  int glCreateProgramObjectARB()
  {
        checkContext();
int _res = downstreamGL.glCreateProgramObjectARB();
    checkGLGetError("glCreateProgramObjectARB");
    return _res;
  }
  public  int glCreateShader(int arg0)
  {
        checkContext();
int _res = downstreamGL.glCreateShader(arg0);
    checkGLGetError("glCreateShader");
    return _res;
  }
  public  int glCreateShaderObjectARB(int arg0)
  {
        checkContext();
int _res = downstreamGL.glCreateShaderObjectARB(arg0);
    checkGLGetError("glCreateShaderObjectARB");
    return _res;
  }
  public  void glCullFace(int arg0)
  {
        checkContext();
downstreamGL.glCullFace(arg0);
    checkGLGetError("glCullFace");
  }
  public  void glCullParameterdvEXT(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glCullParameterdvEXT(arg0,arg1);
    checkGLGetError("glCullParameterdvEXT");
  }
  public  void glCullParameterdvEXT(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glCullParameterdvEXT(arg0,arg1,arg2);
    checkGLGetError("glCullParameterdvEXT");
  }
  public  void glCullParameterfvEXT(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glCullParameterfvEXT(arg0,arg1);
    checkGLGetError("glCullParameterfvEXT");
  }
  public  void glCullParameterfvEXT(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glCullParameterfvEXT(arg0,arg1,arg2);
    checkGLGetError("glCullParameterfvEXT");
  }
  public  void glCurrentPaletteMatrixARB(int arg0)
  {
        checkContext();
downstreamGL.glCurrentPaletteMatrixARB(arg0);
    checkGLGetError("glCurrentPaletteMatrixARB");
  }
  public  void glDeformSGIX(int arg0)
  {
        checkContext();
downstreamGL.glDeformSGIX(arg0);
    checkGLGetError("glDeformSGIX");
  }
  public  void glDeformationMap3dSGIX(int arg0,double arg1,double arg2,int arg3,int arg4,double arg5,double arg6,int arg7,int arg8,double arg9,double arg10,int arg11,int arg12,java.nio.DoubleBuffer arg13)
  {
        checkContext();
downstreamGL.glDeformationMap3dSGIX(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13);
    checkGLGetError("glDeformationMap3dSGIX");
  }
  public  void glDeformationMap3dSGIX(int arg0,double arg1,double arg2,int arg3,int arg4,double arg5,double arg6,int arg7,int arg8,double arg9,double arg10,int arg11,int arg12,double[] arg13,int arg14)
  {
        checkContext();
downstreamGL.glDeformationMap3dSGIX(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14);
    checkGLGetError("glDeformationMap3dSGIX");
  }
  public  void glDeformationMap3fSGIX(int arg0,float arg1,float arg2,int arg3,int arg4,float arg5,float arg6,int arg7,int arg8,float arg9,float arg10,int arg11,int arg12,java.nio.FloatBuffer arg13)
  {
        checkContext();
downstreamGL.glDeformationMap3fSGIX(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13);
    checkGLGetError("glDeformationMap3fSGIX");
  }
  public  void glDeformationMap3fSGIX(int arg0,float arg1,float arg2,int arg3,int arg4,float arg5,float arg6,int arg7,int arg8,float arg9,float arg10,int arg11,int arg12,float[] arg13,int arg14)
  {
        checkContext();
downstreamGL.glDeformationMap3fSGIX(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14);
    checkGLGetError("glDeformationMap3fSGIX");
  }
  public  void glDeleteAsyncMarkersSGIX(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glDeleteAsyncMarkersSGIX(arg0,arg1);
    checkGLGetError("glDeleteAsyncMarkersSGIX");
  }
  public  void glDeleteBufferRegion(int arg0)
  {
        checkContext();
downstreamGL.glDeleteBufferRegion(arg0);
    checkGLGetError("glDeleteBufferRegion");
  }
  public  void glDeleteBuffers(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glDeleteBuffers(arg0,arg1);
    checkGLGetError("glDeleteBuffers");
  }
  public  void glDeleteBuffers(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glDeleteBuffers(arg0,arg1,arg2);
    checkGLGetError("glDeleteBuffers");
  }
  public  void glDeleteBuffersARB(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glDeleteBuffersARB(arg0,arg1);
    checkGLGetError("glDeleteBuffersARB");
  }
  public  void glDeleteBuffersARB(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glDeleteBuffersARB(arg0,arg1,arg2);
    checkGLGetError("glDeleteBuffersARB");
  }
  public  void glDeleteFencesAPPLE(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glDeleteFencesAPPLE(arg0,arg1);
    checkGLGetError("glDeleteFencesAPPLE");
  }
  public  void glDeleteFencesAPPLE(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glDeleteFencesAPPLE(arg0,arg1,arg2);
    checkGLGetError("glDeleteFencesAPPLE");
  }
  public  void glDeleteFencesNV(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glDeleteFencesNV(arg0,arg1);
    checkGLGetError("glDeleteFencesNV");
  }
  public  void glDeleteFencesNV(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glDeleteFencesNV(arg0,arg1,arg2);
    checkGLGetError("glDeleteFencesNV");
  }
  public  void glDeleteFragmentShaderATI(int arg0)
  {
        checkContext();
downstreamGL.glDeleteFragmentShaderATI(arg0);
    checkGLGetError("glDeleteFragmentShaderATI");
  }
  public  void glDeleteFramebuffersEXT(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glDeleteFramebuffersEXT(arg0,arg1);
    checkGLGetError("glDeleteFramebuffersEXT");
  }
  public  void glDeleteFramebuffersEXT(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glDeleteFramebuffersEXT(arg0,arg1,arg2);
    checkGLGetError("glDeleteFramebuffersEXT");
  }
  public  void glDeleteLists(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glDeleteLists(arg0,arg1);
    checkGLGetError("glDeleteLists");
  }
  public  void glDeleteObjectARB(int arg0)
  {
        checkContext();
downstreamGL.glDeleteObjectARB(arg0);
    checkGLGetError("glDeleteObjectARB");
  }
  public  void glDeleteOcclusionQueriesNV(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glDeleteOcclusionQueriesNV(arg0,arg1);
    checkGLGetError("glDeleteOcclusionQueriesNV");
  }
  public  void glDeleteOcclusionQueriesNV(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glDeleteOcclusionQueriesNV(arg0,arg1,arg2);
    checkGLGetError("glDeleteOcclusionQueriesNV");
  }
  public  void glDeleteProgram(int arg0)
  {
        checkContext();
downstreamGL.glDeleteProgram(arg0);
    checkGLGetError("glDeleteProgram");
  }
  public  void glDeleteProgramsARB(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glDeleteProgramsARB(arg0,arg1);
    checkGLGetError("glDeleteProgramsARB");
  }
  public  void glDeleteProgramsARB(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glDeleteProgramsARB(arg0,arg1,arg2);
    checkGLGetError("glDeleteProgramsARB");
  }
  public  void glDeleteProgramsNV(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glDeleteProgramsNV(arg0,arg1);
    checkGLGetError("glDeleteProgramsNV");
  }
  public  void glDeleteProgramsNV(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glDeleteProgramsNV(arg0,arg1,arg2);
    checkGLGetError("glDeleteProgramsNV");
  }
  public  void glDeleteQueries(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glDeleteQueries(arg0,arg1);
    checkGLGetError("glDeleteQueries");
  }
  public  void glDeleteQueries(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glDeleteQueries(arg0,arg1,arg2);
    checkGLGetError("glDeleteQueries");
  }
  public  void glDeleteQueriesARB(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glDeleteQueriesARB(arg0,arg1);
    checkGLGetError("glDeleteQueriesARB");
  }
  public  void glDeleteQueriesARB(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glDeleteQueriesARB(arg0,arg1,arg2);
    checkGLGetError("glDeleteQueriesARB");
  }
  public  void glDeleteRenderbuffersEXT(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glDeleteRenderbuffersEXT(arg0,arg1);
    checkGLGetError("glDeleteRenderbuffersEXT");
  }
  public  void glDeleteRenderbuffersEXT(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glDeleteRenderbuffersEXT(arg0,arg1,arg2);
    checkGLGetError("glDeleteRenderbuffersEXT");
  }
  public  void glDeleteShader(int arg0)
  {
        checkContext();
downstreamGL.glDeleteShader(arg0);
    checkGLGetError("glDeleteShader");
  }
  public  void glDeleteTextures(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glDeleteTextures(arg0,arg1);
    checkGLGetError("glDeleteTextures");
  }
  public  void glDeleteTextures(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glDeleteTextures(arg0,arg1,arg2);
    checkGLGetError("glDeleteTextures");
  }
  public  void glDeleteVertexArraysAPPLE(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glDeleteVertexArraysAPPLE(arg0,arg1);
    checkGLGetError("glDeleteVertexArraysAPPLE");
  }
  public  void glDeleteVertexArraysAPPLE(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glDeleteVertexArraysAPPLE(arg0,arg1,arg2);
    checkGLGetError("glDeleteVertexArraysAPPLE");
  }
  public  void glDeleteVertexShaderEXT(int arg0)
  {
        checkContext();
downstreamGL.glDeleteVertexShaderEXT(arg0);
    checkGLGetError("glDeleteVertexShaderEXT");
  }
  public  void glDepthBoundsEXT(double arg0,double arg1)
  {
        checkContext();
downstreamGL.glDepthBoundsEXT(arg0,arg1);
    checkGLGetError("glDepthBoundsEXT");
  }
  public  void glDepthBoundsdNV(double arg0,double arg1)
  {
        checkContext();
downstreamGL.glDepthBoundsdNV(arg0,arg1);
    checkGLGetError("glDepthBoundsdNV");
  }
  public  void glDepthFunc(int arg0)
  {
        checkContext();
downstreamGL.glDepthFunc(arg0);
    checkGLGetError("glDepthFunc");
  }
  public  void glDepthMask(boolean arg0)
  {
        checkContext();
downstreamGL.glDepthMask(arg0);
    checkGLGetError("glDepthMask");
  }
  public  void glDepthRange(double arg0,double arg1)
  {
        checkContext();
downstreamGL.glDepthRange(arg0,arg1);
    checkGLGetError("glDepthRange");
  }
  public  void glDepthRangedNV(double arg0,double arg1)
  {
        checkContext();
downstreamGL.glDepthRangedNV(arg0,arg1);
    checkGLGetError("glDepthRangedNV");
  }
  public  void glDetachObjectARB(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glDetachObjectARB(arg0,arg1);
    checkGLGetError("glDetachObjectARB");
  }
  public  void glDetachShader(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glDetachShader(arg0,arg1);
    checkGLGetError("glDetachShader");
  }
  public  void glDetailTexFuncSGIS(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glDetailTexFuncSGIS(arg0,arg1,arg2);
    checkGLGetError("glDetailTexFuncSGIS");
  }
  public  void glDetailTexFuncSGIS(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glDetailTexFuncSGIS(arg0,arg1,arg2,arg3);
    checkGLGetError("glDetailTexFuncSGIS");
  }
  public  void glDisable(int arg0)
  {
        checkContext();
downstreamGL.glDisable(arg0);
    checkGLGetError("glDisable");
  }
  public  void glDisableClientState(int arg0)
  {
        checkContext();
downstreamGL.glDisableClientState(arg0);
    checkGLGetError("glDisableClientState");
  }
  public  void glDisableIndexedEXT(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glDisableIndexedEXT(arg0,arg1);
    checkGLGetError("glDisableIndexedEXT");
  }
  public  void glDisableVariantClientStateEXT(int arg0)
  {
        checkContext();
downstreamGL.glDisableVariantClientStateEXT(arg0);
    checkGLGetError("glDisableVariantClientStateEXT");
  }
  public  void glDisableVertexAttribAPPLE(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glDisableVertexAttribAPPLE(arg0,arg1);
    checkGLGetError("glDisableVertexAttribAPPLE");
  }
  public  void glDisableVertexAttribArray(int arg0)
  {
        checkContext();
downstreamGL.glDisableVertexAttribArray(arg0);
    checkGLGetError("glDisableVertexAttribArray");
  }
  public  void glDisableVertexAttribArrayARB(int arg0)
  {
        checkContext();
downstreamGL.glDisableVertexAttribArrayARB(arg0);
    checkGLGetError("glDisableVertexAttribArrayARB");
  }
  public  void glDrawArrays(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glDrawArrays(arg0,arg1,arg2);
    checkGLGetError("glDrawArrays");
  }
  public  void glDrawArraysInstancedEXT(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glDrawArraysInstancedEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glDrawArraysInstancedEXT");
  }
  public  void glDrawBuffer(int arg0)
  {
        checkContext();
downstreamGL.glDrawBuffer(arg0);
    checkGLGetError("glDrawBuffer");
  }
  public  void glDrawBufferRegion(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6)
  {
        checkContext();
downstreamGL.glDrawBufferRegion(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glDrawBufferRegion");
  }
  public  void glDrawBuffers(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glDrawBuffers(arg0,arg1);
    checkGLGetError("glDrawBuffers");
  }
  public  void glDrawBuffers(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glDrawBuffers(arg0,arg1,arg2);
    checkGLGetError("glDrawBuffers");
  }
  public  void glDrawBuffersARB(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glDrawBuffersARB(arg0,arg1);
    checkGLGetError("glDrawBuffersARB");
  }
  public  void glDrawBuffersARB(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glDrawBuffersARB(arg0,arg1,arg2);
    checkGLGetError("glDrawBuffersARB");
  }
  public  void glDrawBuffersATI(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glDrawBuffersATI(arg0,arg1);
    checkGLGetError("glDrawBuffersATI");
  }
  public  void glDrawBuffersATI(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glDrawBuffersATI(arg0,arg1,arg2);
    checkGLGetError("glDrawBuffersATI");
  }
  public  void glDrawElementArrayAPPLE(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glDrawElementArrayAPPLE(arg0,arg1,arg2);
    checkGLGetError("glDrawElementArrayAPPLE");
  }
  public  void glDrawElementArrayATI(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glDrawElementArrayATI(arg0,arg1);
    checkGLGetError("glDrawElementArrayATI");
  }
  public  void glDrawElements(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
        checkContext();
downstreamGL.glDrawElements(arg0,arg1,arg2,arg3);
    checkGLGetError("glDrawElements");
  }
  public  void glDrawElements(int arg0,int arg1,int arg2,long arg3)
  {
        checkContext();
downstreamGL.glDrawElements(arg0,arg1,arg2,arg3);
    checkGLGetError("glDrawElements");
  }
  public  void glDrawElementsInstancedEXT(int arg0,int arg1,int arg2,java.nio.Buffer arg3,int arg4)
  {
        checkContext();
downstreamGL.glDrawElementsInstancedEXT(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glDrawElementsInstancedEXT");
  }
  public  void glDrawMeshArraysSUN(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glDrawMeshArraysSUN(arg0,arg1,arg2,arg3);
    checkGLGetError("glDrawMeshArraysSUN");
  }
  public  void glDrawPixels(int arg0,int arg1,int arg2,int arg3,java.nio.Buffer arg4)
  {
        checkContext();
downstreamGL.glDrawPixels(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glDrawPixels");
  }
  public  void glDrawPixels(int arg0,int arg1,int arg2,int arg3,long arg4)
  {
        checkContext();
downstreamGL.glDrawPixels(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glDrawPixels");
  }
  public  void glDrawRangeElementArrayAPPLE(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glDrawRangeElementArrayAPPLE(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glDrawRangeElementArrayAPPLE");
  }
  public  void glDrawRangeElementArrayATI(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glDrawRangeElementArrayATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glDrawRangeElementArrayATI");
  }
  public  void glDrawRangeElements(int arg0,int arg1,int arg2,int arg3,int arg4,java.nio.Buffer arg5)
  {
        checkContext();
downstreamGL.glDrawRangeElements(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glDrawRangeElements");
  }
  public  void glDrawRangeElements(int arg0,int arg1,int arg2,int arg3,int arg4,long arg5)
  {
        checkContext();
downstreamGL.glDrawRangeElements(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glDrawRangeElements");
  }
  public  void glEdgeFlag(boolean arg0)
  {
        checkContext();
downstreamGL.glEdgeFlag(arg0);
    checkGLGetError("glEdgeFlag");
  }
  public  void glEdgeFlagPointer(int arg0,java.nio.Buffer arg1)
  {
        checkContext();
downstreamGL.glEdgeFlagPointer(arg0,arg1);
    checkGLGetError("glEdgeFlagPointer");
  }
  public  void glEdgeFlagPointer(int arg0,long arg1)
  {
        checkContext();
downstreamGL.glEdgeFlagPointer(arg0,arg1);
    checkGLGetError("glEdgeFlagPointer");
  }
  public  void glEdgeFlagv(java.nio.ByteBuffer arg0)
  {
        checkContext();
downstreamGL.glEdgeFlagv(arg0);
    checkGLGetError("glEdgeFlagv");
  }
  public  void glEdgeFlagv(byte[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glEdgeFlagv(arg0,arg1);
    checkGLGetError("glEdgeFlagv");
  }
  public  void glElementPointerAPPLE(int arg0,java.nio.Buffer arg1)
  {
        checkContext();
downstreamGL.glElementPointerAPPLE(arg0,arg1);
    checkGLGetError("glElementPointerAPPLE");
  }
  public  void glElementPointerATI(int arg0,java.nio.Buffer arg1)
  {
        checkContext();
downstreamGL.glElementPointerATI(arg0,arg1);
    checkGLGetError("glElementPointerATI");
  }
  public  void glElementPointerATI(int arg0,long arg1)
  {
        checkContext();
downstreamGL.glElementPointerATI(arg0,arg1);
    checkGLGetError("glElementPointerATI");
  }
  public  void glEnable(int arg0)
  {
        checkContext();
downstreamGL.glEnable(arg0);
    checkGLGetError("glEnable");
  }
  public  void glEnableClientState(int arg0)
  {
        checkContext();
downstreamGL.glEnableClientState(arg0);
    checkGLGetError("glEnableClientState");
  }
  public  void glEnableIndexedEXT(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glEnableIndexedEXT(arg0,arg1);
    checkGLGetError("glEnableIndexedEXT");
  }
  public  void glEnableVariantClientStateEXT(int arg0)
  {
        checkContext();
downstreamGL.glEnableVariantClientStateEXT(arg0);
    checkGLGetError("glEnableVariantClientStateEXT");
  }
  public  void glEnableVertexAttribAPPLE(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glEnableVertexAttribAPPLE(arg0,arg1);
    checkGLGetError("glEnableVertexAttribAPPLE");
  }
  public  void glEnableVertexAttribArray(int arg0)
  {
        checkContext();
downstreamGL.glEnableVertexAttribArray(arg0);
    checkGLGetError("glEnableVertexAttribArray");
  }
  public  void glEnableVertexAttribArrayARB(int arg0)
  {
        checkContext();
downstreamGL.glEnableVertexAttribArrayARB(arg0);
    checkGLGetError("glEnableVertexAttribArrayARB");
  }
  public  void glEnd()
  {
        checkContext();
downstreamGL.glEnd();
    insideBeginEndPair = false;
    checkGLGetError("glEnd");
  }
  public  void glEndFragmentShaderATI()
  {
        checkContext();
downstreamGL.glEndFragmentShaderATI();
    checkGLGetError("glEndFragmentShaderATI");
  }
  public  void glEndList()
  {
        checkContext();
downstreamGL.glEndList();
    checkGLGetError("glEndList");
  }
  public  void glEndOcclusionQueryNV()
  {
        checkContext();
downstreamGL.glEndOcclusionQueryNV();
    checkGLGetError("glEndOcclusionQueryNV");
  }
  public  void glEndQuery(int arg0)
  {
        checkContext();
downstreamGL.glEndQuery(arg0);
    checkGLGetError("glEndQuery");
  }
  public  void glEndQueryARB(int arg0)
  {
        checkContext();
downstreamGL.glEndQueryARB(arg0);
    checkGLGetError("glEndQueryARB");
  }
  public  void glEndTransformFeedbackNV()
  {
        checkContext();
downstreamGL.glEndTransformFeedbackNV();
    checkGLGetError("glEndTransformFeedbackNV");
  }
  public  void glEndVertexShaderEXT()
  {
        checkContext();
downstreamGL.glEndVertexShaderEXT();
    checkGLGetError("glEndVertexShaderEXT");
  }
  public  void glEvalCoord1d(double arg0)
  {
        checkContext();
downstreamGL.glEvalCoord1d(arg0);
    checkGLGetError("glEvalCoord1d");
  }
  public  void glEvalCoord1dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glEvalCoord1dv(arg0);
    checkGLGetError("glEvalCoord1dv");
  }
  public  void glEvalCoord1dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glEvalCoord1dv(arg0,arg1);
    checkGLGetError("glEvalCoord1dv");
  }
  public  void glEvalCoord1f(float arg0)
  {
        checkContext();
downstreamGL.glEvalCoord1f(arg0);
    checkGLGetError("glEvalCoord1f");
  }
  public  void glEvalCoord1fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glEvalCoord1fv(arg0);
    checkGLGetError("glEvalCoord1fv");
  }
  public  void glEvalCoord1fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glEvalCoord1fv(arg0,arg1);
    checkGLGetError("glEvalCoord1fv");
  }
  public  void glEvalCoord2d(double arg0,double arg1)
  {
        checkContext();
downstreamGL.glEvalCoord2d(arg0,arg1);
    checkGLGetError("glEvalCoord2d");
  }
  public  void glEvalCoord2dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glEvalCoord2dv(arg0);
    checkGLGetError("glEvalCoord2dv");
  }
  public  void glEvalCoord2dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glEvalCoord2dv(arg0,arg1);
    checkGLGetError("glEvalCoord2dv");
  }
  public  void glEvalCoord2f(float arg0,float arg1)
  {
        checkContext();
downstreamGL.glEvalCoord2f(arg0,arg1);
    checkGLGetError("glEvalCoord2f");
  }
  public  void glEvalCoord2fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glEvalCoord2fv(arg0);
    checkGLGetError("glEvalCoord2fv");
  }
  public  void glEvalCoord2fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glEvalCoord2fv(arg0,arg1);
    checkGLGetError("glEvalCoord2fv");
  }
  public  void glEvalMapsNV(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glEvalMapsNV(arg0,arg1);
    checkGLGetError("glEvalMapsNV");
  }
  public  void glEvalMesh1(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glEvalMesh1(arg0,arg1,arg2);
    checkGLGetError("glEvalMesh1");
  }
  public  void glEvalMesh2(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glEvalMesh2(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glEvalMesh2");
  }
  public  void glEvalPoint1(int arg0)
  {
        checkContext();
downstreamGL.glEvalPoint1(arg0);
    checkGLGetError("glEvalPoint1");
  }
  public  void glEvalPoint2(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glEvalPoint2(arg0,arg1);
    checkGLGetError("glEvalPoint2");
  }
  public  void glExecuteProgramNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glExecuteProgramNV(arg0,arg1,arg2);
    checkGLGetError("glExecuteProgramNV");
  }
  public  void glExecuteProgramNV(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glExecuteProgramNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glExecuteProgramNV");
  }
  public  void glExtractComponentEXT(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glExtractComponentEXT(arg0,arg1,arg2);
    checkGLGetError("glExtractComponentEXT");
  }
  public  void glFeedbackBuffer(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glFeedbackBuffer(arg0,arg1,arg2);
    checkGLGetError("glFeedbackBuffer");
  }
  public  void glFinalCombinerInputNV(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glFinalCombinerInputNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glFinalCombinerInputNV");
  }
  public  void glFinish()
  {
        checkContext();
downstreamGL.glFinish();
    checkGLGetError("glFinish");
  }
  public  int glFinishAsyncSGIX(java.nio.IntBuffer arg0)
  {
        checkContext();
int _res = downstreamGL.glFinishAsyncSGIX(arg0);
    checkGLGetError("glFinishAsyncSGIX");
    return _res;
  }
  public  int glFinishAsyncSGIX(int[] arg0,int arg1)
  {
        checkContext();
int _res = downstreamGL.glFinishAsyncSGIX(arg0,arg1);
    checkGLGetError("glFinishAsyncSGIX");
    return _res;
  }
  public  void glFinishFenceAPPLE(int arg0)
  {
        checkContext();
downstreamGL.glFinishFenceAPPLE(arg0);
    checkGLGetError("glFinishFenceAPPLE");
  }
  public  void glFinishFenceNV(int arg0)
  {
        checkContext();
downstreamGL.glFinishFenceNV(arg0);
    checkGLGetError("glFinishFenceNV");
  }
  public  void glFinishObjectAPPLE(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glFinishObjectAPPLE(arg0,arg1);
    checkGLGetError("glFinishObjectAPPLE");
  }
  public  void glFinishRenderAPPLE()
  {
        checkContext();
downstreamGL.glFinishRenderAPPLE();
    checkGLGetError("glFinishRenderAPPLE");
  }
  public  void glFinishTextureSUNX()
  {
        checkContext();
downstreamGL.glFinishTextureSUNX();
    checkGLGetError("glFinishTextureSUNX");
  }
  public  void glFlush()
  {
        checkContext();
downstreamGL.glFlush();
    checkGLGetError("glFlush");
  }
  public  void glFlushMappedBufferRangeAPPLE(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glFlushMappedBufferRangeAPPLE(arg0,arg1,arg2);
    checkGLGetError("glFlushMappedBufferRangeAPPLE");
  }
  public  void glFlushPixelDataRangeNV(int arg0)
  {
        checkContext();
downstreamGL.glFlushPixelDataRangeNV(arg0);
    checkGLGetError("glFlushPixelDataRangeNV");
  }
  public  void glFlushRasterSGIX()
  {
        checkContext();
downstreamGL.glFlushRasterSGIX();
    checkGLGetError("glFlushRasterSGIX");
  }
  public  void glFlushRenderAPPLE()
  {
        checkContext();
downstreamGL.glFlushRenderAPPLE();
    checkGLGetError("glFlushRenderAPPLE");
  }
  public  void glFlushVertexArrayRangeAPPLE(int arg0,java.nio.Buffer arg1)
  {
        checkContext();
downstreamGL.glFlushVertexArrayRangeAPPLE(arg0,arg1);
    checkGLGetError("glFlushVertexArrayRangeAPPLE");
  }
  public  void glFlushVertexArrayRangeNV()
  {
        checkContext();
downstreamGL.glFlushVertexArrayRangeNV();
    checkGLGetError("glFlushVertexArrayRangeNV");
  }
  public  void glFogCoordPointer(int arg0,int arg1,java.nio.Buffer arg2)
  {
        checkContext();
downstreamGL.glFogCoordPointer(arg0,arg1,arg2);
    checkGLGetError("glFogCoordPointer");
  }
  public  void glFogCoordPointer(int arg0,int arg1,long arg2)
  {
        checkContext();
downstreamGL.glFogCoordPointer(arg0,arg1,arg2);
    checkGLGetError("glFogCoordPointer");
  }
  public  void glFogCoordPointerEXT(int arg0,int arg1,java.nio.Buffer arg2)
  {
        checkContext();
downstreamGL.glFogCoordPointerEXT(arg0,arg1,arg2);
    checkGLGetError("glFogCoordPointerEXT");
  }
  public  void glFogCoordPointerEXT(int arg0,int arg1,long arg2)
  {
        checkContext();
downstreamGL.glFogCoordPointerEXT(arg0,arg1,arg2);
    checkGLGetError("glFogCoordPointerEXT");
  }
  public  void glFogCoordd(double arg0)
  {
        checkContext();
downstreamGL.glFogCoordd(arg0);
    checkGLGetError("glFogCoordd");
  }
  public  void glFogCoorddEXT(double arg0)
  {
        checkContext();
downstreamGL.glFogCoorddEXT(arg0);
    checkGLGetError("glFogCoorddEXT");
  }
  public  void glFogCoorddv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glFogCoorddv(arg0);
    checkGLGetError("glFogCoorddv");
  }
  public  void glFogCoorddv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glFogCoorddv(arg0,arg1);
    checkGLGetError("glFogCoorddv");
  }
  public  void glFogCoorddvEXT(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glFogCoorddvEXT(arg0);
    checkGLGetError("glFogCoorddvEXT");
  }
  public  void glFogCoorddvEXT(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glFogCoorddvEXT(arg0,arg1);
    checkGLGetError("glFogCoorddvEXT");
  }
  public  void glFogCoordf(float arg0)
  {
        checkContext();
downstreamGL.glFogCoordf(arg0);
    checkGLGetError("glFogCoordf");
  }
  public  void glFogCoordfEXT(float arg0)
  {
        checkContext();
downstreamGL.glFogCoordfEXT(arg0);
    checkGLGetError("glFogCoordfEXT");
  }
  public  void glFogCoordfv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glFogCoordfv(arg0);
    checkGLGetError("glFogCoordfv");
  }
  public  void glFogCoordfv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glFogCoordfv(arg0,arg1);
    checkGLGetError("glFogCoordfv");
  }
  public  void glFogCoordfvEXT(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glFogCoordfvEXT(arg0);
    checkGLGetError("glFogCoordfvEXT");
  }
  public  void glFogCoordfvEXT(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glFogCoordfvEXT(arg0,arg1);
    checkGLGetError("glFogCoordfvEXT");
  }
  public  void glFogCoordhNV(short arg0)
  {
        checkContext();
downstreamGL.glFogCoordhNV(arg0);
    checkGLGetError("glFogCoordhNV");
  }
  public  void glFogCoordhvNV(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glFogCoordhvNV(arg0);
    checkGLGetError("glFogCoordhvNV");
  }
  public  void glFogCoordhvNV(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glFogCoordhvNV(arg0,arg1);
    checkGLGetError("glFogCoordhvNV");
  }
  public  void glFogFuncSGIS(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glFogFuncSGIS(arg0,arg1);
    checkGLGetError("glFogFuncSGIS");
  }
  public  void glFogFuncSGIS(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glFogFuncSGIS(arg0,arg1,arg2);
    checkGLGetError("glFogFuncSGIS");
  }
  public  void glFogf(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glFogf(arg0,arg1);
    checkGLGetError("glFogf");
  }
  public  void glFogfv(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glFogfv(arg0,arg1);
    checkGLGetError("glFogfv");
  }
  public  void glFogfv(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glFogfv(arg0,arg1,arg2);
    checkGLGetError("glFogfv");
  }
  public  void glFogi(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glFogi(arg0,arg1);
    checkGLGetError("glFogi");
  }
  public  void glFogiv(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glFogiv(arg0,arg1);
    checkGLGetError("glFogiv");
  }
  public  void glFogiv(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glFogiv(arg0,arg1,arg2);
    checkGLGetError("glFogiv");
  }
  public  void glFragmentColorMaterialSGIX(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glFragmentColorMaterialSGIX(arg0,arg1);
    checkGLGetError("glFragmentColorMaterialSGIX");
  }
  public  void glFragmentLightModelfSGIX(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glFragmentLightModelfSGIX(arg0,arg1);
    checkGLGetError("glFragmentLightModelfSGIX");
  }
  public  void glFragmentLightModelfvSGIX(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glFragmentLightModelfvSGIX(arg0,arg1);
    checkGLGetError("glFragmentLightModelfvSGIX");
  }
  public  void glFragmentLightModelfvSGIX(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glFragmentLightModelfvSGIX(arg0,arg1,arg2);
    checkGLGetError("glFragmentLightModelfvSGIX");
  }
  public  void glFragmentLightModeliSGIX(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glFragmentLightModeliSGIX(arg0,arg1);
    checkGLGetError("glFragmentLightModeliSGIX");
  }
  public  void glFragmentLightModelivSGIX(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glFragmentLightModelivSGIX(arg0,arg1);
    checkGLGetError("glFragmentLightModelivSGIX");
  }
  public  void glFragmentLightModelivSGIX(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glFragmentLightModelivSGIX(arg0,arg1,arg2);
    checkGLGetError("glFragmentLightModelivSGIX");
  }
  public  void glFragmentLightfSGIX(int arg0,int arg1,float arg2)
  {
        checkContext();
downstreamGL.glFragmentLightfSGIX(arg0,arg1,arg2);
    checkGLGetError("glFragmentLightfSGIX");
  }
  public  void glFragmentLightfvSGIX(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glFragmentLightfvSGIX(arg0,arg1,arg2);
    checkGLGetError("glFragmentLightfvSGIX");
  }
  public  void glFragmentLightfvSGIX(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glFragmentLightfvSGIX(arg0,arg1,arg2,arg3);
    checkGLGetError("glFragmentLightfvSGIX");
  }
  public  void glFragmentLightiSGIX(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glFragmentLightiSGIX(arg0,arg1,arg2);
    checkGLGetError("glFragmentLightiSGIX");
  }
  public  void glFragmentLightivSGIX(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glFragmentLightivSGIX(arg0,arg1,arg2);
    checkGLGetError("glFragmentLightivSGIX");
  }
  public  void glFragmentLightivSGIX(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glFragmentLightivSGIX(arg0,arg1,arg2,arg3);
    checkGLGetError("glFragmentLightivSGIX");
  }
  public  void glFragmentMaterialfSGIX(int arg0,int arg1,float arg2)
  {
        checkContext();
downstreamGL.glFragmentMaterialfSGIX(arg0,arg1,arg2);
    checkGLGetError("glFragmentMaterialfSGIX");
  }
  public  void glFragmentMaterialfvSGIX(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glFragmentMaterialfvSGIX(arg0,arg1,arg2);
    checkGLGetError("glFragmentMaterialfvSGIX");
  }
  public  void glFragmentMaterialfvSGIX(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glFragmentMaterialfvSGIX(arg0,arg1,arg2,arg3);
    checkGLGetError("glFragmentMaterialfvSGIX");
  }
  public  void glFragmentMaterialiSGIX(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glFragmentMaterialiSGIX(arg0,arg1,arg2);
    checkGLGetError("glFragmentMaterialiSGIX");
  }
  public  void glFragmentMaterialivSGIX(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glFragmentMaterialivSGIX(arg0,arg1,arg2);
    checkGLGetError("glFragmentMaterialivSGIX");
  }
  public  void glFragmentMaterialivSGIX(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glFragmentMaterialivSGIX(arg0,arg1,arg2,arg3);
    checkGLGetError("glFragmentMaterialivSGIX");
  }
  public  void glFrameZoomSGIX(int arg0)
  {
        checkContext();
downstreamGL.glFrameZoomSGIX(arg0);
    checkGLGetError("glFrameZoomSGIX");
  }
  public  void glFramebufferRenderbufferEXT(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glFramebufferRenderbufferEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glFramebufferRenderbufferEXT");
  }
  public  void glFramebufferTexture1DEXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glFramebufferTexture1DEXT(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glFramebufferTexture1DEXT");
  }
  public  void glFramebufferTexture2DEXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glFramebufferTexture2DEXT(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glFramebufferTexture2DEXT");
  }
  public  void glFramebufferTexture3DEXT(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
        checkContext();
downstreamGL.glFramebufferTexture3DEXT(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glFramebufferTexture3DEXT");
  }
  public  void glFramebufferTextureEXT(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glFramebufferTextureEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glFramebufferTextureEXT");
  }
  public  void glFramebufferTextureFaceEXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glFramebufferTextureFaceEXT(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glFramebufferTextureFaceEXT");
  }
  public  void glFramebufferTextureLayerEXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glFramebufferTextureLayerEXT(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glFramebufferTextureLayerEXT");
  }
  public  void glFreeObjectBufferATI(int arg0)
  {
        checkContext();
downstreamGL.glFreeObjectBufferATI(arg0);
    checkGLGetError("glFreeObjectBufferATI");
  }
  public  void glFrontFace(int arg0)
  {
        checkContext();
downstreamGL.glFrontFace(arg0);
    checkGLGetError("glFrontFace");
  }
  public  void glFrustum(double arg0,double arg1,double arg2,double arg3,double arg4,double arg5)
  {
        checkContext();
downstreamGL.glFrustum(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glFrustum");
  }
  public  int glGenAsyncMarkersSGIX(int arg0)
  {
        checkContext();
int _res = downstreamGL.glGenAsyncMarkersSGIX(arg0);
    checkGLGetError("glGenAsyncMarkersSGIX");
    return _res;
  }
  public  void glGenBuffers(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glGenBuffers(arg0,arg1);
    checkGLGetError("glGenBuffers");
  }
  public  void glGenBuffers(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGenBuffers(arg0,arg1,arg2);
    checkGLGetError("glGenBuffers");
  }
  public  void glGenBuffersARB(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glGenBuffersARB(arg0,arg1);
    checkGLGetError("glGenBuffersARB");
  }
  public  void glGenBuffersARB(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGenBuffersARB(arg0,arg1,arg2);
    checkGLGetError("glGenBuffersARB");
  }
  public  void glGenFencesAPPLE(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glGenFencesAPPLE(arg0,arg1);
    checkGLGetError("glGenFencesAPPLE");
  }
  public  void glGenFencesAPPLE(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGenFencesAPPLE(arg0,arg1,arg2);
    checkGLGetError("glGenFencesAPPLE");
  }
  public  void glGenFencesNV(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glGenFencesNV(arg0,arg1);
    checkGLGetError("glGenFencesNV");
  }
  public  void glGenFencesNV(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGenFencesNV(arg0,arg1,arg2);
    checkGLGetError("glGenFencesNV");
  }
  public  int glGenFragmentShadersATI(int arg0)
  {
        checkContext();
int _res = downstreamGL.glGenFragmentShadersATI(arg0);
    checkGLGetError("glGenFragmentShadersATI");
    return _res;
  }
  public  void glGenFramebuffersEXT(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glGenFramebuffersEXT(arg0,arg1);
    checkGLGetError("glGenFramebuffersEXT");
  }
  public  void glGenFramebuffersEXT(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGenFramebuffersEXT(arg0,arg1,arg2);
    checkGLGetError("glGenFramebuffersEXT");
  }
  public  int glGenLists(int arg0)
  {
        checkContext();
int _res = downstreamGL.glGenLists(arg0);
    checkGLGetError("glGenLists");
    return _res;
  }
  public  void glGenOcclusionQueriesNV(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glGenOcclusionQueriesNV(arg0,arg1);
    checkGLGetError("glGenOcclusionQueriesNV");
  }
  public  void glGenOcclusionQueriesNV(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGenOcclusionQueriesNV(arg0,arg1,arg2);
    checkGLGetError("glGenOcclusionQueriesNV");
  }
  public  void glGenProgramsARB(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glGenProgramsARB(arg0,arg1);
    checkGLGetError("glGenProgramsARB");
  }
  public  void glGenProgramsARB(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGenProgramsARB(arg0,arg1,arg2);
    checkGLGetError("glGenProgramsARB");
  }
  public  void glGenProgramsNV(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glGenProgramsNV(arg0,arg1);
    checkGLGetError("glGenProgramsNV");
  }
  public  void glGenProgramsNV(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGenProgramsNV(arg0,arg1,arg2);
    checkGLGetError("glGenProgramsNV");
  }
  public  void glGenQueries(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glGenQueries(arg0,arg1);
    checkGLGetError("glGenQueries");
  }
  public  void glGenQueries(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGenQueries(arg0,arg1,arg2);
    checkGLGetError("glGenQueries");
  }
  public  void glGenQueriesARB(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glGenQueriesARB(arg0,arg1);
    checkGLGetError("glGenQueriesARB");
  }
  public  void glGenQueriesARB(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGenQueriesARB(arg0,arg1,arg2);
    checkGLGetError("glGenQueriesARB");
  }
  public  void glGenRenderbuffersEXT(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glGenRenderbuffersEXT(arg0,arg1);
    checkGLGetError("glGenRenderbuffersEXT");
  }
  public  void glGenRenderbuffersEXT(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGenRenderbuffersEXT(arg0,arg1,arg2);
    checkGLGetError("glGenRenderbuffersEXT");
  }
  public  int glGenSymbolsEXT(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
int _res = downstreamGL.glGenSymbolsEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGenSymbolsEXT");
    return _res;
  }
  public  void glGenTextures(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glGenTextures(arg0,arg1);
    checkGLGetError("glGenTextures");
  }
  public  void glGenTextures(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGenTextures(arg0,arg1,arg2);
    checkGLGetError("glGenTextures");
  }
  public  void glGenVertexArraysAPPLE(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glGenVertexArraysAPPLE(arg0,arg1);
    checkGLGetError("glGenVertexArraysAPPLE");
  }
  public  void glGenVertexArraysAPPLE(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGenVertexArraysAPPLE(arg0,arg1,arg2);
    checkGLGetError("glGenVertexArraysAPPLE");
  }
  public  int glGenVertexShadersEXT(int arg0)
  {
        checkContext();
int _res = downstreamGL.glGenVertexShadersEXT(arg0);
    checkGLGetError("glGenVertexShadersEXT");
    return _res;
  }
  public  void glGenerateMipmapEXT(int arg0)
  {
        checkContext();
downstreamGL.glGenerateMipmapEXT(arg0);
    checkGLGetError("glGenerateMipmapEXT");
  }
  public  void glGetActiveAttrib(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4,java.nio.IntBuffer arg5,java.nio.ByteBuffer arg6)
  {
        checkContext();
downstreamGL.glGetActiveAttrib(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glGetActiveAttrib");
  }
  public  void glGetActiveAttrib(int arg0,int arg1,int arg2,int[] arg3,int arg4,int[] arg5,int arg6,int[] arg7,int arg8,byte[] arg9,int arg10)
  {
        checkContext();
downstreamGL.glGetActiveAttrib(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    checkGLGetError("glGetActiveAttrib");
  }
  public  void glGetActiveAttribARB(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4,java.nio.IntBuffer arg5,java.nio.ByteBuffer arg6)
  {
        checkContext();
downstreamGL.glGetActiveAttribARB(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glGetActiveAttribARB");
  }
  public  void glGetActiveAttribARB(int arg0,int arg1,int arg2,int[] arg3,int arg4,int[] arg5,int arg6,int[] arg7,int arg8,byte[] arg9,int arg10)
  {
        checkContext();
downstreamGL.glGetActiveAttribARB(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    checkGLGetError("glGetActiveAttribARB");
  }
  public  void glGetActiveUniform(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4,java.nio.IntBuffer arg5,java.nio.ByteBuffer arg6)
  {
        checkContext();
downstreamGL.glGetActiveUniform(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glGetActiveUniform");
  }
  public  void glGetActiveUniform(int arg0,int arg1,int arg2,int[] arg3,int arg4,int[] arg5,int arg6,int[] arg7,int arg8,byte[] arg9,int arg10)
  {
        checkContext();
downstreamGL.glGetActiveUniform(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    checkGLGetError("glGetActiveUniform");
  }
  public  void glGetActiveUniformARB(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4,java.nio.IntBuffer arg5,java.nio.ByteBuffer arg6)
  {
        checkContext();
downstreamGL.glGetActiveUniformARB(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glGetActiveUniformARB");
  }
  public  void glGetActiveUniformARB(int arg0,int arg1,int arg2,int[] arg3,int arg4,int[] arg5,int arg6,int[] arg7,int arg8,byte[] arg9,int arg10)
  {
        checkContext();
downstreamGL.glGetActiveUniformARB(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    checkGLGetError("glGetActiveUniformARB");
  }
  public  void glGetActiveVaryingNV(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4,java.nio.IntBuffer arg5,java.nio.ByteBuffer arg6)
  {
        checkContext();
downstreamGL.glGetActiveVaryingNV(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glGetActiveVaryingNV");
  }
  public  void glGetActiveVaryingNV(int arg0,int arg1,int arg2,int[] arg3,int arg4,int[] arg5,int arg6,int[] arg7,int arg8,byte[] arg9,int arg10)
  {
        checkContext();
downstreamGL.glGetActiveVaryingNV(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    checkGLGetError("glGetActiveVaryingNV");
  }
  public  void glGetArrayObjectfvATI(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetArrayObjectfvATI(arg0,arg1,arg2);
    checkGLGetError("glGetArrayObjectfvATI");
  }
  public  void glGetArrayObjectfvATI(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetArrayObjectfvATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetArrayObjectfvATI");
  }
  public  void glGetArrayObjectivATI(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetArrayObjectivATI(arg0,arg1,arg2);
    checkGLGetError("glGetArrayObjectivATI");
  }
  public  void glGetArrayObjectivATI(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetArrayObjectivATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetArrayObjectivATI");
  }
  public  void glGetAttachedObjectsARB(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.IntBuffer arg3)
  {
        checkContext();
downstreamGL.glGetAttachedObjectsARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetAttachedObjectsARB");
  }
  public  void glGetAttachedObjectsARB(int arg0,int arg1,int[] arg2,int arg3,int[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glGetAttachedObjectsARB(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glGetAttachedObjectsARB");
  }
  public  void glGetAttachedShaders(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.IntBuffer arg3)
  {
        checkContext();
downstreamGL.glGetAttachedShaders(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetAttachedShaders");
  }
  public  void glGetAttachedShaders(int arg0,int arg1,int[] arg2,int arg3,int[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glGetAttachedShaders(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glGetAttachedShaders");
  }
  public  int glGetAttribLocation(int arg0,java.lang.String arg1)
  {
        checkContext();
int _res = downstreamGL.glGetAttribLocation(arg0,arg1);
    checkGLGetError("glGetAttribLocation");
    return _res;
  }
  public  int glGetAttribLocationARB(int arg0,java.lang.String arg1)
  {
        checkContext();
int _res = downstreamGL.glGetAttribLocationARB(arg0,arg1);
    checkGLGetError("glGetAttribLocationARB");
    return _res;
  }
  public  void glGetBooleanIndexedvEXT(int arg0,int arg1,java.nio.ByteBuffer arg2)
  {
        checkContext();
downstreamGL.glGetBooleanIndexedvEXT(arg0,arg1,arg2);
    checkGLGetError("glGetBooleanIndexedvEXT");
  }
  public  void glGetBooleanIndexedvEXT(int arg0,int arg1,byte[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetBooleanIndexedvEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetBooleanIndexedvEXT");
  }
  public  void glGetBooleanv(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glGetBooleanv(arg0,arg1);
    checkGLGetError("glGetBooleanv");
  }
  public  void glGetBooleanv(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGetBooleanv(arg0,arg1,arg2);
    checkGLGetError("glGetBooleanv");
  }
  public  void glGetBufferParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetBufferParameteriv(arg0,arg1,arg2);
    checkGLGetError("glGetBufferParameteriv");
  }
  public  void glGetBufferParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetBufferParameteriv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetBufferParameteriv");
  }
  public  void glGetBufferParameterivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetBufferParameterivARB(arg0,arg1,arg2);
    checkGLGetError("glGetBufferParameterivARB");
  }
  public  void glGetBufferParameterivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetBufferParameterivARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetBufferParameterivARB");
  }
  public  void glGetBufferSubData(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
        checkContext();
downstreamGL.glGetBufferSubData(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetBufferSubData");
  }
  public  void glGetBufferSubDataARB(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
        checkContext();
downstreamGL.glGetBufferSubDataARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetBufferSubDataARB");
  }
  public  void glGetClipPlane(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glGetClipPlane(arg0,arg1);
    checkGLGetError("glGetClipPlane");
  }
  public  void glGetClipPlane(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGetClipPlane(arg0,arg1,arg2);
    checkGLGetError("glGetClipPlane");
  }
  public  void glGetColorTable(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
        checkContext();
downstreamGL.glGetColorTable(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetColorTable");
  }
  public  void glGetColorTable(int arg0,int arg1,int arg2,long arg3)
  {
        checkContext();
downstreamGL.glGetColorTable(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetColorTable");
  }
  public  void glGetColorTableEXT(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
        checkContext();
downstreamGL.glGetColorTableEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetColorTableEXT");
  }
  public  void glGetColorTableParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetColorTableParameterfv(arg0,arg1,arg2);
    checkGLGetError("glGetColorTableParameterfv");
  }
  public  void glGetColorTableParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetColorTableParameterfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetColorTableParameterfv");
  }
  public  void glGetColorTableParameterfvEXT(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetColorTableParameterfvEXT(arg0,arg1,arg2);
    checkGLGetError("glGetColorTableParameterfvEXT");
  }
  public  void glGetColorTableParameterfvEXT(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetColorTableParameterfvEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetColorTableParameterfvEXT");
  }
  public  void glGetColorTableParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetColorTableParameteriv(arg0,arg1,arg2);
    checkGLGetError("glGetColorTableParameteriv");
  }
  public  void glGetColorTableParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetColorTableParameteriv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetColorTableParameteriv");
  }
  public  void glGetColorTableParameterivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetColorTableParameterivEXT(arg0,arg1,arg2);
    checkGLGetError("glGetColorTableParameterivEXT");
  }
  public  void glGetColorTableParameterivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetColorTableParameterivEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetColorTableParameterivEXT");
  }
  public  void glGetCombinerInputParameterfvNV(int arg0,int arg1,int arg2,int arg3,java.nio.FloatBuffer arg4)
  {
        checkContext();
downstreamGL.glGetCombinerInputParameterfvNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetCombinerInputParameterfvNV");
  }
  public  void glGetCombinerInputParameterfvNV(int arg0,int arg1,int arg2,int arg3,float[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glGetCombinerInputParameterfvNV(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glGetCombinerInputParameterfvNV");
  }
  public  void glGetCombinerInputParameterivNV(int arg0,int arg1,int arg2,int arg3,java.nio.IntBuffer arg4)
  {
        checkContext();
downstreamGL.glGetCombinerInputParameterivNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetCombinerInputParameterivNV");
  }
  public  void glGetCombinerInputParameterivNV(int arg0,int arg1,int arg2,int arg3,int[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glGetCombinerInputParameterivNV(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glGetCombinerInputParameterivNV");
  }
  public  void glGetCombinerOutputParameterfvNV(int arg0,int arg1,int arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glGetCombinerOutputParameterfvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetCombinerOutputParameterfvNV");
  }
  public  void glGetCombinerOutputParameterfvNV(int arg0,int arg1,int arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glGetCombinerOutputParameterfvNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetCombinerOutputParameterfvNV");
  }
  public  void glGetCombinerOutputParameterivNV(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
        checkContext();
downstreamGL.glGetCombinerOutputParameterivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetCombinerOutputParameterivNV");
  }
  public  void glGetCombinerOutputParameterivNV(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glGetCombinerOutputParameterivNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetCombinerOutputParameterivNV");
  }
  public  void glGetCombinerStageParameterfvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetCombinerStageParameterfvNV(arg0,arg1,arg2);
    checkGLGetError("glGetCombinerStageParameterfvNV");
  }
  public  void glGetCombinerStageParameterfvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetCombinerStageParameterfvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetCombinerStageParameterfvNV");
  }
  public  void glGetCompressedTexImage(int arg0,int arg1,java.nio.Buffer arg2)
  {
        checkContext();
downstreamGL.glGetCompressedTexImage(arg0,arg1,arg2);
    checkGLGetError("glGetCompressedTexImage");
  }
  public  void glGetCompressedTexImage(int arg0,int arg1,long arg2)
  {
        checkContext();
downstreamGL.glGetCompressedTexImage(arg0,arg1,arg2);
    checkGLGetError("glGetCompressedTexImage");
  }
  public  void glGetConvolutionFilter(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
        checkContext();
downstreamGL.glGetConvolutionFilter(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetConvolutionFilter");
  }
  public  void glGetConvolutionFilter(int arg0,int arg1,int arg2,long arg3)
  {
        checkContext();
downstreamGL.glGetConvolutionFilter(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetConvolutionFilter");
  }
  public  void glGetConvolutionParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetConvolutionParameterfv(arg0,arg1,arg2);
    checkGLGetError("glGetConvolutionParameterfv");
  }
  public  void glGetConvolutionParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetConvolutionParameterfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetConvolutionParameterfv");
  }
  public  void glGetConvolutionParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetConvolutionParameteriv(arg0,arg1,arg2);
    checkGLGetError("glGetConvolutionParameteriv");
  }
  public  void glGetConvolutionParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetConvolutionParameteriv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetConvolutionParameteriv");
  }
  public  void glGetDetailTexFuncSGIS(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glGetDetailTexFuncSGIS(arg0,arg1);
    checkGLGetError("glGetDetailTexFuncSGIS");
  }
  public  void glGetDetailTexFuncSGIS(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGetDetailTexFuncSGIS(arg0,arg1,arg2);
    checkGLGetError("glGetDetailTexFuncSGIS");
  }
  public  void glGetDoublev(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glGetDoublev(arg0,arg1);
    checkGLGetError("glGetDoublev");
  }
  public  void glGetDoublev(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGetDoublev(arg0,arg1,arg2);
    checkGLGetError("glGetDoublev");
  }
  public  int glGetError()
  {
        checkContext();
int _res = downstreamGL.glGetError();
    checkGLGetError("glGetError");
    return _res;
  }
  public  void glGetFenceivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetFenceivNV(arg0,arg1,arg2);
    checkGLGetError("glGetFenceivNV");
  }
  public  void glGetFenceivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetFenceivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetFenceivNV");
  }
  public  void glGetFinalCombinerInputParameterfvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetFinalCombinerInputParameterfvNV(arg0,arg1,arg2);
    checkGLGetError("glGetFinalCombinerInputParameterfvNV");
  }
  public  void glGetFinalCombinerInputParameterfvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetFinalCombinerInputParameterfvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetFinalCombinerInputParameterfvNV");
  }
  public  void glGetFinalCombinerInputParameterivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetFinalCombinerInputParameterivNV(arg0,arg1,arg2);
    checkGLGetError("glGetFinalCombinerInputParameterivNV");
  }
  public  void glGetFinalCombinerInputParameterivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetFinalCombinerInputParameterivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetFinalCombinerInputParameterivNV");
  }
  public  void glGetFloatv(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glGetFloatv(arg0,arg1);
    checkGLGetError("glGetFloatv");
  }
  public  void glGetFloatv(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGetFloatv(arg0,arg1,arg2);
    checkGLGetError("glGetFloatv");
  }
  public  void glGetFogFuncSGIS(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glGetFogFuncSGIS(arg0);
    checkGLGetError("glGetFogFuncSGIS");
  }
  public  void glGetFogFuncSGIS(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glGetFogFuncSGIS(arg0,arg1);
    checkGLGetError("glGetFogFuncSGIS");
  }
  public  int glGetFragDataLocationEXT(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
int _res = downstreamGL.glGetFragDataLocationEXT(arg0,arg1);
    checkGLGetError("glGetFragDataLocationEXT");
    return _res;
  }
  public  int glGetFragDataLocationEXT(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
int _res = downstreamGL.glGetFragDataLocationEXT(arg0,arg1,arg2);
    checkGLGetError("glGetFragDataLocationEXT");
    return _res;
  }
  public  void glGetFragmentLightfvSGIX(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetFragmentLightfvSGIX(arg0,arg1,arg2);
    checkGLGetError("glGetFragmentLightfvSGIX");
  }
  public  void glGetFragmentLightfvSGIX(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetFragmentLightfvSGIX(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetFragmentLightfvSGIX");
  }
  public  void glGetFragmentLightivSGIX(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetFragmentLightivSGIX(arg0,arg1,arg2);
    checkGLGetError("glGetFragmentLightivSGIX");
  }
  public  void glGetFragmentLightivSGIX(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetFragmentLightivSGIX(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetFragmentLightivSGIX");
  }
  public  void glGetFragmentMaterialfvSGIX(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetFragmentMaterialfvSGIX(arg0,arg1,arg2);
    checkGLGetError("glGetFragmentMaterialfvSGIX");
  }
  public  void glGetFragmentMaterialfvSGIX(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetFragmentMaterialfvSGIX(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetFragmentMaterialfvSGIX");
  }
  public  void glGetFragmentMaterialivSGIX(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetFragmentMaterialivSGIX(arg0,arg1,arg2);
    checkGLGetError("glGetFragmentMaterialivSGIX");
  }
  public  void glGetFragmentMaterialivSGIX(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetFragmentMaterialivSGIX(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetFragmentMaterialivSGIX");
  }
  public  void glGetFramebufferAttachmentParameterivEXT(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
        checkContext();
downstreamGL.glGetFramebufferAttachmentParameterivEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetFramebufferAttachmentParameterivEXT");
  }
  public  void glGetFramebufferAttachmentParameterivEXT(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glGetFramebufferAttachmentParameterivEXT(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetFramebufferAttachmentParameterivEXT");
  }
  public  int glGetHandleARB(int arg0)
  {
        checkContext();
int _res = downstreamGL.glGetHandleARB(arg0);
    checkGLGetError("glGetHandleARB");
    return _res;
  }
  public  void glGetHistogram(int arg0,boolean arg1,int arg2,int arg3,java.nio.Buffer arg4)
  {
        checkContext();
downstreamGL.glGetHistogram(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetHistogram");
  }
  public  void glGetHistogram(int arg0,boolean arg1,int arg2,int arg3,long arg4)
  {
        checkContext();
downstreamGL.glGetHistogram(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetHistogram");
  }
  public  void glGetHistogramParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetHistogramParameterfv(arg0,arg1,arg2);
    checkGLGetError("glGetHistogramParameterfv");
  }
  public  void glGetHistogramParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetHistogramParameterfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetHistogramParameterfv");
  }
  public  void glGetHistogramParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetHistogramParameteriv(arg0,arg1,arg2);
    checkGLGetError("glGetHistogramParameteriv");
  }
  public  void glGetHistogramParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetHistogramParameteriv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetHistogramParameteriv");
  }
  public  void glGetImageTransformParameterfvHP(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetImageTransformParameterfvHP(arg0,arg1,arg2);
    checkGLGetError("glGetImageTransformParameterfvHP");
  }
  public  void glGetImageTransformParameterfvHP(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetImageTransformParameterfvHP(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetImageTransformParameterfvHP");
  }
  public  void glGetImageTransformParameterivHP(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetImageTransformParameterivHP(arg0,arg1,arg2);
    checkGLGetError("glGetImageTransformParameterivHP");
  }
  public  void glGetImageTransformParameterivHP(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetImageTransformParameterivHP(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetImageTransformParameterivHP");
  }
  public  void glGetInfoLogARB(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.ByteBuffer arg3)
  {
        checkContext();
downstreamGL.glGetInfoLogARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetInfoLogARB");
  }
  public  void glGetInfoLogARB(int arg0,int arg1,int[] arg2,int arg3,byte[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glGetInfoLogARB(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glGetInfoLogARB");
  }
  public  int glGetInstrumentsSGIX()
  {
        checkContext();
int _res = downstreamGL.glGetInstrumentsSGIX();
    checkGLGetError("glGetInstrumentsSGIX");
    return _res;
  }
  public  void glGetIntegerIndexedvEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetIntegerIndexedvEXT(arg0,arg1,arg2);
    checkGLGetError("glGetIntegerIndexedvEXT");
  }
  public  void glGetIntegerIndexedvEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetIntegerIndexedvEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetIntegerIndexedvEXT");
  }
  public  void glGetIntegerv(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glGetIntegerv(arg0,arg1);
    checkGLGetError("glGetIntegerv");
  }
  public  void glGetIntegerv(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGetIntegerv(arg0,arg1,arg2);
    checkGLGetError("glGetIntegerv");
  }
  public  void glGetInvariantBooleanvEXT(int arg0,int arg1,java.nio.ByteBuffer arg2)
  {
        checkContext();
downstreamGL.glGetInvariantBooleanvEXT(arg0,arg1,arg2);
    checkGLGetError("glGetInvariantBooleanvEXT");
  }
  public  void glGetInvariantBooleanvEXT(int arg0,int arg1,byte[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetInvariantBooleanvEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetInvariantBooleanvEXT");
  }
  public  void glGetInvariantFloatvEXT(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetInvariantFloatvEXT(arg0,arg1,arg2);
    checkGLGetError("glGetInvariantFloatvEXT");
  }
  public  void glGetInvariantFloatvEXT(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetInvariantFloatvEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetInvariantFloatvEXT");
  }
  public  void glGetInvariantIntegervEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetInvariantIntegervEXT(arg0,arg1,arg2);
    checkGLGetError("glGetInvariantIntegervEXT");
  }
  public  void glGetInvariantIntegervEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetInvariantIntegervEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetInvariantIntegervEXT");
  }
  public  void glGetLightfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetLightfv(arg0,arg1,arg2);
    checkGLGetError("glGetLightfv");
  }
  public  void glGetLightfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetLightfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetLightfv");
  }
  public  void glGetLightiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetLightiv(arg0,arg1,arg2);
    checkGLGetError("glGetLightiv");
  }
  public  void glGetLightiv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetLightiv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetLightiv");
  }
  public  void glGetListParameterfvSGIX(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetListParameterfvSGIX(arg0,arg1,arg2);
    checkGLGetError("glGetListParameterfvSGIX");
  }
  public  void glGetListParameterfvSGIX(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetListParameterfvSGIX(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetListParameterfvSGIX");
  }
  public  void glGetListParameterivSGIX(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetListParameterivSGIX(arg0,arg1,arg2);
    checkGLGetError("glGetListParameterivSGIX");
  }
  public  void glGetListParameterivSGIX(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetListParameterivSGIX(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetListParameterivSGIX");
  }
  public  void glGetLocalConstantBooleanvEXT(int arg0,int arg1,java.nio.ByteBuffer arg2)
  {
        checkContext();
downstreamGL.glGetLocalConstantBooleanvEXT(arg0,arg1,arg2);
    checkGLGetError("glGetLocalConstantBooleanvEXT");
  }
  public  void glGetLocalConstantBooleanvEXT(int arg0,int arg1,byte[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetLocalConstantBooleanvEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetLocalConstantBooleanvEXT");
  }
  public  void glGetLocalConstantFloatvEXT(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetLocalConstantFloatvEXT(arg0,arg1,arg2);
    checkGLGetError("glGetLocalConstantFloatvEXT");
  }
  public  void glGetLocalConstantFloatvEXT(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetLocalConstantFloatvEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetLocalConstantFloatvEXT");
  }
  public  void glGetLocalConstantIntegervEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetLocalConstantIntegervEXT(arg0,arg1,arg2);
    checkGLGetError("glGetLocalConstantIntegervEXT");
  }
  public  void glGetLocalConstantIntegervEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetLocalConstantIntegervEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetLocalConstantIntegervEXT");
  }
  public  void glGetMapAttribParameterfvNV(int arg0,int arg1,int arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glGetMapAttribParameterfvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetMapAttribParameterfvNV");
  }
  public  void glGetMapAttribParameterfvNV(int arg0,int arg1,int arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glGetMapAttribParameterfvNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetMapAttribParameterfvNV");
  }
  public  void glGetMapAttribParameterivNV(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
        checkContext();
downstreamGL.glGetMapAttribParameterivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetMapAttribParameterivNV");
  }
  public  void glGetMapAttribParameterivNV(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glGetMapAttribParameterivNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetMapAttribParameterivNV");
  }
  public  void glGetMapControlPointsNV(int arg0,int arg1,int arg2,int arg3,int arg4,boolean arg5,java.nio.Buffer arg6)
  {
        checkContext();
downstreamGL.glGetMapControlPointsNV(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glGetMapControlPointsNV");
  }
  public  void glGetMapParameterfvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetMapParameterfvNV(arg0,arg1,arg2);
    checkGLGetError("glGetMapParameterfvNV");
  }
  public  void glGetMapParameterfvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetMapParameterfvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetMapParameterfvNV");
  }
  public  void glGetMapParameterivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetMapParameterivNV(arg0,arg1,arg2);
    checkGLGetError("glGetMapParameterivNV");
  }
  public  void glGetMapParameterivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetMapParameterivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetMapParameterivNV");
  }
  public  void glGetMapdv(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
        checkContext();
downstreamGL.glGetMapdv(arg0,arg1,arg2);
    checkGLGetError("glGetMapdv");
  }
  public  void glGetMapdv(int arg0,int arg1,double[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetMapdv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetMapdv");
  }
  public  void glGetMapfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetMapfv(arg0,arg1,arg2);
    checkGLGetError("glGetMapfv");
  }
  public  void glGetMapfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetMapfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetMapfv");
  }
  public  void glGetMapiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetMapiv(arg0,arg1,arg2);
    checkGLGetError("glGetMapiv");
  }
  public  void glGetMapiv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetMapiv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetMapiv");
  }
  public  void glGetMaterialfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetMaterialfv(arg0,arg1,arg2);
    checkGLGetError("glGetMaterialfv");
  }
  public  void glGetMaterialfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetMaterialfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetMaterialfv");
  }
  public  void glGetMaterialiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetMaterialiv(arg0,arg1,arg2);
    checkGLGetError("glGetMaterialiv");
  }
  public  void glGetMaterialiv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetMaterialiv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetMaterialiv");
  }
  public  void glGetMinmax(int arg0,boolean arg1,int arg2,int arg3,java.nio.Buffer arg4)
  {
        checkContext();
downstreamGL.glGetMinmax(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetMinmax");
  }
  public  void glGetMinmax(int arg0,boolean arg1,int arg2,int arg3,long arg4)
  {
        checkContext();
downstreamGL.glGetMinmax(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetMinmax");
  }
  public  void glGetMinmaxParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetMinmaxParameterfv(arg0,arg1,arg2);
    checkGLGetError("glGetMinmaxParameterfv");
  }
  public  void glGetMinmaxParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetMinmaxParameterfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetMinmaxParameterfv");
  }
  public  void glGetMinmaxParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetMinmaxParameteriv(arg0,arg1,arg2);
    checkGLGetError("glGetMinmaxParameteriv");
  }
  public  void glGetMinmaxParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetMinmaxParameteriv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetMinmaxParameteriv");
  }
  public  void glGetObjectBufferfvATI(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetObjectBufferfvATI(arg0,arg1,arg2);
    checkGLGetError("glGetObjectBufferfvATI");
  }
  public  void glGetObjectBufferfvATI(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetObjectBufferfvATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetObjectBufferfvATI");
  }
  public  void glGetObjectBufferivATI(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetObjectBufferivATI(arg0,arg1,arg2);
    checkGLGetError("glGetObjectBufferivATI");
  }
  public  void glGetObjectBufferivATI(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetObjectBufferivATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetObjectBufferivATI");
  }
  public  void glGetObjectParameterfvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetObjectParameterfvARB(arg0,arg1,arg2);
    checkGLGetError("glGetObjectParameterfvARB");
  }
  public  void glGetObjectParameterfvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetObjectParameterfvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetObjectParameterfvARB");
  }
  public  void glGetObjectParameterivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetObjectParameterivARB(arg0,arg1,arg2);
    checkGLGetError("glGetObjectParameterivARB");
  }
  public  void glGetObjectParameterivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetObjectParameterivARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetObjectParameterivARB");
  }
  public  void glGetOcclusionQueryivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetOcclusionQueryivNV(arg0,arg1,arg2);
    checkGLGetError("glGetOcclusionQueryivNV");
  }
  public  void glGetOcclusionQueryivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetOcclusionQueryivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetOcclusionQueryivNV");
  }
  public  void glGetOcclusionQueryuivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetOcclusionQueryuivNV(arg0,arg1,arg2);
    checkGLGetError("glGetOcclusionQueryuivNV");
  }
  public  void glGetOcclusionQueryuivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetOcclusionQueryuivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetOcclusionQueryuivNV");
  }
  public  void glGetPixelMapfv(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glGetPixelMapfv(arg0,arg1);
    checkGLGetError("glGetPixelMapfv");
  }
  public  void glGetPixelMapfv(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGetPixelMapfv(arg0,arg1,arg2);
    checkGLGetError("glGetPixelMapfv");
  }
  public  void glGetPixelMapfv(int arg0,long arg1)
  {
        checkContext();
downstreamGL.glGetPixelMapfv(arg0,arg1);
    checkGLGetError("glGetPixelMapfv");
  }
  public  void glGetPixelMapuiv(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glGetPixelMapuiv(arg0,arg1);
    checkGLGetError("glGetPixelMapuiv");
  }
  public  void glGetPixelMapuiv(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGetPixelMapuiv(arg0,arg1,arg2);
    checkGLGetError("glGetPixelMapuiv");
  }
  public  void glGetPixelMapuiv(int arg0,long arg1)
  {
        checkContext();
downstreamGL.glGetPixelMapuiv(arg0,arg1);
    checkGLGetError("glGetPixelMapuiv");
  }
  public  void glGetPixelMapusv(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glGetPixelMapusv(arg0,arg1);
    checkGLGetError("glGetPixelMapusv");
  }
  public  void glGetPixelMapusv(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGetPixelMapusv(arg0,arg1,arg2);
    checkGLGetError("glGetPixelMapusv");
  }
  public  void glGetPixelMapusv(int arg0,long arg1)
  {
        checkContext();
downstreamGL.glGetPixelMapusv(arg0,arg1);
    checkGLGetError("glGetPixelMapusv");
  }
  public  void glGetPixelTexGenParameterfvSGIS(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glGetPixelTexGenParameterfvSGIS(arg0,arg1);
    checkGLGetError("glGetPixelTexGenParameterfvSGIS");
  }
  public  void glGetPixelTexGenParameterfvSGIS(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGetPixelTexGenParameterfvSGIS(arg0,arg1,arg2);
    checkGLGetError("glGetPixelTexGenParameterfvSGIS");
  }
  public  void glGetPixelTexGenParameterivSGIS(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glGetPixelTexGenParameterivSGIS(arg0,arg1);
    checkGLGetError("glGetPixelTexGenParameterivSGIS");
  }
  public  void glGetPixelTexGenParameterivSGIS(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGetPixelTexGenParameterivSGIS(arg0,arg1,arg2);
    checkGLGetError("glGetPixelTexGenParameterivSGIS");
  }
  public  void glGetPolygonStipple(java.nio.ByteBuffer arg0)
  {
        checkContext();
downstreamGL.glGetPolygonStipple(arg0);
    checkGLGetError("glGetPolygonStipple");
  }
  public  void glGetPolygonStipple(byte[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glGetPolygonStipple(arg0,arg1);
    checkGLGetError("glGetPolygonStipple");
  }
  public  void glGetPolygonStipple(long arg0)
  {
        checkContext();
downstreamGL.glGetPolygonStipple(arg0);
    checkGLGetError("glGetPolygonStipple");
  }
  public  void glGetProgramEnvParameterIivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetProgramEnvParameterIivNV(arg0,arg1,arg2);
    checkGLGetError("glGetProgramEnvParameterIivNV");
  }
  public  void glGetProgramEnvParameterIivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetProgramEnvParameterIivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetProgramEnvParameterIivNV");
  }
  public  void glGetProgramEnvParameterIuivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetProgramEnvParameterIuivNV(arg0,arg1,arg2);
    checkGLGetError("glGetProgramEnvParameterIuivNV");
  }
  public  void glGetProgramEnvParameterIuivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetProgramEnvParameterIuivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetProgramEnvParameterIuivNV");
  }
  public  void glGetProgramEnvParameterdvARB(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
        checkContext();
downstreamGL.glGetProgramEnvParameterdvARB(arg0,arg1,arg2);
    checkGLGetError("glGetProgramEnvParameterdvARB");
  }
  public  void glGetProgramEnvParameterdvARB(int arg0,int arg1,double[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetProgramEnvParameterdvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetProgramEnvParameterdvARB");
  }
  public  void glGetProgramEnvParameterfvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetProgramEnvParameterfvARB(arg0,arg1,arg2);
    checkGLGetError("glGetProgramEnvParameterfvARB");
  }
  public  void glGetProgramEnvParameterfvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetProgramEnvParameterfvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetProgramEnvParameterfvARB");
  }
  public  void glGetProgramInfoLog(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.ByteBuffer arg3)
  {
        checkContext();
downstreamGL.glGetProgramInfoLog(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetProgramInfoLog");
  }
  public  void glGetProgramInfoLog(int arg0,int arg1,int[] arg2,int arg3,byte[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glGetProgramInfoLog(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glGetProgramInfoLog");
  }
  public  void glGetProgramLocalParameterIivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetProgramLocalParameterIivNV(arg0,arg1,arg2);
    checkGLGetError("glGetProgramLocalParameterIivNV");
  }
  public  void glGetProgramLocalParameterIivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetProgramLocalParameterIivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetProgramLocalParameterIivNV");
  }
  public  void glGetProgramLocalParameterIuivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetProgramLocalParameterIuivNV(arg0,arg1,arg2);
    checkGLGetError("glGetProgramLocalParameterIuivNV");
  }
  public  void glGetProgramLocalParameterIuivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetProgramLocalParameterIuivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetProgramLocalParameterIuivNV");
  }
  public  void glGetProgramLocalParameterdvARB(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
        checkContext();
downstreamGL.glGetProgramLocalParameterdvARB(arg0,arg1,arg2);
    checkGLGetError("glGetProgramLocalParameterdvARB");
  }
  public  void glGetProgramLocalParameterdvARB(int arg0,int arg1,double[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetProgramLocalParameterdvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetProgramLocalParameterdvARB");
  }
  public  void glGetProgramLocalParameterfvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetProgramLocalParameterfvARB(arg0,arg1,arg2);
    checkGLGetError("glGetProgramLocalParameterfvARB");
  }
  public  void glGetProgramLocalParameterfvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetProgramLocalParameterfvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetProgramLocalParameterfvARB");
  }
  public  void glGetProgramNamedParameterdvNV(int arg0,int arg1,java.lang.String arg2,java.nio.DoubleBuffer arg3)
  {
        checkContext();
downstreamGL.glGetProgramNamedParameterdvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetProgramNamedParameterdvNV");
  }
  public  void glGetProgramNamedParameterdvNV(int arg0,int arg1,java.lang.String arg2,double[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glGetProgramNamedParameterdvNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetProgramNamedParameterdvNV");
  }
  public  void glGetProgramNamedParameterfvNV(int arg0,int arg1,java.lang.String arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glGetProgramNamedParameterfvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetProgramNamedParameterfvNV");
  }
  public  void glGetProgramNamedParameterfvNV(int arg0,int arg1,java.lang.String arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glGetProgramNamedParameterfvNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetProgramNamedParameterfvNV");
  }
  public  void glGetProgramParameterdvNV(int arg0,int arg1,int arg2,java.nio.DoubleBuffer arg3)
  {
        checkContext();
downstreamGL.glGetProgramParameterdvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetProgramParameterdvNV");
  }
  public  void glGetProgramParameterdvNV(int arg0,int arg1,int arg2,double[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glGetProgramParameterdvNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetProgramParameterdvNV");
  }
  public  void glGetProgramParameterfvNV(int arg0,int arg1,int arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glGetProgramParameterfvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetProgramParameterfvNV");
  }
  public  void glGetProgramParameterfvNV(int arg0,int arg1,int arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glGetProgramParameterfvNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetProgramParameterfvNV");
  }
  public  void glGetProgramStringARB(int arg0,int arg1,java.nio.Buffer arg2)
  {
        checkContext();
downstreamGL.glGetProgramStringARB(arg0,arg1,arg2);
    checkGLGetError("glGetProgramStringARB");
  }
  public  void glGetProgramStringNV(int arg0,int arg1,java.nio.ByteBuffer arg2)
  {
        checkContext();
downstreamGL.glGetProgramStringNV(arg0,arg1,arg2);
    checkGLGetError("glGetProgramStringNV");
  }
  public  void glGetProgramStringNV(int arg0,int arg1,byte[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetProgramStringNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetProgramStringNV");
  }
  public  void glGetProgramiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetProgramiv(arg0,arg1,arg2);
    checkGLGetError("glGetProgramiv");
  }
  public  void glGetProgramiv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetProgramiv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetProgramiv");
  }
  public  void glGetProgramivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetProgramivARB(arg0,arg1,arg2);
    checkGLGetError("glGetProgramivARB");
  }
  public  void glGetProgramivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetProgramivARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetProgramivARB");
  }
  public  void glGetProgramivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetProgramivNV(arg0,arg1,arg2);
    checkGLGetError("glGetProgramivNV");
  }
  public  void glGetProgramivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetProgramivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetProgramivNV");
  }
  public  void glGetQueryObjecti64vEXT(int arg0,int arg1,java.nio.LongBuffer arg2)
  {
        checkContext();
downstreamGL.glGetQueryObjecti64vEXT(arg0,arg1,arg2);
    checkGLGetError("glGetQueryObjecti64vEXT");
  }
  public  void glGetQueryObjecti64vEXT(int arg0,int arg1,long[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetQueryObjecti64vEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetQueryObjecti64vEXT");
  }
  public  void glGetQueryObjectiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetQueryObjectiv(arg0,arg1,arg2);
    checkGLGetError("glGetQueryObjectiv");
  }
  public  void glGetQueryObjectiv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetQueryObjectiv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetQueryObjectiv");
  }
  public  void glGetQueryObjectivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetQueryObjectivARB(arg0,arg1,arg2);
    checkGLGetError("glGetQueryObjectivARB");
  }
  public  void glGetQueryObjectivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetQueryObjectivARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetQueryObjectivARB");
  }
  public  void glGetQueryObjectui64vEXT(int arg0,int arg1,java.nio.LongBuffer arg2)
  {
        checkContext();
downstreamGL.glGetQueryObjectui64vEXT(arg0,arg1,arg2);
    checkGLGetError("glGetQueryObjectui64vEXT");
  }
  public  void glGetQueryObjectui64vEXT(int arg0,int arg1,long[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetQueryObjectui64vEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetQueryObjectui64vEXT");
  }
  public  void glGetQueryObjectuiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetQueryObjectuiv(arg0,arg1,arg2);
    checkGLGetError("glGetQueryObjectuiv");
  }
  public  void glGetQueryObjectuiv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetQueryObjectuiv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetQueryObjectuiv");
  }
  public  void glGetQueryObjectuivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetQueryObjectuivARB(arg0,arg1,arg2);
    checkGLGetError("glGetQueryObjectuivARB");
  }
  public  void glGetQueryObjectuivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetQueryObjectuivARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetQueryObjectuivARB");
  }
  public  void glGetQueryiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetQueryiv(arg0,arg1,arg2);
    checkGLGetError("glGetQueryiv");
  }
  public  void glGetQueryiv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetQueryiv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetQueryiv");
  }
  public  void glGetQueryivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetQueryivARB(arg0,arg1,arg2);
    checkGLGetError("glGetQueryivARB");
  }
  public  void glGetQueryivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetQueryivARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetQueryivARB");
  }
  public  void glGetRenderbufferParameterivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetRenderbufferParameterivEXT(arg0,arg1,arg2);
    checkGLGetError("glGetRenderbufferParameterivEXT");
  }
  public  void glGetRenderbufferParameterivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetRenderbufferParameterivEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetRenderbufferParameterivEXT");
  }
  public  void glGetSeparableFilter(int arg0,int arg1,int arg2,java.nio.Buffer arg3,java.nio.Buffer arg4,java.nio.Buffer arg5)
  {
        checkContext();
downstreamGL.glGetSeparableFilter(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glGetSeparableFilter");
  }
  public  void glGetSeparableFilter(int arg0,int arg1,int arg2,long arg3,long arg4,long arg5)
  {
        checkContext();
downstreamGL.glGetSeparableFilter(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glGetSeparableFilter");
  }
  public  void glGetShaderInfoLog(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.ByteBuffer arg3)
  {
        checkContext();
downstreamGL.glGetShaderInfoLog(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetShaderInfoLog");
  }
  public  void glGetShaderInfoLog(int arg0,int arg1,int[] arg2,int arg3,byte[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glGetShaderInfoLog(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glGetShaderInfoLog");
  }
  public  void glGetShaderSource(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.ByteBuffer arg3)
  {
        checkContext();
downstreamGL.glGetShaderSource(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetShaderSource");
  }
  public  void glGetShaderSource(int arg0,int arg1,int[] arg2,int arg3,byte[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glGetShaderSource(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glGetShaderSource");
  }
  public  void glGetShaderSourceARB(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.ByteBuffer arg3)
  {
        checkContext();
downstreamGL.glGetShaderSourceARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetShaderSourceARB");
  }
  public  void glGetShaderSourceARB(int arg0,int arg1,int[] arg2,int arg3,byte[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glGetShaderSourceARB(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glGetShaderSourceARB");
  }
  public  void glGetShaderiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetShaderiv(arg0,arg1,arg2);
    checkGLGetError("glGetShaderiv");
  }
  public  void glGetShaderiv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetShaderiv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetShaderiv");
  }
  public  void glGetSharpenTexFuncSGIS(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glGetSharpenTexFuncSGIS(arg0,arg1);
    checkGLGetError("glGetSharpenTexFuncSGIS");
  }
  public  void glGetSharpenTexFuncSGIS(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGetSharpenTexFuncSGIS(arg0,arg1,arg2);
    checkGLGetError("glGetSharpenTexFuncSGIS");
  }
  public  java.lang.String glGetString(int arg0)
  {
        checkContext();
java.lang.String _res = downstreamGL.glGetString(arg0);
    checkGLGetError("glGetString");
    return _res;
  }
  public  void glGetTexBumpParameterfvATI(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glGetTexBumpParameterfvATI(arg0,arg1);
    checkGLGetError("glGetTexBumpParameterfvATI");
  }
  public  void glGetTexBumpParameterfvATI(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGetTexBumpParameterfvATI(arg0,arg1,arg2);
    checkGLGetError("glGetTexBumpParameterfvATI");
  }
  public  void glGetTexBumpParameterivATI(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glGetTexBumpParameterivATI(arg0,arg1);
    checkGLGetError("glGetTexBumpParameterivATI");
  }
  public  void glGetTexBumpParameterivATI(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glGetTexBumpParameterivATI(arg0,arg1,arg2);
    checkGLGetError("glGetTexBumpParameterivATI");
  }
  public  void glGetTexEnvfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetTexEnvfv(arg0,arg1,arg2);
    checkGLGetError("glGetTexEnvfv");
  }
  public  void glGetTexEnvfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetTexEnvfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetTexEnvfv");
  }
  public  void glGetTexEnviv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetTexEnviv(arg0,arg1,arg2);
    checkGLGetError("glGetTexEnviv");
  }
  public  void glGetTexEnviv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetTexEnviv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetTexEnviv");
  }
  public  void glGetTexFilterFuncSGIS(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetTexFilterFuncSGIS(arg0,arg1,arg2);
    checkGLGetError("glGetTexFilterFuncSGIS");
  }
  public  void glGetTexFilterFuncSGIS(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetTexFilterFuncSGIS(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetTexFilterFuncSGIS");
  }
  public  void glGetTexGendv(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
        checkContext();
downstreamGL.glGetTexGendv(arg0,arg1,arg2);
    checkGLGetError("glGetTexGendv");
  }
  public  void glGetTexGendv(int arg0,int arg1,double[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetTexGendv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetTexGendv");
  }
  public  void glGetTexGenfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetTexGenfv(arg0,arg1,arg2);
    checkGLGetError("glGetTexGenfv");
  }
  public  void glGetTexGenfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetTexGenfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetTexGenfv");
  }
  public  void glGetTexGeniv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetTexGeniv(arg0,arg1,arg2);
    checkGLGetError("glGetTexGeniv");
  }
  public  void glGetTexGeniv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetTexGeniv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetTexGeniv");
  }
  public  void glGetTexImage(int arg0,int arg1,int arg2,int arg3,java.nio.Buffer arg4)
  {
        checkContext();
downstreamGL.glGetTexImage(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetTexImage");
  }
  public  void glGetTexImage(int arg0,int arg1,int arg2,int arg3,long arg4)
  {
        checkContext();
downstreamGL.glGetTexImage(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetTexImage");
  }
  public  void glGetTexLevelParameterfv(int arg0,int arg1,int arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glGetTexLevelParameterfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetTexLevelParameterfv");
  }
  public  void glGetTexLevelParameterfv(int arg0,int arg1,int arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glGetTexLevelParameterfv(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetTexLevelParameterfv");
  }
  public  void glGetTexLevelParameteriv(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
        checkContext();
downstreamGL.glGetTexLevelParameteriv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetTexLevelParameteriv");
  }
  public  void glGetTexLevelParameteriv(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glGetTexLevelParameteriv(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetTexLevelParameteriv");
  }
  public  void glGetTexParameterIivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetTexParameterIivEXT(arg0,arg1,arg2);
    checkGLGetError("glGetTexParameterIivEXT");
  }
  public  void glGetTexParameterIivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetTexParameterIivEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetTexParameterIivEXT");
  }
  public  void glGetTexParameterIuivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetTexParameterIuivEXT(arg0,arg1,arg2);
    checkGLGetError("glGetTexParameterIuivEXT");
  }
  public  void glGetTexParameterIuivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetTexParameterIuivEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetTexParameterIuivEXT");
  }
  public  void glGetTexParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetTexParameterfv(arg0,arg1,arg2);
    checkGLGetError("glGetTexParameterfv");
  }
  public  void glGetTexParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetTexParameterfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetTexParameterfv");
  }
  public  void glGetTexParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetTexParameteriv(arg0,arg1,arg2);
    checkGLGetError("glGetTexParameteriv");
  }
  public  void glGetTexParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetTexParameteriv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetTexParameteriv");
  }
  public  void glGetTrackMatrixivNV(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
        checkContext();
downstreamGL.glGetTrackMatrixivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetTrackMatrixivNV");
  }
  public  void glGetTrackMatrixivNV(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glGetTrackMatrixivNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glGetTrackMatrixivNV");
  }
  public  void glGetTransformFeedbackVaryingNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetTransformFeedbackVaryingNV(arg0,arg1,arg2);
    checkGLGetError("glGetTransformFeedbackVaryingNV");
  }
  public  void glGetTransformFeedbackVaryingNV(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetTransformFeedbackVaryingNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetTransformFeedbackVaryingNV");
  }
  public  int glGetUniformBufferSizeEXT(int arg0,int arg1)
  {
        checkContext();
int _res = downstreamGL.glGetUniformBufferSizeEXT(arg0,arg1);
    checkGLGetError("glGetUniformBufferSizeEXT");
    return _res;
  }
  public  int glGetUniformLocation(int arg0,java.lang.String arg1)
  {
        checkContext();
int _res = downstreamGL.glGetUniformLocation(arg0,arg1);
    checkGLGetError("glGetUniformLocation");
    return _res;
  }
  public  int glGetUniformLocationARB(int arg0,java.lang.String arg1)
  {
        checkContext();
int _res = downstreamGL.glGetUniformLocationARB(arg0,arg1);
    checkGLGetError("glGetUniformLocationARB");
    return _res;
  }
  public  int glGetUniformOffsetEXT(int arg0,int arg1)
  {
        checkContext();
int _res = downstreamGL.glGetUniformOffsetEXT(arg0,arg1);
    checkGLGetError("glGetUniformOffsetEXT");
    return _res;
  }
  public  void glGetUniformfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetUniformfv(arg0,arg1,arg2);
    checkGLGetError("glGetUniformfv");
  }
  public  void glGetUniformfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetUniformfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetUniformfv");
  }
  public  void glGetUniformfvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetUniformfvARB(arg0,arg1,arg2);
    checkGLGetError("glGetUniformfvARB");
  }
  public  void glGetUniformfvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetUniformfvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetUniformfvARB");
  }
  public  void glGetUniformiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetUniformiv(arg0,arg1,arg2);
    checkGLGetError("glGetUniformiv");
  }
  public  void glGetUniformiv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetUniformiv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetUniformiv");
  }
  public  void glGetUniformivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetUniformivARB(arg0,arg1,arg2);
    checkGLGetError("glGetUniformivARB");
  }
  public  void glGetUniformivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetUniformivARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetUniformivARB");
  }
  public  void glGetUniformuivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetUniformuivEXT(arg0,arg1,arg2);
    checkGLGetError("glGetUniformuivEXT");
  }
  public  void glGetUniformuivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetUniformuivEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetUniformuivEXT");
  }
  public  void glGetVariantArrayObjectfvATI(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVariantArrayObjectfvATI(arg0,arg1,arg2);
    checkGLGetError("glGetVariantArrayObjectfvATI");
  }
  public  void glGetVariantArrayObjectfvATI(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVariantArrayObjectfvATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVariantArrayObjectfvATI");
  }
  public  void glGetVariantArrayObjectivATI(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVariantArrayObjectivATI(arg0,arg1,arg2);
    checkGLGetError("glGetVariantArrayObjectivATI");
  }
  public  void glGetVariantArrayObjectivATI(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVariantArrayObjectivATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVariantArrayObjectivATI");
  }
  public  void glGetVariantBooleanvEXT(int arg0,int arg1,java.nio.ByteBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVariantBooleanvEXT(arg0,arg1,arg2);
    checkGLGetError("glGetVariantBooleanvEXT");
  }
  public  void glGetVariantBooleanvEXT(int arg0,int arg1,byte[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVariantBooleanvEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVariantBooleanvEXT");
  }
  public  void glGetVariantFloatvEXT(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVariantFloatvEXT(arg0,arg1,arg2);
    checkGLGetError("glGetVariantFloatvEXT");
  }
  public  void glGetVariantFloatvEXT(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVariantFloatvEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVariantFloatvEXT");
  }
  public  void glGetVariantIntegervEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVariantIntegervEXT(arg0,arg1,arg2);
    checkGLGetError("glGetVariantIntegervEXT");
  }
  public  void glGetVariantIntegervEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVariantIntegervEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVariantIntegervEXT");
  }
  public  int glGetVaryingLocationNV(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
int _res = downstreamGL.glGetVaryingLocationNV(arg0,arg1);
    checkGLGetError("glGetVaryingLocationNV");
    return _res;
  }
  public  int glGetVaryingLocationNV(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
int _res = downstreamGL.glGetVaryingLocationNV(arg0,arg1,arg2);
    checkGLGetError("glGetVaryingLocationNV");
    return _res;
  }
  public  void glGetVertexAttribArrayObjectfvATI(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVertexAttribArrayObjectfvATI(arg0,arg1,arg2);
    checkGLGetError("glGetVertexAttribArrayObjectfvATI");
  }
  public  void glGetVertexAttribArrayObjectfvATI(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVertexAttribArrayObjectfvATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVertexAttribArrayObjectfvATI");
  }
  public  void glGetVertexAttribArrayObjectivATI(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVertexAttribArrayObjectivATI(arg0,arg1,arg2);
    checkGLGetError("glGetVertexAttribArrayObjectivATI");
  }
  public  void glGetVertexAttribArrayObjectivATI(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVertexAttribArrayObjectivATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVertexAttribArrayObjectivATI");
  }
  public  void glGetVertexAttribIivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVertexAttribIivEXT(arg0,arg1,arg2);
    checkGLGetError("glGetVertexAttribIivEXT");
  }
  public  void glGetVertexAttribIivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVertexAttribIivEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVertexAttribIivEXT");
  }
  public  void glGetVertexAttribIuivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVertexAttribIuivEXT(arg0,arg1,arg2);
    checkGLGetError("glGetVertexAttribIuivEXT");
  }
  public  void glGetVertexAttribIuivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVertexAttribIuivEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVertexAttribIuivEXT");
  }
  public  void glGetVertexAttribdv(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVertexAttribdv(arg0,arg1,arg2);
    checkGLGetError("glGetVertexAttribdv");
  }
  public  void glGetVertexAttribdv(int arg0,int arg1,double[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVertexAttribdv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVertexAttribdv");
  }
  public  void glGetVertexAttribdvARB(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVertexAttribdvARB(arg0,arg1,arg2);
    checkGLGetError("glGetVertexAttribdvARB");
  }
  public  void glGetVertexAttribdvARB(int arg0,int arg1,double[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVertexAttribdvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVertexAttribdvARB");
  }
  public  void glGetVertexAttribdvNV(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVertexAttribdvNV(arg0,arg1,arg2);
    checkGLGetError("glGetVertexAttribdvNV");
  }
  public  void glGetVertexAttribdvNV(int arg0,int arg1,double[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVertexAttribdvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVertexAttribdvNV");
  }
  public  void glGetVertexAttribfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVertexAttribfv(arg0,arg1,arg2);
    checkGLGetError("glGetVertexAttribfv");
  }
  public  void glGetVertexAttribfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVertexAttribfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVertexAttribfv");
  }
  public  void glGetVertexAttribfvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVertexAttribfvARB(arg0,arg1,arg2);
    checkGLGetError("glGetVertexAttribfvARB");
  }
  public  void glGetVertexAttribfvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVertexAttribfvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVertexAttribfvARB");
  }
  public  void glGetVertexAttribfvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVertexAttribfvNV(arg0,arg1,arg2);
    checkGLGetError("glGetVertexAttribfvNV");
  }
  public  void glGetVertexAttribfvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVertexAttribfvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVertexAttribfvNV");
  }
  public  void glGetVertexAttribiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVertexAttribiv(arg0,arg1,arg2);
    checkGLGetError("glGetVertexAttribiv");
  }
  public  void glGetVertexAttribiv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVertexAttribiv(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVertexAttribiv");
  }
  public  void glGetVertexAttribivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVertexAttribivARB(arg0,arg1,arg2);
    checkGLGetError("glGetVertexAttribivARB");
  }
  public  void glGetVertexAttribivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVertexAttribivARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVertexAttribivARB");
  }
  public  void glGetVertexAttribivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glGetVertexAttribivNV(arg0,arg1,arg2);
    checkGLGetError("glGetVertexAttribivNV");
  }
  public  void glGetVertexAttribivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glGetVertexAttribivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glGetVertexAttribivNV");
  }
  public  void glGlobalAlphaFactorbSUN(byte arg0)
  {
        checkContext();
downstreamGL.glGlobalAlphaFactorbSUN(arg0);
    checkGLGetError("glGlobalAlphaFactorbSUN");
  }
  public  void glGlobalAlphaFactordSUN(double arg0)
  {
        checkContext();
downstreamGL.glGlobalAlphaFactordSUN(arg0);
    checkGLGetError("glGlobalAlphaFactordSUN");
  }
  public  void glGlobalAlphaFactorfSUN(float arg0)
  {
        checkContext();
downstreamGL.glGlobalAlphaFactorfSUN(arg0);
    checkGLGetError("glGlobalAlphaFactorfSUN");
  }
  public  void glGlobalAlphaFactoriSUN(int arg0)
  {
        checkContext();
downstreamGL.glGlobalAlphaFactoriSUN(arg0);
    checkGLGetError("glGlobalAlphaFactoriSUN");
  }
  public  void glGlobalAlphaFactorsSUN(short arg0)
  {
        checkContext();
downstreamGL.glGlobalAlphaFactorsSUN(arg0);
    checkGLGetError("glGlobalAlphaFactorsSUN");
  }
  public  void glGlobalAlphaFactorubSUN(byte arg0)
  {
        checkContext();
downstreamGL.glGlobalAlphaFactorubSUN(arg0);
    checkGLGetError("glGlobalAlphaFactorubSUN");
  }
  public  void glGlobalAlphaFactoruiSUN(int arg0)
  {
        checkContext();
downstreamGL.glGlobalAlphaFactoruiSUN(arg0);
    checkGLGetError("glGlobalAlphaFactoruiSUN");
  }
  public  void glGlobalAlphaFactorusSUN(short arg0)
  {
        checkContext();
downstreamGL.glGlobalAlphaFactorusSUN(arg0);
    checkGLGetError("glGlobalAlphaFactorusSUN");
  }
  public  void glHint(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glHint(arg0,arg1);
    checkGLGetError("glHint");
  }
  public  void glHintPGI(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glHintPGI(arg0,arg1);
    checkGLGetError("glHintPGI");
  }
  public  void glHistogram(int arg0,int arg1,int arg2,boolean arg3)
  {
        checkContext();
downstreamGL.glHistogram(arg0,arg1,arg2,arg3);
    checkGLGetError("glHistogram");
  }
  public  void glIglooInterfaceSGIX(int arg0,java.nio.Buffer arg1)
  {
        checkContext();
downstreamGL.glIglooInterfaceSGIX(arg0,arg1);
    checkGLGetError("glIglooInterfaceSGIX");
  }
  public  void glImageTransformParameterfHP(int arg0,int arg1,float arg2)
  {
        checkContext();
downstreamGL.glImageTransformParameterfHP(arg0,arg1,arg2);
    checkGLGetError("glImageTransformParameterfHP");
  }
  public  void glImageTransformParameterfvHP(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glImageTransformParameterfvHP(arg0,arg1,arg2);
    checkGLGetError("glImageTransformParameterfvHP");
  }
  public  void glImageTransformParameterfvHP(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glImageTransformParameterfvHP(arg0,arg1,arg2,arg3);
    checkGLGetError("glImageTransformParameterfvHP");
  }
  public  void glImageTransformParameteriHP(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glImageTransformParameteriHP(arg0,arg1,arg2);
    checkGLGetError("glImageTransformParameteriHP");
  }
  public  void glImageTransformParameterivHP(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glImageTransformParameterivHP(arg0,arg1,arg2);
    checkGLGetError("glImageTransformParameterivHP");
  }
  public  void glImageTransformParameterivHP(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glImageTransformParameterivHP(arg0,arg1,arg2,arg3);
    checkGLGetError("glImageTransformParameterivHP");
  }
  public  void glIndexFuncEXT(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glIndexFuncEXT(arg0,arg1);
    checkGLGetError("glIndexFuncEXT");
  }
  public  void glIndexMask(int arg0)
  {
        checkContext();
downstreamGL.glIndexMask(arg0);
    checkGLGetError("glIndexMask");
  }
  public  void glIndexMaterialEXT(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glIndexMaterialEXT(arg0,arg1);
    checkGLGetError("glIndexMaterialEXT");
  }
  public  void glIndexPointer(int arg0,int arg1,java.nio.Buffer arg2)
  {
        checkContext();
downstreamGL.glIndexPointer(arg0,arg1,arg2);
    checkGLGetError("glIndexPointer");
  }
  public  void glIndexd(double arg0)
  {
        checkContext();
downstreamGL.glIndexd(arg0);
    checkGLGetError("glIndexd");
  }
  public  void glIndexdv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glIndexdv(arg0);
    checkGLGetError("glIndexdv");
  }
  public  void glIndexdv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glIndexdv(arg0,arg1);
    checkGLGetError("glIndexdv");
  }
  public  void glIndexf(float arg0)
  {
        checkContext();
downstreamGL.glIndexf(arg0);
    checkGLGetError("glIndexf");
  }
  public  void glIndexfv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glIndexfv(arg0);
    checkGLGetError("glIndexfv");
  }
  public  void glIndexfv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glIndexfv(arg0,arg1);
    checkGLGetError("glIndexfv");
  }
  public  void glIndexi(int arg0)
  {
        checkContext();
downstreamGL.glIndexi(arg0);
    checkGLGetError("glIndexi");
  }
  public  void glIndexiv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glIndexiv(arg0);
    checkGLGetError("glIndexiv");
  }
  public  void glIndexiv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glIndexiv(arg0,arg1);
    checkGLGetError("glIndexiv");
  }
  public  void glIndexs(short arg0)
  {
        checkContext();
downstreamGL.glIndexs(arg0);
    checkGLGetError("glIndexs");
  }
  public  void glIndexsv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glIndexsv(arg0);
    checkGLGetError("glIndexsv");
  }
  public  void glIndexsv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glIndexsv(arg0,arg1);
    checkGLGetError("glIndexsv");
  }
  public  void glIndexub(byte arg0)
  {
        checkContext();
downstreamGL.glIndexub(arg0);
    checkGLGetError("glIndexub");
  }
  public  void glIndexubv(java.nio.ByteBuffer arg0)
  {
        checkContext();
downstreamGL.glIndexubv(arg0);
    checkGLGetError("glIndexubv");
  }
  public  void glIndexubv(byte[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glIndexubv(arg0,arg1);
    checkGLGetError("glIndexubv");
  }
  public  void glInitNames()
  {
        checkContext();
downstreamGL.glInitNames();
    checkGLGetError("glInitNames");
  }
  public  void glInsertComponentEXT(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glInsertComponentEXT(arg0,arg1,arg2);
    checkGLGetError("glInsertComponentEXT");
  }
  public  void glInstrumentsBufferSGIX(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glInstrumentsBufferSGIX(arg0,arg1);
    checkGLGetError("glInstrumentsBufferSGIX");
  }
  public  void glInstrumentsBufferSGIX(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glInstrumentsBufferSGIX(arg0,arg1,arg2);
    checkGLGetError("glInstrumentsBufferSGIX");
  }
  public  void glInterleavedArrays(int arg0,int arg1,java.nio.Buffer arg2)
  {
        checkContext();
downstreamGL.glInterleavedArrays(arg0,arg1,arg2);
    checkGLGetError("glInterleavedArrays");
  }
  public  void glInterleavedArrays(int arg0,int arg1,long arg2)
  {
        checkContext();
downstreamGL.glInterleavedArrays(arg0,arg1,arg2);
    checkGLGetError("glInterleavedArrays");
  }
  public  boolean glIsAsyncMarkerSGIX(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsAsyncMarkerSGIX(arg0);
    checkGLGetError("glIsAsyncMarkerSGIX");
    return _res;
  }
  public  boolean glIsBuffer(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsBuffer(arg0);
    checkGLGetError("glIsBuffer");
    return _res;
  }
  public  boolean glIsBufferARB(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsBufferARB(arg0);
    checkGLGetError("glIsBufferARB");
    return _res;
  }
  public  boolean glIsEnabled(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsEnabled(arg0);
    checkGLGetError("glIsEnabled");
    return _res;
  }
  public  boolean glIsEnabledIndexedEXT(int arg0,int arg1)
  {
        checkContext();
boolean _res = downstreamGL.glIsEnabledIndexedEXT(arg0,arg1);
    checkGLGetError("glIsEnabledIndexedEXT");
    return _res;
  }
  public  boolean glIsFenceAPPLE(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsFenceAPPLE(arg0);
    checkGLGetError("glIsFenceAPPLE");
    return _res;
  }
  public  boolean glIsFenceNV(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsFenceNV(arg0);
    checkGLGetError("glIsFenceNV");
    return _res;
  }
  public  boolean glIsFramebufferEXT(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsFramebufferEXT(arg0);
    checkGLGetError("glIsFramebufferEXT");
    return _res;
  }
  public  boolean glIsList(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsList(arg0);
    checkGLGetError("glIsList");
    return _res;
  }
  public  boolean glIsObjectBufferATI(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsObjectBufferATI(arg0);
    checkGLGetError("glIsObjectBufferATI");
    return _res;
  }
  public  boolean glIsOcclusionQueryNV(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsOcclusionQueryNV(arg0);
    checkGLGetError("glIsOcclusionQueryNV");
    return _res;
  }
  public  boolean glIsProgram(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsProgram(arg0);
    checkGLGetError("glIsProgram");
    return _res;
  }
  public  boolean glIsProgramARB(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsProgramARB(arg0);
    checkGLGetError("glIsProgramARB");
    return _res;
  }
  public  boolean glIsProgramNV(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsProgramNV(arg0);
    checkGLGetError("glIsProgramNV");
    return _res;
  }
  public  boolean glIsQuery(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsQuery(arg0);
    checkGLGetError("glIsQuery");
    return _res;
  }
  public  boolean glIsQueryARB(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsQueryARB(arg0);
    checkGLGetError("glIsQueryARB");
    return _res;
  }
  public  boolean glIsRenderbufferEXT(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsRenderbufferEXT(arg0);
    checkGLGetError("glIsRenderbufferEXT");
    return _res;
  }
  public  boolean glIsShader(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsShader(arg0);
    checkGLGetError("glIsShader");
    return _res;
  }
  public  boolean glIsTexture(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsTexture(arg0);
    checkGLGetError("glIsTexture");
    return _res;
  }
  public  boolean glIsVariantEnabledEXT(int arg0,int arg1)
  {
        checkContext();
boolean _res = downstreamGL.glIsVariantEnabledEXT(arg0,arg1);
    checkGLGetError("glIsVariantEnabledEXT");
    return _res;
  }
  public  boolean glIsVertexArrayAPPLE(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glIsVertexArrayAPPLE(arg0);
    checkGLGetError("glIsVertexArrayAPPLE");
    return _res;
  }
  public  boolean glIsVertexAttribEnabledAPPLE(int arg0,int arg1)
  {
        checkContext();
boolean _res = downstreamGL.glIsVertexAttribEnabledAPPLE(arg0,arg1);
    checkGLGetError("glIsVertexAttribEnabledAPPLE");
    return _res;
  }
  public  void glLightEnviSGIX(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glLightEnviSGIX(arg0,arg1);
    checkGLGetError("glLightEnviSGIX");
  }
  public  void glLightModelf(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glLightModelf(arg0,arg1);
    checkGLGetError("glLightModelf");
  }
  public  void glLightModelfv(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glLightModelfv(arg0,arg1);
    checkGLGetError("glLightModelfv");
  }
  public  void glLightModelfv(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glLightModelfv(arg0,arg1,arg2);
    checkGLGetError("glLightModelfv");
  }
  public  void glLightModeli(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glLightModeli(arg0,arg1);
    checkGLGetError("glLightModeli");
  }
  public  void glLightModeliv(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glLightModeliv(arg0,arg1);
    checkGLGetError("glLightModeliv");
  }
  public  void glLightModeliv(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glLightModeliv(arg0,arg1,arg2);
    checkGLGetError("glLightModeliv");
  }
  public  void glLightf(int arg0,int arg1,float arg2)
  {
        checkContext();
downstreamGL.glLightf(arg0,arg1,arg2);
    checkGLGetError("glLightf");
  }
  public  void glLightfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glLightfv(arg0,arg1,arg2);
    checkGLGetError("glLightfv");
  }
  public  void glLightfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glLightfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glLightfv");
  }
  public  void glLighti(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glLighti(arg0,arg1,arg2);
    checkGLGetError("glLighti");
  }
  public  void glLightiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glLightiv(arg0,arg1,arg2);
    checkGLGetError("glLightiv");
  }
  public  void glLightiv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glLightiv(arg0,arg1,arg2,arg3);
    checkGLGetError("glLightiv");
  }
  public  void glLineStipple(int arg0,short arg1)
  {
        checkContext();
downstreamGL.glLineStipple(arg0,arg1);
    checkGLGetError("glLineStipple");
  }
  public  void glLineWidth(float arg0)
  {
        checkContext();
downstreamGL.glLineWidth(arg0);
    checkGLGetError("glLineWidth");
  }
  public  void glLinkProgram(int arg0)
  {
        checkContext();
downstreamGL.glLinkProgram(arg0);
    checkGLGetError("glLinkProgram");
  }
  public  void glLinkProgramARB(int arg0)
  {
        checkContext();
downstreamGL.glLinkProgramARB(arg0);
    checkGLGetError("glLinkProgramARB");
  }
  public  void glListBase(int arg0)
  {
        checkContext();
downstreamGL.glListBase(arg0);
    checkGLGetError("glListBase");
  }
  public  void glListParameterfSGIX(int arg0,int arg1,float arg2)
  {
        checkContext();
downstreamGL.glListParameterfSGIX(arg0,arg1,arg2);
    checkGLGetError("glListParameterfSGIX");
  }
  public  void glListParameterfvSGIX(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glListParameterfvSGIX(arg0,arg1,arg2);
    checkGLGetError("glListParameterfvSGIX");
  }
  public  void glListParameterfvSGIX(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glListParameterfvSGIX(arg0,arg1,arg2,arg3);
    checkGLGetError("glListParameterfvSGIX");
  }
  public  void glListParameteriSGIX(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glListParameteriSGIX(arg0,arg1,arg2);
    checkGLGetError("glListParameteriSGIX");
  }
  public  void glListParameterivSGIX(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glListParameterivSGIX(arg0,arg1,arg2);
    checkGLGetError("glListParameterivSGIX");
  }
  public  void glListParameterivSGIX(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glListParameterivSGIX(arg0,arg1,arg2,arg3);
    checkGLGetError("glListParameterivSGIX");
  }
  public  void glLoadIdentity()
  {
        checkContext();
downstreamGL.glLoadIdentity();
    checkGLGetError("glLoadIdentity");
  }
  public  void glLoadIdentityDeformationMapSGIX(int arg0)
  {
        checkContext();
downstreamGL.glLoadIdentityDeformationMapSGIX(arg0);
    checkGLGetError("glLoadIdentityDeformationMapSGIX");
  }
  public  void glLoadMatrixd(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glLoadMatrixd(arg0);
    checkGLGetError("glLoadMatrixd");
  }
  public  void glLoadMatrixd(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glLoadMatrixd(arg0,arg1);
    checkGLGetError("glLoadMatrixd");
  }
  public  void glLoadMatrixf(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glLoadMatrixf(arg0);
    checkGLGetError("glLoadMatrixf");
  }
  public  void glLoadMatrixf(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glLoadMatrixf(arg0,arg1);
    checkGLGetError("glLoadMatrixf");
  }
  public  void glLoadName(int arg0)
  {
        checkContext();
downstreamGL.glLoadName(arg0);
    checkGLGetError("glLoadName");
  }
  public  void glLoadProgramNV(int arg0,int arg1,int arg2,java.lang.String arg3)
  {
        checkContext();
downstreamGL.glLoadProgramNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glLoadProgramNV");
  }
  public  void glLoadTransposeMatrixd(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glLoadTransposeMatrixd(arg0);
    checkGLGetError("glLoadTransposeMatrixd");
  }
  public  void glLoadTransposeMatrixd(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glLoadTransposeMatrixd(arg0,arg1);
    checkGLGetError("glLoadTransposeMatrixd");
  }
  public  void glLoadTransposeMatrixf(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glLoadTransposeMatrixf(arg0);
    checkGLGetError("glLoadTransposeMatrixf");
  }
  public  void glLoadTransposeMatrixf(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glLoadTransposeMatrixf(arg0,arg1);
    checkGLGetError("glLoadTransposeMatrixf");
  }
  public  void glLockArraysEXT(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glLockArraysEXT(arg0,arg1);
    checkGLGetError("glLockArraysEXT");
  }
  public  void glLogicOp(int arg0)
  {
        checkContext();
downstreamGL.glLogicOp(arg0);
    checkGLGetError("glLogicOp");
  }
  public  void glMap1d(int arg0,double arg1,double arg2,int arg3,int arg4,java.nio.DoubleBuffer arg5)
  {
        checkContext();
downstreamGL.glMap1d(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glMap1d");
  }
  public  void glMap1d(int arg0,double arg1,double arg2,int arg3,int arg4,double[] arg5,int arg6)
  {
        checkContext();
downstreamGL.glMap1d(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glMap1d");
  }
  public  void glMap1f(int arg0,float arg1,float arg2,int arg3,int arg4,java.nio.FloatBuffer arg5)
  {
        checkContext();
downstreamGL.glMap1f(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glMap1f");
  }
  public  void glMap1f(int arg0,float arg1,float arg2,int arg3,int arg4,float[] arg5,int arg6)
  {
        checkContext();
downstreamGL.glMap1f(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glMap1f");
  }
  public  void glMap2d(int arg0,double arg1,double arg2,int arg3,int arg4,double arg5,double arg6,int arg7,int arg8,java.nio.DoubleBuffer arg9)
  {
        checkContext();
downstreamGL.glMap2d(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    checkGLGetError("glMap2d");
  }
  public  void glMap2d(int arg0,double arg1,double arg2,int arg3,int arg4,double arg5,double arg6,int arg7,int arg8,double[] arg9,int arg10)
  {
        checkContext();
downstreamGL.glMap2d(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    checkGLGetError("glMap2d");
  }
  public  void glMap2f(int arg0,float arg1,float arg2,int arg3,int arg4,float arg5,float arg6,int arg7,int arg8,java.nio.FloatBuffer arg9)
  {
        checkContext();
downstreamGL.glMap2f(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    checkGLGetError("glMap2f");
  }
  public  void glMap2f(int arg0,float arg1,float arg2,int arg3,int arg4,float arg5,float arg6,int arg7,int arg8,float[] arg9,int arg10)
  {
        checkContext();
downstreamGL.glMap2f(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    checkGLGetError("glMap2f");
  }
  public  java.nio.ByteBuffer glMapBuffer(int arg0,int arg1)
  {
        checkContext();
java.nio.ByteBuffer _res = downstreamGL.glMapBuffer(arg0,arg1);
    checkGLGetError("glMapBuffer");
    return _res;
  }
  public  java.nio.ByteBuffer glMapBufferARB(int arg0,int arg1)
  {
        checkContext();
java.nio.ByteBuffer _res = downstreamGL.glMapBufferARB(arg0,arg1);
    checkGLGetError("glMapBufferARB");
    return _res;
  }
  public  void glMapControlPointsNV(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,boolean arg7,java.nio.Buffer arg8)
  {
        checkContext();
downstreamGL.glMapControlPointsNV(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    checkGLGetError("glMapControlPointsNV");
  }
  public  void glMapGrid1d(int arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glMapGrid1d(arg0,arg1,arg2);
    checkGLGetError("glMapGrid1d");
  }
  public  void glMapGrid1f(int arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glMapGrid1f(arg0,arg1,arg2);
    checkGLGetError("glMapGrid1f");
  }
  public  void glMapGrid2d(int arg0,double arg1,double arg2,int arg3,double arg4,double arg5)
  {
        checkContext();
downstreamGL.glMapGrid2d(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glMapGrid2d");
  }
  public  void glMapGrid2f(int arg0,float arg1,float arg2,int arg3,float arg4,float arg5)
  {
        checkContext();
downstreamGL.glMapGrid2f(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glMapGrid2f");
  }
  public  void glMapParameterfvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glMapParameterfvNV(arg0,arg1,arg2);
    checkGLGetError("glMapParameterfvNV");
  }
  public  void glMapParameterfvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glMapParameterfvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glMapParameterfvNV");
  }
  public  void glMapParameterivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glMapParameterivNV(arg0,arg1,arg2);
    checkGLGetError("glMapParameterivNV");
  }
  public  void glMapParameterivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glMapParameterivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glMapParameterivNV");
  }
  public  void glMapVertexAttrib1dAPPLE(int arg0,int arg1,double arg2,double arg3,int arg4,int arg5,java.nio.DoubleBuffer arg6)
  {
        checkContext();
downstreamGL.glMapVertexAttrib1dAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glMapVertexAttrib1dAPPLE");
  }
  public  void glMapVertexAttrib1dAPPLE(int arg0,int arg1,double arg2,double arg3,int arg4,int arg5,double[] arg6,int arg7)
  {
        checkContext();
downstreamGL.glMapVertexAttrib1dAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glMapVertexAttrib1dAPPLE");
  }
  public  void glMapVertexAttrib1fAPPLE(int arg0,int arg1,float arg2,float arg3,int arg4,int arg5,java.nio.FloatBuffer arg6)
  {
        checkContext();
downstreamGL.glMapVertexAttrib1fAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glMapVertexAttrib1fAPPLE");
  }
  public  void glMapVertexAttrib1fAPPLE(int arg0,int arg1,float arg2,float arg3,int arg4,int arg5,float[] arg6,int arg7)
  {
        checkContext();
downstreamGL.glMapVertexAttrib1fAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glMapVertexAttrib1fAPPLE");
  }
  public  void glMapVertexAttrib2dAPPLE(int arg0,int arg1,double arg2,double arg3,int arg4,int arg5,double arg6,double arg7,int arg8,int arg9,java.nio.DoubleBuffer arg10)
  {
        checkContext();
downstreamGL.glMapVertexAttrib2dAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    checkGLGetError("glMapVertexAttrib2dAPPLE");
  }
  public  void glMapVertexAttrib2dAPPLE(int arg0,int arg1,double arg2,double arg3,int arg4,int arg5,double arg6,double arg7,int arg8,int arg9,double[] arg10,int arg11)
  {
        checkContext();
downstreamGL.glMapVertexAttrib2dAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11);
    checkGLGetError("glMapVertexAttrib2dAPPLE");
  }
  public  void glMapVertexAttrib2fAPPLE(int arg0,int arg1,float arg2,float arg3,int arg4,int arg5,float arg6,float arg7,int arg8,int arg9,java.nio.FloatBuffer arg10)
  {
        checkContext();
downstreamGL.glMapVertexAttrib2fAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    checkGLGetError("glMapVertexAttrib2fAPPLE");
  }
  public  void glMapVertexAttrib2fAPPLE(int arg0,int arg1,float arg2,float arg3,int arg4,int arg5,float arg6,float arg7,int arg8,int arg9,float[] arg10,int arg11)
  {
        checkContext();
downstreamGL.glMapVertexAttrib2fAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11);
    checkGLGetError("glMapVertexAttrib2fAPPLE");
  }
  public  void glMaterialf(int arg0,int arg1,float arg2)
  {
        checkContext();
downstreamGL.glMaterialf(arg0,arg1,arg2);
    checkGLGetError("glMaterialf");
  }
  public  void glMaterialfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glMaterialfv(arg0,arg1,arg2);
    checkGLGetError("glMaterialfv");
  }
  public  void glMaterialfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glMaterialfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glMaterialfv");
  }
  public  void glMateriali(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glMateriali(arg0,arg1,arg2);
    checkGLGetError("glMateriali");
  }
  public  void glMaterialiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glMaterialiv(arg0,arg1,arg2);
    checkGLGetError("glMaterialiv");
  }
  public  void glMaterialiv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glMaterialiv(arg0,arg1,arg2,arg3);
    checkGLGetError("glMaterialiv");
  }
  public  void glMatrixIndexPointerARB(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
        checkContext();
downstreamGL.glMatrixIndexPointerARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glMatrixIndexPointerARB");
  }
  public  void glMatrixIndexPointerARB(int arg0,int arg1,int arg2,long arg3)
  {
        checkContext();
downstreamGL.glMatrixIndexPointerARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glMatrixIndexPointerARB");
  }
  public  void glMatrixIndexubvARB(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glMatrixIndexubvARB(arg0,arg1);
    checkGLGetError("glMatrixIndexubvARB");
  }
  public  void glMatrixIndexubvARB(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMatrixIndexubvARB(arg0,arg1,arg2);
    checkGLGetError("glMatrixIndexubvARB");
  }
  public  void glMatrixIndexuivARB(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glMatrixIndexuivARB(arg0,arg1);
    checkGLGetError("glMatrixIndexuivARB");
  }
  public  void glMatrixIndexuivARB(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMatrixIndexuivARB(arg0,arg1,arg2);
    checkGLGetError("glMatrixIndexuivARB");
  }
  public  void glMatrixIndexusvARB(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glMatrixIndexusvARB(arg0,arg1);
    checkGLGetError("glMatrixIndexusvARB");
  }
  public  void glMatrixIndexusvARB(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMatrixIndexusvARB(arg0,arg1,arg2);
    checkGLGetError("glMatrixIndexusvARB");
  }
  public  void glMatrixMode(int arg0)
  {
        checkContext();
downstreamGL.glMatrixMode(arg0);
    checkGLGetError("glMatrixMode");
  }
  public  void glMinmax(int arg0,int arg1,boolean arg2)
  {
        checkContext();
downstreamGL.glMinmax(arg0,arg1,arg2);
    checkGLGetError("glMinmax");
  }
  public  void glMultMatrixd(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glMultMatrixd(arg0);
    checkGLGetError("glMultMatrixd");
  }
  public  void glMultMatrixd(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glMultMatrixd(arg0,arg1);
    checkGLGetError("glMultMatrixd");
  }
  public  void glMultMatrixf(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glMultMatrixf(arg0);
    checkGLGetError("glMultMatrixf");
  }
  public  void glMultMatrixf(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glMultMatrixf(arg0,arg1);
    checkGLGetError("glMultMatrixf");
  }
  public  void glMultTransposeMatrixd(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glMultTransposeMatrixd(arg0);
    checkGLGetError("glMultTransposeMatrixd");
  }
  public  void glMultTransposeMatrixd(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glMultTransposeMatrixd(arg0,arg1);
    checkGLGetError("glMultTransposeMatrixd");
  }
  public  void glMultTransposeMatrixf(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glMultTransposeMatrixf(arg0);
    checkGLGetError("glMultTransposeMatrixf");
  }
  public  void glMultTransposeMatrixf(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glMultTransposeMatrixf(arg0,arg1);
    checkGLGetError("glMultTransposeMatrixf");
  }
  public  void glMultiDrawArrays(int arg0,java.nio.IntBuffer arg1,java.nio.IntBuffer arg2,int arg3)
  {
        checkContext();
downstreamGL.glMultiDrawArrays(arg0,arg1,arg2,arg3);
    checkGLGetError("glMultiDrawArrays");
  }
  public  void glMultiDrawArrays(int arg0,int[] arg1,int arg2,int[] arg3,int arg4,int arg5)
  {
        checkContext();
downstreamGL.glMultiDrawArrays(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glMultiDrawArrays");
  }
  public  void glMultiDrawArraysEXT(int arg0,java.nio.IntBuffer arg1,java.nio.IntBuffer arg2,int arg3)
  {
        checkContext();
downstreamGL.glMultiDrawArraysEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glMultiDrawArraysEXT");
  }
  public  void glMultiDrawArraysEXT(int arg0,int[] arg1,int arg2,int[] arg3,int arg4,int arg5)
  {
        checkContext();
downstreamGL.glMultiDrawArraysEXT(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glMultiDrawArraysEXT");
  }
  public  void glMultiDrawElementArrayAPPLE(int arg0,java.nio.IntBuffer arg1,java.nio.IntBuffer arg2,int arg3)
  {
        checkContext();
downstreamGL.glMultiDrawElementArrayAPPLE(arg0,arg1,arg2,arg3);
    checkGLGetError("glMultiDrawElementArrayAPPLE");
  }
  public  void glMultiDrawElementArrayAPPLE(int arg0,int[] arg1,int arg2,int[] arg3,int arg4,int arg5)
  {
        checkContext();
downstreamGL.glMultiDrawElementArrayAPPLE(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glMultiDrawElementArrayAPPLE");
  }
  public  void glMultiDrawElements(int arg0,java.nio.IntBuffer arg1,int arg2,java.nio.Buffer[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glMultiDrawElements(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glMultiDrawElements");
  }
  public  void glMultiDrawElements(int arg0,int[] arg1,int arg2,int arg3,java.nio.Buffer[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glMultiDrawElements(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glMultiDrawElements");
  }
  public  void glMultiDrawElementsEXT(int arg0,java.nio.IntBuffer arg1,int arg2,java.nio.Buffer[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glMultiDrawElementsEXT(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glMultiDrawElementsEXT");
  }
  public  void glMultiDrawElementsEXT(int arg0,int[] arg1,int arg2,int arg3,java.nio.Buffer[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glMultiDrawElementsEXT(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glMultiDrawElementsEXT");
  }
  public  void glMultiDrawRangeElementArrayAPPLE(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4,int arg5)
  {
        checkContext();
downstreamGL.glMultiDrawRangeElementArrayAPPLE(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glMultiDrawRangeElementArrayAPPLE");
  }
  public  void glMultiDrawRangeElementArrayAPPLE(int arg0,int arg1,int arg2,int[] arg3,int arg4,int[] arg5,int arg6,int arg7)
  {
        checkContext();
downstreamGL.glMultiDrawRangeElementArrayAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glMultiDrawRangeElementArrayAPPLE");
  }
  public  void glMultiModeDrawArraysIBM(java.nio.IntBuffer arg0,java.nio.IntBuffer arg1,java.nio.IntBuffer arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glMultiModeDrawArraysIBM(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glMultiModeDrawArraysIBM");
  }
  public  void glMultiModeDrawArraysIBM(int[] arg0,int arg1,int[] arg2,int arg3,int[] arg4,int arg5,int arg6,int arg7)
  {
        checkContext();
downstreamGL.glMultiModeDrawArraysIBM(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glMultiModeDrawArraysIBM");
  }
  public  void glMultiModeDrawElementsIBM(java.nio.IntBuffer arg0,java.nio.IntBuffer arg1,int arg2,java.nio.Buffer[] arg3,int arg4,int arg5)
  {
        checkContext();
downstreamGL.glMultiModeDrawElementsIBM(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glMultiModeDrawElementsIBM");
  }
  public  void glMultiModeDrawElementsIBM(int[] arg0,int arg1,int[] arg2,int arg3,int arg4,java.nio.Buffer[] arg5,int arg6,int arg7)
  {
        checkContext();
downstreamGL.glMultiModeDrawElementsIBM(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glMultiModeDrawElementsIBM");
  }
  public  void glMultiTexCoord1d(int arg0,double arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord1d(arg0,arg1);
    checkGLGetError("glMultiTexCoord1d");
  }
  public  void glMultiTexCoord1dv(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord1dv(arg0,arg1);
    checkGLGetError("glMultiTexCoord1dv");
  }
  public  void glMultiTexCoord1dv(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord1dv(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord1dv");
  }
  public  void glMultiTexCoord1f(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord1f(arg0,arg1);
    checkGLGetError("glMultiTexCoord1f");
  }
  public  void glMultiTexCoord1fv(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord1fv(arg0,arg1);
    checkGLGetError("glMultiTexCoord1fv");
  }
  public  void glMultiTexCoord1fv(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord1fv(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord1fv");
  }
  public  void glMultiTexCoord1hNV(int arg0,short arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord1hNV(arg0,arg1);
    checkGLGetError("glMultiTexCoord1hNV");
  }
  public  void glMultiTexCoord1hvNV(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord1hvNV(arg0,arg1);
    checkGLGetError("glMultiTexCoord1hvNV");
  }
  public  void glMultiTexCoord1hvNV(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord1hvNV(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord1hvNV");
  }
  public  void glMultiTexCoord1i(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord1i(arg0,arg1);
    checkGLGetError("glMultiTexCoord1i");
  }
  public  void glMultiTexCoord1iv(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord1iv(arg0,arg1);
    checkGLGetError("glMultiTexCoord1iv");
  }
  public  void glMultiTexCoord1iv(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord1iv(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord1iv");
  }
  public  void glMultiTexCoord1s(int arg0,short arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord1s(arg0,arg1);
    checkGLGetError("glMultiTexCoord1s");
  }
  public  void glMultiTexCoord1sv(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord1sv(arg0,arg1);
    checkGLGetError("glMultiTexCoord1sv");
  }
  public  void glMultiTexCoord1sv(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord1sv(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord1sv");
  }
  public  void glMultiTexCoord2d(int arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord2d(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord2d");
  }
  public  void glMultiTexCoord2dv(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord2dv(arg0,arg1);
    checkGLGetError("glMultiTexCoord2dv");
  }
  public  void glMultiTexCoord2dv(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord2dv(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord2dv");
  }
  public  void glMultiTexCoord2f(int arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord2f(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord2f");
  }
  public  void glMultiTexCoord2fv(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord2fv(arg0,arg1);
    checkGLGetError("glMultiTexCoord2fv");
  }
  public  void glMultiTexCoord2fv(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord2fv(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord2fv");
  }
  public  void glMultiTexCoord2hNV(int arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord2hNV(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord2hNV");
  }
  public  void glMultiTexCoord2hvNV(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord2hvNV(arg0,arg1);
    checkGLGetError("glMultiTexCoord2hvNV");
  }
  public  void glMultiTexCoord2hvNV(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord2hvNV(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord2hvNV");
  }
  public  void glMultiTexCoord2i(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord2i(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord2i");
  }
  public  void glMultiTexCoord2iv(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord2iv(arg0,arg1);
    checkGLGetError("glMultiTexCoord2iv");
  }
  public  void glMultiTexCoord2iv(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord2iv(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord2iv");
  }
  public  void glMultiTexCoord2s(int arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord2s(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord2s");
  }
  public  void glMultiTexCoord2sv(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord2sv(arg0,arg1);
    checkGLGetError("glMultiTexCoord2sv");
  }
  public  void glMultiTexCoord2sv(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord2sv(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord2sv");
  }
  public  void glMultiTexCoord3d(int arg0,double arg1,double arg2,double arg3)
  {
        checkContext();
downstreamGL.glMultiTexCoord3d(arg0,arg1,arg2,arg3);
    checkGLGetError("glMultiTexCoord3d");
  }
  public  void glMultiTexCoord3dv(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord3dv(arg0,arg1);
    checkGLGetError("glMultiTexCoord3dv");
  }
  public  void glMultiTexCoord3dv(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord3dv(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord3dv");
  }
  public  void glMultiTexCoord3f(int arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glMultiTexCoord3f(arg0,arg1,arg2,arg3);
    checkGLGetError("glMultiTexCoord3f");
  }
  public  void glMultiTexCoord3fv(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord3fv(arg0,arg1);
    checkGLGetError("glMultiTexCoord3fv");
  }
  public  void glMultiTexCoord3fv(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord3fv(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord3fv");
  }
  public  void glMultiTexCoord3hNV(int arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glMultiTexCoord3hNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glMultiTexCoord3hNV");
  }
  public  void glMultiTexCoord3hvNV(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord3hvNV(arg0,arg1);
    checkGLGetError("glMultiTexCoord3hvNV");
  }
  public  void glMultiTexCoord3hvNV(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord3hvNV(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord3hvNV");
  }
  public  void glMultiTexCoord3i(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glMultiTexCoord3i(arg0,arg1,arg2,arg3);
    checkGLGetError("glMultiTexCoord3i");
  }
  public  void glMultiTexCoord3iv(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord3iv(arg0,arg1);
    checkGLGetError("glMultiTexCoord3iv");
  }
  public  void glMultiTexCoord3iv(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord3iv(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord3iv");
  }
  public  void glMultiTexCoord3s(int arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glMultiTexCoord3s(arg0,arg1,arg2,arg3);
    checkGLGetError("glMultiTexCoord3s");
  }
  public  void glMultiTexCoord3sv(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord3sv(arg0,arg1);
    checkGLGetError("glMultiTexCoord3sv");
  }
  public  void glMultiTexCoord3sv(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord3sv(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord3sv");
  }
  public  void glMultiTexCoord4d(int arg0,double arg1,double arg2,double arg3,double arg4)
  {
        checkContext();
downstreamGL.glMultiTexCoord4d(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glMultiTexCoord4d");
  }
  public  void glMultiTexCoord4dv(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord4dv(arg0,arg1);
    checkGLGetError("glMultiTexCoord4dv");
  }
  public  void glMultiTexCoord4dv(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord4dv(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord4dv");
  }
  public  void glMultiTexCoord4f(int arg0,float arg1,float arg2,float arg3,float arg4)
  {
        checkContext();
downstreamGL.glMultiTexCoord4f(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glMultiTexCoord4f");
  }
  public  void glMultiTexCoord4fv(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord4fv(arg0,arg1);
    checkGLGetError("glMultiTexCoord4fv");
  }
  public  void glMultiTexCoord4fv(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord4fv(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord4fv");
  }
  public  void glMultiTexCoord4hNV(int arg0,short arg1,short arg2,short arg3,short arg4)
  {
        checkContext();
downstreamGL.glMultiTexCoord4hNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glMultiTexCoord4hNV");
  }
  public  void glMultiTexCoord4hvNV(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord4hvNV(arg0,arg1);
    checkGLGetError("glMultiTexCoord4hvNV");
  }
  public  void glMultiTexCoord4hvNV(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord4hvNV(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord4hvNV");
  }
  public  void glMultiTexCoord4i(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glMultiTexCoord4i(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glMultiTexCoord4i");
  }
  public  void glMultiTexCoord4iv(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord4iv(arg0,arg1);
    checkGLGetError("glMultiTexCoord4iv");
  }
  public  void glMultiTexCoord4iv(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord4iv(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord4iv");
  }
  public  void glMultiTexCoord4s(int arg0,short arg1,short arg2,short arg3,short arg4)
  {
        checkContext();
downstreamGL.glMultiTexCoord4s(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glMultiTexCoord4s");
  }
  public  void glMultiTexCoord4sv(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glMultiTexCoord4sv(arg0,arg1);
    checkGLGetError("glMultiTexCoord4sv");
  }
  public  void glMultiTexCoord4sv(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glMultiTexCoord4sv(arg0,arg1,arg2);
    checkGLGetError("glMultiTexCoord4sv");
  }
  public  int glNewBufferRegion(int arg0)
  {
        checkContext();
int _res = downstreamGL.glNewBufferRegion(arg0);
    checkGLGetError("glNewBufferRegion");
    return _res;
  }
  public  void glNewList(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glNewList(arg0,arg1);
    checkGLGetError("glNewList");
  }
  public  int glNewObjectBufferATI(int arg0,java.nio.Buffer arg1,int arg2)
  {
        checkContext();
int _res = downstreamGL.glNewObjectBufferATI(arg0,arg1,arg2);
    checkGLGetError("glNewObjectBufferATI");
    return _res;
  }
  public  void glNormal3b(byte arg0,byte arg1,byte arg2)
  {
        checkContext();
downstreamGL.glNormal3b(arg0,arg1,arg2);
    checkGLGetError("glNormal3b");
  }
  public  void glNormal3bv(java.nio.ByteBuffer arg0)
  {
        checkContext();
downstreamGL.glNormal3bv(arg0);
    checkGLGetError("glNormal3bv");
  }
  public  void glNormal3bv(byte[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glNormal3bv(arg0,arg1);
    checkGLGetError("glNormal3bv");
  }
  public  void glNormal3d(double arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glNormal3d(arg0,arg1,arg2);
    checkGLGetError("glNormal3d");
  }
  public  void glNormal3dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glNormal3dv(arg0);
    checkGLGetError("glNormal3dv");
  }
  public  void glNormal3dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glNormal3dv(arg0,arg1);
    checkGLGetError("glNormal3dv");
  }
  public  void glNormal3f(float arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glNormal3f(arg0,arg1,arg2);
    checkGLGetError("glNormal3f");
  }
  public  void glNormal3fVertex3fSUN(float arg0,float arg1,float arg2,float arg3,float arg4,float arg5)
  {
        checkContext();
downstreamGL.glNormal3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glNormal3fVertex3fSUN");
  }
  public  void glNormal3fVertex3fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glNormal3fVertex3fvSUN(arg0,arg1);
    checkGLGetError("glNormal3fVertex3fvSUN");
  }
  public  void glNormal3fVertex3fvSUN(float[] arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3);
    checkGLGetError("glNormal3fVertex3fvSUN");
  }
  public  void glNormal3fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glNormal3fv(arg0);
    checkGLGetError("glNormal3fv");
  }
  public  void glNormal3fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glNormal3fv(arg0,arg1);
    checkGLGetError("glNormal3fv");
  }
  public  void glNormal3hNV(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glNormal3hNV(arg0,arg1,arg2);
    checkGLGetError("glNormal3hNV");
  }
  public  void glNormal3hvNV(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glNormal3hvNV(arg0);
    checkGLGetError("glNormal3hvNV");
  }
  public  void glNormal3hvNV(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glNormal3hvNV(arg0,arg1);
    checkGLGetError("glNormal3hvNV");
  }
  public  void glNormal3i(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glNormal3i(arg0,arg1,arg2);
    checkGLGetError("glNormal3i");
  }
  public  void glNormal3iv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glNormal3iv(arg0);
    checkGLGetError("glNormal3iv");
  }
  public  void glNormal3iv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glNormal3iv(arg0,arg1);
    checkGLGetError("glNormal3iv");
  }
  public  void glNormal3s(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glNormal3s(arg0,arg1,arg2);
    checkGLGetError("glNormal3s");
  }
  public  void glNormal3sv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glNormal3sv(arg0);
    checkGLGetError("glNormal3sv");
  }
  public  void glNormal3sv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glNormal3sv(arg0,arg1);
    checkGLGetError("glNormal3sv");
  }
  public  void glNormalPointer(int arg0,int arg1,java.nio.Buffer arg2)
  {
        checkContext();
downstreamGL.glNormalPointer(arg0,arg1,arg2);
    checkGLGetError("glNormalPointer");
  }
  public  void glNormalPointer(int arg0,int arg1,long arg2)
  {
        checkContext();
downstreamGL.glNormalPointer(arg0,arg1,arg2);
    checkGLGetError("glNormalPointer");
  }
  public  void glNormalStream3bATI(int arg0,byte arg1,byte arg2,byte arg3)
  {
        checkContext();
downstreamGL.glNormalStream3bATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glNormalStream3bATI");
  }
  public  void glNormalStream3bvATI(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glNormalStream3bvATI(arg0,arg1);
    checkGLGetError("glNormalStream3bvATI");
  }
  public  void glNormalStream3bvATI(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glNormalStream3bvATI(arg0,arg1,arg2);
    checkGLGetError("glNormalStream3bvATI");
  }
  public  void glNormalStream3dATI(int arg0,double arg1,double arg2,double arg3)
  {
        checkContext();
downstreamGL.glNormalStream3dATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glNormalStream3dATI");
  }
  public  void glNormalStream3dvATI(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glNormalStream3dvATI(arg0,arg1);
    checkGLGetError("glNormalStream3dvATI");
  }
  public  void glNormalStream3dvATI(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glNormalStream3dvATI(arg0,arg1,arg2);
    checkGLGetError("glNormalStream3dvATI");
  }
  public  void glNormalStream3fATI(int arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glNormalStream3fATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glNormalStream3fATI");
  }
  public  void glNormalStream3fvATI(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glNormalStream3fvATI(arg0,arg1);
    checkGLGetError("glNormalStream3fvATI");
  }
  public  void glNormalStream3fvATI(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glNormalStream3fvATI(arg0,arg1,arg2);
    checkGLGetError("glNormalStream3fvATI");
  }
  public  void glNormalStream3iATI(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glNormalStream3iATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glNormalStream3iATI");
  }
  public  void glNormalStream3ivATI(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glNormalStream3ivATI(arg0,arg1);
    checkGLGetError("glNormalStream3ivATI");
  }
  public  void glNormalStream3ivATI(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glNormalStream3ivATI(arg0,arg1,arg2);
    checkGLGetError("glNormalStream3ivATI");
  }
  public  void glNormalStream3sATI(int arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glNormalStream3sATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glNormalStream3sATI");
  }
  public  void glNormalStream3svATI(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glNormalStream3svATI(arg0,arg1);
    checkGLGetError("glNormalStream3svATI");
  }
  public  void glNormalStream3svATI(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glNormalStream3svATI(arg0,arg1,arg2);
    checkGLGetError("glNormalStream3svATI");
  }
  public  void glOrtho(double arg0,double arg1,double arg2,double arg3,double arg4,double arg5)
  {
        checkContext();
downstreamGL.glOrtho(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glOrtho");
  }
  public  void glPNTrianglesfATI(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glPNTrianglesfATI(arg0,arg1);
    checkGLGetError("glPNTrianglesfATI");
  }
  public  void glPNTrianglesiATI(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glPNTrianglesiATI(arg0,arg1);
    checkGLGetError("glPNTrianglesiATI");
  }
  public  void glPassTexCoordATI(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glPassTexCoordATI(arg0,arg1,arg2);
    checkGLGetError("glPassTexCoordATI");
  }
  public  void glPassThrough(float arg0)
  {
        checkContext();
downstreamGL.glPassThrough(arg0);
    checkGLGetError("glPassThrough");
  }
  public  void glPixelDataRangeNV(int arg0,int arg1,java.nio.Buffer arg2)
  {
        checkContext();
downstreamGL.glPixelDataRangeNV(arg0,arg1,arg2);
    checkGLGetError("glPixelDataRangeNV");
  }
  public  void glPixelMapfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glPixelMapfv(arg0,arg1,arg2);
    checkGLGetError("glPixelMapfv");
  }
  public  void glPixelMapfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glPixelMapfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glPixelMapfv");
  }
  public  void glPixelMapfv(int arg0,int arg1,long arg2)
  {
        checkContext();
downstreamGL.glPixelMapfv(arg0,arg1,arg2);
    checkGLGetError("glPixelMapfv");
  }
  public  void glPixelMapuiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glPixelMapuiv(arg0,arg1,arg2);
    checkGLGetError("glPixelMapuiv");
  }
  public  void glPixelMapuiv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glPixelMapuiv(arg0,arg1,arg2,arg3);
    checkGLGetError("glPixelMapuiv");
  }
  public  void glPixelMapuiv(int arg0,int arg1,long arg2)
  {
        checkContext();
downstreamGL.glPixelMapuiv(arg0,arg1,arg2);
    checkGLGetError("glPixelMapuiv");
  }
  public  void glPixelMapusv(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
        checkContext();
downstreamGL.glPixelMapusv(arg0,arg1,arg2);
    checkGLGetError("glPixelMapusv");
  }
  public  void glPixelMapusv(int arg0,int arg1,short[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glPixelMapusv(arg0,arg1,arg2,arg3);
    checkGLGetError("glPixelMapusv");
  }
  public  void glPixelMapusv(int arg0,int arg1,long arg2)
  {
        checkContext();
downstreamGL.glPixelMapusv(arg0,arg1,arg2);
    checkGLGetError("glPixelMapusv");
  }
  public  void glPixelStoref(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glPixelStoref(arg0,arg1);
    checkGLGetError("glPixelStoref");
  }
  public  void glPixelStorei(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glPixelStorei(arg0,arg1);
    checkGLGetError("glPixelStorei");
  }
  public  void glPixelTexGenParameterfSGIS(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glPixelTexGenParameterfSGIS(arg0,arg1);
    checkGLGetError("glPixelTexGenParameterfSGIS");
  }
  public  void glPixelTexGenParameterfvSGIS(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glPixelTexGenParameterfvSGIS(arg0,arg1);
    checkGLGetError("glPixelTexGenParameterfvSGIS");
  }
  public  void glPixelTexGenParameterfvSGIS(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glPixelTexGenParameterfvSGIS(arg0,arg1,arg2);
    checkGLGetError("glPixelTexGenParameterfvSGIS");
  }
  public  void glPixelTexGenParameteriSGIS(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glPixelTexGenParameteriSGIS(arg0,arg1);
    checkGLGetError("glPixelTexGenParameteriSGIS");
  }
  public  void glPixelTexGenParameterivSGIS(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glPixelTexGenParameterivSGIS(arg0,arg1);
    checkGLGetError("glPixelTexGenParameterivSGIS");
  }
  public  void glPixelTexGenParameterivSGIS(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glPixelTexGenParameterivSGIS(arg0,arg1,arg2);
    checkGLGetError("glPixelTexGenParameterivSGIS");
  }
  public  void glPixelTexGenSGIX(int arg0)
  {
        checkContext();
downstreamGL.glPixelTexGenSGIX(arg0);
    checkGLGetError("glPixelTexGenSGIX");
  }
  public  void glPixelTransferf(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glPixelTransferf(arg0,arg1);
    checkGLGetError("glPixelTransferf");
  }
  public  void glPixelTransferi(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glPixelTransferi(arg0,arg1);
    checkGLGetError("glPixelTransferi");
  }
  public  void glPixelTransformParameterfEXT(int arg0,int arg1,float arg2)
  {
        checkContext();
downstreamGL.glPixelTransformParameterfEXT(arg0,arg1,arg2);
    checkGLGetError("glPixelTransformParameterfEXT");
  }
  public  void glPixelTransformParameterfvEXT(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glPixelTransformParameterfvEXT(arg0,arg1,arg2);
    checkGLGetError("glPixelTransformParameterfvEXT");
  }
  public  void glPixelTransformParameterfvEXT(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glPixelTransformParameterfvEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glPixelTransformParameterfvEXT");
  }
  public  void glPixelTransformParameteriEXT(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glPixelTransformParameteriEXT(arg0,arg1,arg2);
    checkGLGetError("glPixelTransformParameteriEXT");
  }
  public  void glPixelTransformParameterivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glPixelTransformParameterivEXT(arg0,arg1,arg2);
    checkGLGetError("glPixelTransformParameterivEXT");
  }
  public  void glPixelTransformParameterivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glPixelTransformParameterivEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glPixelTransformParameterivEXT");
  }
  public  void glPixelZoom(float arg0,float arg1)
  {
        checkContext();
downstreamGL.glPixelZoom(arg0,arg1);
    checkGLGetError("glPixelZoom");
  }
  public  void glPointParameterf(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glPointParameterf(arg0,arg1);
    checkGLGetError("glPointParameterf");
  }
  public  void glPointParameterfARB(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glPointParameterfARB(arg0,arg1);
    checkGLGetError("glPointParameterfARB");
  }
  public  void glPointParameterfEXT(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glPointParameterfEXT(arg0,arg1);
    checkGLGetError("glPointParameterfEXT");
  }
  public  void glPointParameterfSGIS(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glPointParameterfSGIS(arg0,arg1);
    checkGLGetError("glPointParameterfSGIS");
  }
  public  void glPointParameterfv(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glPointParameterfv(arg0,arg1);
    checkGLGetError("glPointParameterfv");
  }
  public  void glPointParameterfv(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glPointParameterfv(arg0,arg1,arg2);
    checkGLGetError("glPointParameterfv");
  }
  public  void glPointParameterfvARB(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glPointParameterfvARB(arg0,arg1);
    checkGLGetError("glPointParameterfvARB");
  }
  public  void glPointParameterfvARB(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glPointParameterfvARB(arg0,arg1,arg2);
    checkGLGetError("glPointParameterfvARB");
  }
  public  void glPointParameterfvEXT(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glPointParameterfvEXT(arg0,arg1);
    checkGLGetError("glPointParameterfvEXT");
  }
  public  void glPointParameterfvEXT(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glPointParameterfvEXT(arg0,arg1,arg2);
    checkGLGetError("glPointParameterfvEXT");
  }
  public  void glPointParameterfvSGIS(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glPointParameterfvSGIS(arg0,arg1);
    checkGLGetError("glPointParameterfvSGIS");
  }
  public  void glPointParameterfvSGIS(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glPointParameterfvSGIS(arg0,arg1,arg2);
    checkGLGetError("glPointParameterfvSGIS");
  }
  public  void glPointParameteri(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glPointParameteri(arg0,arg1);
    checkGLGetError("glPointParameteri");
  }
  public  void glPointParameteriNV(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glPointParameteriNV(arg0,arg1);
    checkGLGetError("glPointParameteriNV");
  }
  public  void glPointParameteriv(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glPointParameteriv(arg0,arg1);
    checkGLGetError("glPointParameteriv");
  }
  public  void glPointParameteriv(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glPointParameteriv(arg0,arg1,arg2);
    checkGLGetError("glPointParameteriv");
  }
  public  void glPointParameterivNV(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glPointParameterivNV(arg0,arg1);
    checkGLGetError("glPointParameterivNV");
  }
  public  void glPointParameterivNV(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glPointParameterivNV(arg0,arg1,arg2);
    checkGLGetError("glPointParameterivNV");
  }
  public  void glPointSize(float arg0)
  {
        checkContext();
downstreamGL.glPointSize(arg0);
    checkGLGetError("glPointSize");
  }
  public  int glPollAsyncSGIX(java.nio.IntBuffer arg0)
  {
        checkContext();
int _res = downstreamGL.glPollAsyncSGIX(arg0);
    checkGLGetError("glPollAsyncSGIX");
    return _res;
  }
  public  int glPollAsyncSGIX(int[] arg0,int arg1)
  {
        checkContext();
int _res = downstreamGL.glPollAsyncSGIX(arg0,arg1);
    checkGLGetError("glPollAsyncSGIX");
    return _res;
  }
  public  int glPollInstrumentsSGIX(java.nio.IntBuffer arg0)
  {
        checkContext();
int _res = downstreamGL.glPollInstrumentsSGIX(arg0);
    checkGLGetError("glPollInstrumentsSGIX");
    return _res;
  }
  public  int glPollInstrumentsSGIX(int[] arg0,int arg1)
  {
        checkContext();
int _res = downstreamGL.glPollInstrumentsSGIX(arg0,arg1);
    checkGLGetError("glPollInstrumentsSGIX");
    return _res;
  }
  public  void glPolygonMode(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glPolygonMode(arg0,arg1);
    checkGLGetError("glPolygonMode");
  }
  public  void glPolygonOffset(float arg0,float arg1)
  {
        checkContext();
downstreamGL.glPolygonOffset(arg0,arg1);
    checkGLGetError("glPolygonOffset");
  }
  public  void glPolygonStipple(java.nio.ByteBuffer arg0)
  {
        checkContext();
downstreamGL.glPolygonStipple(arg0);
    checkGLGetError("glPolygonStipple");
  }
  public  void glPolygonStipple(byte[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glPolygonStipple(arg0,arg1);
    checkGLGetError("glPolygonStipple");
  }
  public  void glPolygonStipple(long arg0)
  {
        checkContext();
downstreamGL.glPolygonStipple(arg0);
    checkGLGetError("glPolygonStipple");
  }
  public  void glPopAttrib()
  {
        checkContext();
downstreamGL.glPopAttrib();
    checkGLGetError("glPopAttrib");
  }
  public  void glPopClientAttrib()
  {
        checkContext();
downstreamGL.glPopClientAttrib();
    checkGLGetError("glPopClientAttrib");
  }
  public  void glPopMatrix()
  {
        checkContext();
downstreamGL.glPopMatrix();
    checkGLGetError("glPopMatrix");
  }
  public  void glPopName()
  {
        checkContext();
downstreamGL.glPopName();
    checkGLGetError("glPopName");
  }
  public  void glPrimitiveRestartIndexNV(int arg0)
  {
        checkContext();
downstreamGL.glPrimitiveRestartIndexNV(arg0);
    checkGLGetError("glPrimitiveRestartIndexNV");
  }
  public  void glPrimitiveRestartNV()
  {
        checkContext();
downstreamGL.glPrimitiveRestartNV();
    checkGLGetError("glPrimitiveRestartNV");
  }
  public  void glPrioritizeTextures(int arg0,java.nio.IntBuffer arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glPrioritizeTextures(arg0,arg1,arg2);
    checkGLGetError("glPrioritizeTextures");
  }
  public  void glPrioritizeTextures(int arg0,int[] arg1,int arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glPrioritizeTextures(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glPrioritizeTextures");
  }
  public  void glProgramBufferParametersIivNV(int arg0,int arg1,int arg2,int arg3,java.nio.IntBuffer arg4)
  {
        checkContext();
downstreamGL.glProgramBufferParametersIivNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glProgramBufferParametersIivNV");
  }
  public  void glProgramBufferParametersIivNV(int arg0,int arg1,int arg2,int arg3,int[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glProgramBufferParametersIivNV(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glProgramBufferParametersIivNV");
  }
  public  void glProgramBufferParametersIuivNV(int arg0,int arg1,int arg2,int arg3,java.nio.IntBuffer arg4)
  {
        checkContext();
downstreamGL.glProgramBufferParametersIuivNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glProgramBufferParametersIuivNV");
  }
  public  void glProgramBufferParametersIuivNV(int arg0,int arg1,int arg2,int arg3,int[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glProgramBufferParametersIuivNV(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glProgramBufferParametersIuivNV");
  }
  public  void glProgramBufferParametersfvNV(int arg0,int arg1,int arg2,int arg3,java.nio.FloatBuffer arg4)
  {
        checkContext();
downstreamGL.glProgramBufferParametersfvNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glProgramBufferParametersfvNV");
  }
  public  void glProgramBufferParametersfvNV(int arg0,int arg1,int arg2,int arg3,float[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glProgramBufferParametersfvNV(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glProgramBufferParametersfvNV");
  }
  public  void glProgramEnvParameter4dARB(int arg0,int arg1,double arg2,double arg3,double arg4,double arg5)
  {
        checkContext();
downstreamGL.glProgramEnvParameter4dARB(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glProgramEnvParameter4dARB");
  }
  public  void glProgramEnvParameter4dvARB(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
        checkContext();
downstreamGL.glProgramEnvParameter4dvARB(arg0,arg1,arg2);
    checkGLGetError("glProgramEnvParameter4dvARB");
  }
  public  void glProgramEnvParameter4dvARB(int arg0,int arg1,double[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glProgramEnvParameter4dvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramEnvParameter4dvARB");
  }
  public  void glProgramEnvParameter4fARB(int arg0,int arg1,float arg2,float arg3,float arg4,float arg5)
  {
        checkContext();
downstreamGL.glProgramEnvParameter4fARB(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glProgramEnvParameter4fARB");
  }
  public  void glProgramEnvParameter4fvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glProgramEnvParameter4fvARB(arg0,arg1,arg2);
    checkGLGetError("glProgramEnvParameter4fvARB");
  }
  public  void glProgramEnvParameter4fvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glProgramEnvParameter4fvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramEnvParameter4fvARB");
  }
  public  void glProgramEnvParameterI4iNV(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
        checkContext();
downstreamGL.glProgramEnvParameterI4iNV(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glProgramEnvParameterI4iNV");
  }
  public  void glProgramEnvParameterI4ivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glProgramEnvParameterI4ivNV(arg0,arg1,arg2);
    checkGLGetError("glProgramEnvParameterI4ivNV");
  }
  public  void glProgramEnvParameterI4ivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glProgramEnvParameterI4ivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramEnvParameterI4ivNV");
  }
  public  void glProgramEnvParameterI4uiNV(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
        checkContext();
downstreamGL.glProgramEnvParameterI4uiNV(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glProgramEnvParameterI4uiNV");
  }
  public  void glProgramEnvParameterI4uivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glProgramEnvParameterI4uivNV(arg0,arg1,arg2);
    checkGLGetError("glProgramEnvParameterI4uivNV");
  }
  public  void glProgramEnvParameterI4uivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glProgramEnvParameterI4uivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramEnvParameterI4uivNV");
  }
  public  void glProgramEnvParameters4fvEXT(int arg0,int arg1,int arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glProgramEnvParameters4fvEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramEnvParameters4fvEXT");
  }
  public  void glProgramEnvParameters4fvEXT(int arg0,int arg1,int arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glProgramEnvParameters4fvEXT(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glProgramEnvParameters4fvEXT");
  }
  public  void glProgramEnvParametersI4ivNV(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
        checkContext();
downstreamGL.glProgramEnvParametersI4ivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramEnvParametersI4ivNV");
  }
  public  void glProgramEnvParametersI4ivNV(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glProgramEnvParametersI4ivNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glProgramEnvParametersI4ivNV");
  }
  public  void glProgramEnvParametersI4uivNV(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
        checkContext();
downstreamGL.glProgramEnvParametersI4uivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramEnvParametersI4uivNV");
  }
  public  void glProgramEnvParametersI4uivNV(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glProgramEnvParametersI4uivNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glProgramEnvParametersI4uivNV");
  }
  public  void glProgramLocalParameter4dARB(int arg0,int arg1,double arg2,double arg3,double arg4,double arg5)
  {
        checkContext();
downstreamGL.glProgramLocalParameter4dARB(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glProgramLocalParameter4dARB");
  }
  public  void glProgramLocalParameter4dvARB(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
        checkContext();
downstreamGL.glProgramLocalParameter4dvARB(arg0,arg1,arg2);
    checkGLGetError("glProgramLocalParameter4dvARB");
  }
  public  void glProgramLocalParameter4dvARB(int arg0,int arg1,double[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glProgramLocalParameter4dvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramLocalParameter4dvARB");
  }
  public  void glProgramLocalParameter4fARB(int arg0,int arg1,float arg2,float arg3,float arg4,float arg5)
  {
        checkContext();
downstreamGL.glProgramLocalParameter4fARB(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glProgramLocalParameter4fARB");
  }
  public  void glProgramLocalParameter4fvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glProgramLocalParameter4fvARB(arg0,arg1,arg2);
    checkGLGetError("glProgramLocalParameter4fvARB");
  }
  public  void glProgramLocalParameter4fvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glProgramLocalParameter4fvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramLocalParameter4fvARB");
  }
  public  void glProgramLocalParameterI4iNV(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
        checkContext();
downstreamGL.glProgramLocalParameterI4iNV(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glProgramLocalParameterI4iNV");
  }
  public  void glProgramLocalParameterI4ivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glProgramLocalParameterI4ivNV(arg0,arg1,arg2);
    checkGLGetError("glProgramLocalParameterI4ivNV");
  }
  public  void glProgramLocalParameterI4ivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glProgramLocalParameterI4ivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramLocalParameterI4ivNV");
  }
  public  void glProgramLocalParameterI4uiNV(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
        checkContext();
downstreamGL.glProgramLocalParameterI4uiNV(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glProgramLocalParameterI4uiNV");
  }
  public  void glProgramLocalParameterI4uivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glProgramLocalParameterI4uivNV(arg0,arg1,arg2);
    checkGLGetError("glProgramLocalParameterI4uivNV");
  }
  public  void glProgramLocalParameterI4uivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glProgramLocalParameterI4uivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramLocalParameterI4uivNV");
  }
  public  void glProgramLocalParameters4fvEXT(int arg0,int arg1,int arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glProgramLocalParameters4fvEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramLocalParameters4fvEXT");
  }
  public  void glProgramLocalParameters4fvEXT(int arg0,int arg1,int arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glProgramLocalParameters4fvEXT(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glProgramLocalParameters4fvEXT");
  }
  public  void glProgramLocalParametersI4ivNV(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
        checkContext();
downstreamGL.glProgramLocalParametersI4ivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramLocalParametersI4ivNV");
  }
  public  void glProgramLocalParametersI4ivNV(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glProgramLocalParametersI4ivNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glProgramLocalParametersI4ivNV");
  }
  public  void glProgramLocalParametersI4uivNV(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
        checkContext();
downstreamGL.glProgramLocalParametersI4uivNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramLocalParametersI4uivNV");
  }
  public  void glProgramLocalParametersI4uivNV(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glProgramLocalParametersI4uivNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glProgramLocalParametersI4uivNV");
  }
  public  void glProgramNamedParameter4dNV(int arg0,int arg1,java.lang.String arg2,double arg3,double arg4,double arg5,double arg6)
  {
        checkContext();
downstreamGL.glProgramNamedParameter4dNV(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glProgramNamedParameter4dNV");
  }
  public  void glProgramNamedParameter4dvNV(int arg0,int arg1,java.lang.String arg2,java.nio.DoubleBuffer arg3)
  {
        checkContext();
downstreamGL.glProgramNamedParameter4dvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramNamedParameter4dvNV");
  }
  public  void glProgramNamedParameter4dvNV(int arg0,int arg1,java.lang.String arg2,double[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glProgramNamedParameter4dvNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glProgramNamedParameter4dvNV");
  }
  public  void glProgramNamedParameter4fNV(int arg0,int arg1,java.lang.String arg2,float arg3,float arg4,float arg5,float arg6)
  {
        checkContext();
downstreamGL.glProgramNamedParameter4fNV(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glProgramNamedParameter4fNV");
  }
  public  void glProgramNamedParameter4fvNV(int arg0,int arg1,java.lang.String arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glProgramNamedParameter4fvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramNamedParameter4fvNV");
  }
  public  void glProgramNamedParameter4fvNV(int arg0,int arg1,java.lang.String arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glProgramNamedParameter4fvNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glProgramNamedParameter4fvNV");
  }
  public  void glProgramParameter4dNV(int arg0,int arg1,double arg2,double arg3,double arg4,double arg5)
  {
        checkContext();
downstreamGL.glProgramParameter4dNV(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glProgramParameter4dNV");
  }
  public  void glProgramParameter4dvNV(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
        checkContext();
downstreamGL.glProgramParameter4dvNV(arg0,arg1,arg2);
    checkGLGetError("glProgramParameter4dvNV");
  }
  public  void glProgramParameter4dvNV(int arg0,int arg1,double[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glProgramParameter4dvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramParameter4dvNV");
  }
  public  void glProgramParameter4fNV(int arg0,int arg1,float arg2,float arg3,float arg4,float arg5)
  {
        checkContext();
downstreamGL.glProgramParameter4fNV(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glProgramParameter4fNV");
  }
  public  void glProgramParameter4fvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glProgramParameter4fvNV(arg0,arg1,arg2);
    checkGLGetError("glProgramParameter4fvNV");
  }
  public  void glProgramParameter4fvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glProgramParameter4fvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramParameter4fvNV");
  }
  public  void glProgramParameteriEXT(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glProgramParameteriEXT(arg0,arg1,arg2);
    checkGLGetError("glProgramParameteriEXT");
  }
  public  void glProgramParameters4dvNV(int arg0,int arg1,int arg2,java.nio.DoubleBuffer arg3)
  {
        checkContext();
downstreamGL.glProgramParameters4dvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramParameters4dvNV");
  }
  public  void glProgramParameters4dvNV(int arg0,int arg1,int arg2,double[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glProgramParameters4dvNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glProgramParameters4dvNV");
  }
  public  void glProgramParameters4fvNV(int arg0,int arg1,int arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glProgramParameters4fvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramParameters4fvNV");
  }
  public  void glProgramParameters4fvNV(int arg0,int arg1,int arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glProgramParameters4fvNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glProgramParameters4fvNV");
  }
  public  void glProgramStringARB(int arg0,int arg1,int arg2,java.lang.String arg3)
  {
        checkContext();
downstreamGL.glProgramStringARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glProgramStringARB");
  }
  public  void glProgramVertexLimitNV(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glProgramVertexLimitNV(arg0,arg1);
    checkGLGetError("glProgramVertexLimitNV");
  }
  public  void glPushAttrib(int arg0)
  {
        checkContext();
downstreamGL.glPushAttrib(arg0);
    checkGLGetError("glPushAttrib");
  }
  public  void glPushClientAttrib(int arg0)
  {
        checkContext();
downstreamGL.glPushClientAttrib(arg0);
    checkGLGetError("glPushClientAttrib");
  }
  public  void glPushMatrix()
  {
        checkContext();
downstreamGL.glPushMatrix();
    checkGLGetError("glPushMatrix");
  }
  public  void glPushName(int arg0)
  {
        checkContext();
downstreamGL.glPushName(arg0);
    checkGLGetError("glPushName");
  }
  public  void glRasterPos2d(double arg0,double arg1)
  {
        checkContext();
downstreamGL.glRasterPos2d(arg0,arg1);
    checkGLGetError("glRasterPos2d");
  }
  public  void glRasterPos2dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glRasterPos2dv(arg0);
    checkGLGetError("glRasterPos2dv");
  }
  public  void glRasterPos2dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glRasterPos2dv(arg0,arg1);
    checkGLGetError("glRasterPos2dv");
  }
  public  void glRasterPos2f(float arg0,float arg1)
  {
        checkContext();
downstreamGL.glRasterPos2f(arg0,arg1);
    checkGLGetError("glRasterPos2f");
  }
  public  void glRasterPos2fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glRasterPos2fv(arg0);
    checkGLGetError("glRasterPos2fv");
  }
  public  void glRasterPos2fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glRasterPos2fv(arg0,arg1);
    checkGLGetError("glRasterPos2fv");
  }
  public  void glRasterPos2i(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glRasterPos2i(arg0,arg1);
    checkGLGetError("glRasterPos2i");
  }
  public  void glRasterPos2iv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glRasterPos2iv(arg0);
    checkGLGetError("glRasterPos2iv");
  }
  public  void glRasterPos2iv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glRasterPos2iv(arg0,arg1);
    checkGLGetError("glRasterPos2iv");
  }
  public  void glRasterPos2s(short arg0,short arg1)
  {
        checkContext();
downstreamGL.glRasterPos2s(arg0,arg1);
    checkGLGetError("glRasterPos2s");
  }
  public  void glRasterPos2sv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glRasterPos2sv(arg0);
    checkGLGetError("glRasterPos2sv");
  }
  public  void glRasterPos2sv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glRasterPos2sv(arg0,arg1);
    checkGLGetError("glRasterPos2sv");
  }
  public  void glRasterPos3d(double arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glRasterPos3d(arg0,arg1,arg2);
    checkGLGetError("glRasterPos3d");
  }
  public  void glRasterPos3dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glRasterPos3dv(arg0);
    checkGLGetError("glRasterPos3dv");
  }
  public  void glRasterPos3dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glRasterPos3dv(arg0,arg1);
    checkGLGetError("glRasterPos3dv");
  }
  public  void glRasterPos3f(float arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glRasterPos3f(arg0,arg1,arg2);
    checkGLGetError("glRasterPos3f");
  }
  public  void glRasterPos3fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glRasterPos3fv(arg0);
    checkGLGetError("glRasterPos3fv");
  }
  public  void glRasterPos3fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glRasterPos3fv(arg0,arg1);
    checkGLGetError("glRasterPos3fv");
  }
  public  void glRasterPos3i(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glRasterPos3i(arg0,arg1,arg2);
    checkGLGetError("glRasterPos3i");
  }
  public  void glRasterPos3iv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glRasterPos3iv(arg0);
    checkGLGetError("glRasterPos3iv");
  }
  public  void glRasterPos3iv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glRasterPos3iv(arg0,arg1);
    checkGLGetError("glRasterPos3iv");
  }
  public  void glRasterPos3s(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glRasterPos3s(arg0,arg1,arg2);
    checkGLGetError("glRasterPos3s");
  }
  public  void glRasterPos3sv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glRasterPos3sv(arg0);
    checkGLGetError("glRasterPos3sv");
  }
  public  void glRasterPos3sv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glRasterPos3sv(arg0,arg1);
    checkGLGetError("glRasterPos3sv");
  }
  public  void glRasterPos4d(double arg0,double arg1,double arg2,double arg3)
  {
        checkContext();
downstreamGL.glRasterPos4d(arg0,arg1,arg2,arg3);
    checkGLGetError("glRasterPos4d");
  }
  public  void glRasterPos4dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glRasterPos4dv(arg0);
    checkGLGetError("glRasterPos4dv");
  }
  public  void glRasterPos4dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glRasterPos4dv(arg0,arg1);
    checkGLGetError("glRasterPos4dv");
  }
  public  void glRasterPos4f(float arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glRasterPos4f(arg0,arg1,arg2,arg3);
    checkGLGetError("glRasterPos4f");
  }
  public  void glRasterPos4fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glRasterPos4fv(arg0);
    checkGLGetError("glRasterPos4fv");
  }
  public  void glRasterPos4fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glRasterPos4fv(arg0,arg1);
    checkGLGetError("glRasterPos4fv");
  }
  public  void glRasterPos4i(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glRasterPos4i(arg0,arg1,arg2,arg3);
    checkGLGetError("glRasterPos4i");
  }
  public  void glRasterPos4iv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glRasterPos4iv(arg0);
    checkGLGetError("glRasterPos4iv");
  }
  public  void glRasterPos4iv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glRasterPos4iv(arg0,arg1);
    checkGLGetError("glRasterPos4iv");
  }
  public  void glRasterPos4s(short arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glRasterPos4s(arg0,arg1,arg2,arg3);
    checkGLGetError("glRasterPos4s");
  }
  public  void glRasterPos4sv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glRasterPos4sv(arg0);
    checkGLGetError("glRasterPos4sv");
  }
  public  void glRasterPos4sv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glRasterPos4sv(arg0,arg1);
    checkGLGetError("glRasterPos4sv");
  }
  public  void glReadBuffer(int arg0)
  {
        checkContext();
downstreamGL.glReadBuffer(arg0);
    checkGLGetError("glReadBuffer");
  }
  public  void glReadBufferRegion(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glReadBufferRegion(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glReadBufferRegion");
  }
  public  void glReadInstrumentsSGIX(int arg0)
  {
        checkContext();
downstreamGL.glReadInstrumentsSGIX(arg0);
    checkGLGetError("glReadInstrumentsSGIX");
  }
  public  void glReadPixels(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,java.nio.Buffer arg6)
  {
        checkContext();
downstreamGL.glReadPixels(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glReadPixels");
  }
  public  void glReadPixels(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,long arg6)
  {
        checkContext();
downstreamGL.glReadPixels(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glReadPixels");
  }
  public  void glRectd(double arg0,double arg1,double arg2,double arg3)
  {
        checkContext();
downstreamGL.glRectd(arg0,arg1,arg2,arg3);
    checkGLGetError("glRectd");
  }
  public  void glRectdv(java.nio.DoubleBuffer arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glRectdv(arg0,arg1);
    checkGLGetError("glRectdv");
  }
  public  void glRectdv(double[] arg0,int arg1,double[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glRectdv(arg0,arg1,arg2,arg3);
    checkGLGetError("glRectdv");
  }
  public  void glRectf(float arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glRectf(arg0,arg1,arg2,arg3);
    checkGLGetError("glRectf");
  }
  public  void glRectfv(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glRectfv(arg0,arg1);
    checkGLGetError("glRectfv");
  }
  public  void glRectfv(float[] arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glRectfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glRectfv");
  }
  public  void glRecti(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glRecti(arg0,arg1,arg2,arg3);
    checkGLGetError("glRecti");
  }
  public  void glRectiv(java.nio.IntBuffer arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glRectiv(arg0,arg1);
    checkGLGetError("glRectiv");
  }
  public  void glRectiv(int[] arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glRectiv(arg0,arg1,arg2,arg3);
    checkGLGetError("glRectiv");
  }
  public  void glRects(short arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glRects(arg0,arg1,arg2,arg3);
    checkGLGetError("glRects");
  }
  public  void glRectsv(java.nio.ShortBuffer arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glRectsv(arg0,arg1);
    checkGLGetError("glRectsv");
  }
  public  void glRectsv(short[] arg0,int arg1,short[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glRectsv(arg0,arg1,arg2,arg3);
    checkGLGetError("glRectsv");
  }
  public  void glReferencePlaneSGIX(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glReferencePlaneSGIX(arg0);
    checkGLGetError("glReferencePlaneSGIX");
  }
  public  void glReferencePlaneSGIX(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glReferencePlaneSGIX(arg0,arg1);
    checkGLGetError("glReferencePlaneSGIX");
  }
  public  int glRenderMode(int arg0)
  {
        checkContext();
int _res = downstreamGL.glRenderMode(arg0);
    checkGLGetError("glRenderMode");
    return _res;
  }
  public  void glRenderbufferStorageEXT(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glRenderbufferStorageEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glRenderbufferStorageEXT");
  }
  public  void glRenderbufferStorageMultisampleCoverageNV(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
        checkContext();
downstreamGL.glRenderbufferStorageMultisampleCoverageNV(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glRenderbufferStorageMultisampleCoverageNV");
  }
  public  void glRenderbufferStorageMultisampleEXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glRenderbufferStorageMultisampleEXT(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glRenderbufferStorageMultisampleEXT");
  }
  public  void glReplacementCodeuiColor3fVertex3fSUN(int arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6)
  {
        checkContext();
downstreamGL.glReplacementCodeuiColor3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glReplacementCodeuiColor3fVertex3fSUN");
  }
  public  void glReplacementCodeuiColor3fVertex3fvSUN(java.nio.IntBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glReplacementCodeuiColor3fVertex3fvSUN(arg0,arg1,arg2);
    checkGLGetError("glReplacementCodeuiColor3fVertex3fvSUN");
  }
  public  void glReplacementCodeuiColor3fVertex3fvSUN(int[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glReplacementCodeuiColor3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glReplacementCodeuiColor3fVertex3fvSUN");
  }
  public  void glReplacementCodeuiColor4fNormal3fVertex3fSUN(int arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7,float arg8,float arg9,float arg10)
  {
        checkContext();
downstreamGL.glReplacementCodeuiColor4fNormal3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    checkGLGetError("glReplacementCodeuiColor4fNormal3fVertex3fSUN");
  }
  public  void glReplacementCodeuiColor4fNormal3fVertex3fvSUN(java.nio.IntBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glReplacementCodeuiColor4fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3);
    checkGLGetError("glReplacementCodeuiColor4fNormal3fVertex3fvSUN");
  }
  public  void glReplacementCodeuiColor4fNormal3fVertex3fvSUN(int[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5,float[] arg6,int arg7)
  {
        checkContext();
downstreamGL.glReplacementCodeuiColor4fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glReplacementCodeuiColor4fNormal3fVertex3fvSUN");
  }
  public  void glReplacementCodeuiColor4ubVertex3fSUN(int arg0,byte arg1,byte arg2,byte arg3,byte arg4,float arg5,float arg6,float arg7)
  {
        checkContext();
downstreamGL.glReplacementCodeuiColor4ubVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glReplacementCodeuiColor4ubVertex3fSUN");
  }
  public  void glReplacementCodeuiColor4ubVertex3fvSUN(java.nio.IntBuffer arg0,java.nio.ByteBuffer arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glReplacementCodeuiColor4ubVertex3fvSUN(arg0,arg1,arg2);
    checkGLGetError("glReplacementCodeuiColor4ubVertex3fvSUN");
  }
  public  void glReplacementCodeuiColor4ubVertex3fvSUN(int[] arg0,int arg1,byte[] arg2,int arg3,float[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glReplacementCodeuiColor4ubVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glReplacementCodeuiColor4ubVertex3fvSUN");
  }
  public  void glReplacementCodeuiNormal3fVertex3fSUN(int arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6)
  {
        checkContext();
downstreamGL.glReplacementCodeuiNormal3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glReplacementCodeuiNormal3fVertex3fSUN");
  }
  public  void glReplacementCodeuiNormal3fVertex3fvSUN(java.nio.IntBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glReplacementCodeuiNormal3fVertex3fvSUN(arg0,arg1,arg2);
    checkGLGetError("glReplacementCodeuiNormal3fVertex3fvSUN");
  }
  public  void glReplacementCodeuiNormal3fVertex3fvSUN(int[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glReplacementCodeuiNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glReplacementCodeuiNormal3fVertex3fvSUN");
  }
  public  void glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fSUN(int arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7,float arg8,float arg9,float arg10,float arg11,float arg12)
  {
        checkContext();
downstreamGL.glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12);
    checkGLGetError("glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fSUN");
  }
  public  void glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fvSUN(java.nio.IntBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2,java.nio.FloatBuffer arg3,java.nio.FloatBuffer arg4)
  {
        checkContext();
downstreamGL.glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fvSUN");
  }
  public  void glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fvSUN(int[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5,float[] arg6,int arg7,float[] arg8,int arg9)
  {
        checkContext();
downstreamGL.glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    checkGLGetError("glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fvSUN");
  }
  public  void glReplacementCodeuiTexCoord2fNormal3fVertex3fSUN(int arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7,float arg8)
  {
        checkContext();
downstreamGL.glReplacementCodeuiTexCoord2fNormal3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    checkGLGetError("glReplacementCodeuiTexCoord2fNormal3fVertex3fSUN");
  }
  public  void glReplacementCodeuiTexCoord2fNormal3fVertex3fvSUN(java.nio.IntBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glReplacementCodeuiTexCoord2fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3);
    checkGLGetError("glReplacementCodeuiTexCoord2fNormal3fVertex3fvSUN");
  }
  public  void glReplacementCodeuiTexCoord2fNormal3fVertex3fvSUN(int[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5,float[] arg6,int arg7)
  {
        checkContext();
downstreamGL.glReplacementCodeuiTexCoord2fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glReplacementCodeuiTexCoord2fNormal3fVertex3fvSUN");
  }
  public  void glReplacementCodeuiTexCoord2fVertex3fSUN(int arg0,float arg1,float arg2,float arg3,float arg4,float arg5)
  {
        checkContext();
downstreamGL.glReplacementCodeuiTexCoord2fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glReplacementCodeuiTexCoord2fVertex3fSUN");
  }
  public  void glReplacementCodeuiTexCoord2fVertex3fvSUN(java.nio.IntBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glReplacementCodeuiTexCoord2fVertex3fvSUN(arg0,arg1,arg2);
    checkGLGetError("glReplacementCodeuiTexCoord2fVertex3fvSUN");
  }
  public  void glReplacementCodeuiTexCoord2fVertex3fvSUN(int[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glReplacementCodeuiTexCoord2fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glReplacementCodeuiTexCoord2fVertex3fvSUN");
  }
  public  void glReplacementCodeuiVertex3fSUN(int arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glReplacementCodeuiVertex3fSUN(arg0,arg1,arg2,arg3);
    checkGLGetError("glReplacementCodeuiVertex3fSUN");
  }
  public  void glReplacementCodeuiVertex3fvSUN(java.nio.IntBuffer arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glReplacementCodeuiVertex3fvSUN(arg0,arg1);
    checkGLGetError("glReplacementCodeuiVertex3fvSUN");
  }
  public  void glReplacementCodeuiVertex3fvSUN(int[] arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glReplacementCodeuiVertex3fvSUN(arg0,arg1,arg2,arg3);
    checkGLGetError("glReplacementCodeuiVertex3fvSUN");
  }
  public  void glRequestResidentProgramsNV(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glRequestResidentProgramsNV(arg0,arg1);
    checkGLGetError("glRequestResidentProgramsNV");
  }
  public  void glRequestResidentProgramsNV(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glRequestResidentProgramsNV(arg0,arg1,arg2);
    checkGLGetError("glRequestResidentProgramsNV");
  }
  public  void glResetHistogram(int arg0)
  {
        checkContext();
downstreamGL.glResetHistogram(arg0);
    checkGLGetError("glResetHistogram");
  }
  public  void glResetMinmax(int arg0)
  {
        checkContext();
downstreamGL.glResetMinmax(arg0);
    checkGLGetError("glResetMinmax");
  }
  public  void glResizeBuffersMESA()
  {
        checkContext();
downstreamGL.glResizeBuffersMESA();
    checkGLGetError("glResizeBuffersMESA");
  }
  public  void glRotated(double arg0,double arg1,double arg2,double arg3)
  {
        checkContext();
downstreamGL.glRotated(arg0,arg1,arg2,arg3);
    checkGLGetError("glRotated");
  }
  public  void glRotatef(float arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glRotatef(arg0,arg1,arg2,arg3);
    checkGLGetError("glRotatef");
  }
  public  void glSampleCoverage(float arg0,boolean arg1)
  {
        checkContext();
downstreamGL.glSampleCoverage(arg0,arg1);
    checkGLGetError("glSampleCoverage");
  }
  public  void glSampleMapATI(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glSampleMapATI(arg0,arg1,arg2);
    checkGLGetError("glSampleMapATI");
  }
  public  void glSampleMaskEXT(float arg0,boolean arg1)
  {
        checkContext();
downstreamGL.glSampleMaskEXT(arg0,arg1);
    checkGLGetError("glSampleMaskEXT");
  }
  public  void glSampleMaskSGIS(float arg0,boolean arg1)
  {
        checkContext();
downstreamGL.glSampleMaskSGIS(arg0,arg1);
    checkGLGetError("glSampleMaskSGIS");
  }
  public  void glSamplePatternEXT(int arg0)
  {
        checkContext();
downstreamGL.glSamplePatternEXT(arg0);
    checkGLGetError("glSamplePatternEXT");
  }
  public  void glSamplePatternSGIS(int arg0)
  {
        checkContext();
downstreamGL.glSamplePatternSGIS(arg0);
    checkGLGetError("glSamplePatternSGIS");
  }
  public  void glScaled(double arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glScaled(arg0,arg1,arg2);
    checkGLGetError("glScaled");
  }
  public  void glScalef(float arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glScalef(arg0,arg1,arg2);
    checkGLGetError("glScalef");
  }
  public  void glScissor(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glScissor(arg0,arg1,arg2,arg3);
    checkGLGetError("glScissor");
  }
  public  void glSecondaryColor3b(byte arg0,byte arg1,byte arg2)
  {
        checkContext();
downstreamGL.glSecondaryColor3b(arg0,arg1,arg2);
    checkGLGetError("glSecondaryColor3b");
  }
  public  void glSecondaryColor3bEXT(byte arg0,byte arg1,byte arg2)
  {
        checkContext();
downstreamGL.glSecondaryColor3bEXT(arg0,arg1,arg2);
    checkGLGetError("glSecondaryColor3bEXT");
  }
  public  void glSecondaryColor3bv(java.nio.ByteBuffer arg0)
  {
        checkContext();
downstreamGL.glSecondaryColor3bv(arg0);
    checkGLGetError("glSecondaryColor3bv");
  }
  public  void glSecondaryColor3bv(byte[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glSecondaryColor3bv(arg0,arg1);
    checkGLGetError("glSecondaryColor3bv");
  }
  public  void glSecondaryColor3bvEXT(java.nio.ByteBuffer arg0)
  {
        checkContext();
downstreamGL.glSecondaryColor3bvEXT(arg0);
    checkGLGetError("glSecondaryColor3bvEXT");
  }
  public  void glSecondaryColor3bvEXT(byte[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glSecondaryColor3bvEXT(arg0,arg1);
    checkGLGetError("glSecondaryColor3bvEXT");
  }
  public  void glSecondaryColor3d(double arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glSecondaryColor3d(arg0,arg1,arg2);
    checkGLGetError("glSecondaryColor3d");
  }
  public  void glSecondaryColor3dEXT(double arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glSecondaryColor3dEXT(arg0,arg1,arg2);
    checkGLGetError("glSecondaryColor3dEXT");
  }
  public  void glSecondaryColor3dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glSecondaryColor3dv(arg0);
    checkGLGetError("glSecondaryColor3dv");
  }
  public  void glSecondaryColor3dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glSecondaryColor3dv(arg0,arg1);
    checkGLGetError("glSecondaryColor3dv");
  }
  public  void glSecondaryColor3dvEXT(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glSecondaryColor3dvEXT(arg0);
    checkGLGetError("glSecondaryColor3dvEXT");
  }
  public  void glSecondaryColor3dvEXT(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glSecondaryColor3dvEXT(arg0,arg1);
    checkGLGetError("glSecondaryColor3dvEXT");
  }
  public  void glSecondaryColor3f(float arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glSecondaryColor3f(arg0,arg1,arg2);
    checkGLGetError("glSecondaryColor3f");
  }
  public  void glSecondaryColor3fEXT(float arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glSecondaryColor3fEXT(arg0,arg1,arg2);
    checkGLGetError("glSecondaryColor3fEXT");
  }
  public  void glSecondaryColor3fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glSecondaryColor3fv(arg0);
    checkGLGetError("glSecondaryColor3fv");
  }
  public  void glSecondaryColor3fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glSecondaryColor3fv(arg0,arg1);
    checkGLGetError("glSecondaryColor3fv");
  }
  public  void glSecondaryColor3fvEXT(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glSecondaryColor3fvEXT(arg0);
    checkGLGetError("glSecondaryColor3fvEXT");
  }
  public  void glSecondaryColor3fvEXT(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glSecondaryColor3fvEXT(arg0,arg1);
    checkGLGetError("glSecondaryColor3fvEXT");
  }
  public  void glSecondaryColor3hNV(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glSecondaryColor3hNV(arg0,arg1,arg2);
    checkGLGetError("glSecondaryColor3hNV");
  }
  public  void glSecondaryColor3hvNV(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glSecondaryColor3hvNV(arg0);
    checkGLGetError("glSecondaryColor3hvNV");
  }
  public  void glSecondaryColor3hvNV(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glSecondaryColor3hvNV(arg0,arg1);
    checkGLGetError("glSecondaryColor3hvNV");
  }
  public  void glSecondaryColor3i(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glSecondaryColor3i(arg0,arg1,arg2);
    checkGLGetError("glSecondaryColor3i");
  }
  public  void glSecondaryColor3iEXT(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glSecondaryColor3iEXT(arg0,arg1,arg2);
    checkGLGetError("glSecondaryColor3iEXT");
  }
  public  void glSecondaryColor3iv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glSecondaryColor3iv(arg0);
    checkGLGetError("glSecondaryColor3iv");
  }
  public  void glSecondaryColor3iv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glSecondaryColor3iv(arg0,arg1);
    checkGLGetError("glSecondaryColor3iv");
  }
  public  void glSecondaryColor3ivEXT(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glSecondaryColor3ivEXT(arg0);
    checkGLGetError("glSecondaryColor3ivEXT");
  }
  public  void glSecondaryColor3ivEXT(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glSecondaryColor3ivEXT(arg0,arg1);
    checkGLGetError("glSecondaryColor3ivEXT");
  }
  public  void glSecondaryColor3s(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glSecondaryColor3s(arg0,arg1,arg2);
    checkGLGetError("glSecondaryColor3s");
  }
  public  void glSecondaryColor3sEXT(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glSecondaryColor3sEXT(arg0,arg1,arg2);
    checkGLGetError("glSecondaryColor3sEXT");
  }
  public  void glSecondaryColor3sv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glSecondaryColor3sv(arg0);
    checkGLGetError("glSecondaryColor3sv");
  }
  public  void glSecondaryColor3sv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glSecondaryColor3sv(arg0,arg1);
    checkGLGetError("glSecondaryColor3sv");
  }
  public  void glSecondaryColor3svEXT(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glSecondaryColor3svEXT(arg0);
    checkGLGetError("glSecondaryColor3svEXT");
  }
  public  void glSecondaryColor3svEXT(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glSecondaryColor3svEXT(arg0,arg1);
    checkGLGetError("glSecondaryColor3svEXT");
  }
  public  void glSecondaryColor3ub(byte arg0,byte arg1,byte arg2)
  {
        checkContext();
downstreamGL.glSecondaryColor3ub(arg0,arg1,arg2);
    checkGLGetError("glSecondaryColor3ub");
  }
  public  void glSecondaryColor3ubEXT(byte arg0,byte arg1,byte arg2)
  {
        checkContext();
downstreamGL.glSecondaryColor3ubEXT(arg0,arg1,arg2);
    checkGLGetError("glSecondaryColor3ubEXT");
  }
  public  void glSecondaryColor3ubv(java.nio.ByteBuffer arg0)
  {
        checkContext();
downstreamGL.glSecondaryColor3ubv(arg0);
    checkGLGetError("glSecondaryColor3ubv");
  }
  public  void glSecondaryColor3ubv(byte[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glSecondaryColor3ubv(arg0,arg1);
    checkGLGetError("glSecondaryColor3ubv");
  }
  public  void glSecondaryColor3ubvEXT(java.nio.ByteBuffer arg0)
  {
        checkContext();
downstreamGL.glSecondaryColor3ubvEXT(arg0);
    checkGLGetError("glSecondaryColor3ubvEXT");
  }
  public  void glSecondaryColor3ubvEXT(byte[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glSecondaryColor3ubvEXT(arg0,arg1);
    checkGLGetError("glSecondaryColor3ubvEXT");
  }
  public  void glSecondaryColor3ui(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glSecondaryColor3ui(arg0,arg1,arg2);
    checkGLGetError("glSecondaryColor3ui");
  }
  public  void glSecondaryColor3uiEXT(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glSecondaryColor3uiEXT(arg0,arg1,arg2);
    checkGLGetError("glSecondaryColor3uiEXT");
  }
  public  void glSecondaryColor3uiv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glSecondaryColor3uiv(arg0);
    checkGLGetError("glSecondaryColor3uiv");
  }
  public  void glSecondaryColor3uiv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glSecondaryColor3uiv(arg0,arg1);
    checkGLGetError("glSecondaryColor3uiv");
  }
  public  void glSecondaryColor3uivEXT(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glSecondaryColor3uivEXT(arg0);
    checkGLGetError("glSecondaryColor3uivEXT");
  }
  public  void glSecondaryColor3uivEXT(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glSecondaryColor3uivEXT(arg0,arg1);
    checkGLGetError("glSecondaryColor3uivEXT");
  }
  public  void glSecondaryColor3us(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glSecondaryColor3us(arg0,arg1,arg2);
    checkGLGetError("glSecondaryColor3us");
  }
  public  void glSecondaryColor3usEXT(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glSecondaryColor3usEXT(arg0,arg1,arg2);
    checkGLGetError("glSecondaryColor3usEXT");
  }
  public  void glSecondaryColor3usv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glSecondaryColor3usv(arg0);
    checkGLGetError("glSecondaryColor3usv");
  }
  public  void glSecondaryColor3usv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glSecondaryColor3usv(arg0,arg1);
    checkGLGetError("glSecondaryColor3usv");
  }
  public  void glSecondaryColor3usvEXT(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glSecondaryColor3usvEXT(arg0);
    checkGLGetError("glSecondaryColor3usvEXT");
  }
  public  void glSecondaryColor3usvEXT(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glSecondaryColor3usvEXT(arg0,arg1);
    checkGLGetError("glSecondaryColor3usvEXT");
  }
  public  void glSecondaryColorPointer(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
        checkContext();
downstreamGL.glSecondaryColorPointer(arg0,arg1,arg2,arg3);
    checkGLGetError("glSecondaryColorPointer");
  }
  public  void glSecondaryColorPointer(int arg0,int arg1,int arg2,long arg3)
  {
        checkContext();
downstreamGL.glSecondaryColorPointer(arg0,arg1,arg2,arg3);
    checkGLGetError("glSecondaryColorPointer");
  }
  public  void glSecondaryColorPointerEXT(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
        checkContext();
downstreamGL.glSecondaryColorPointerEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glSecondaryColorPointerEXT");
  }
  public  void glSecondaryColorPointerEXT(int arg0,int arg1,int arg2,long arg3)
  {
        checkContext();
downstreamGL.glSecondaryColorPointerEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glSecondaryColorPointerEXT");
  }
  public  void glSelectBuffer(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glSelectBuffer(arg0,arg1);
    checkGLGetError("glSelectBuffer");
  }
  public  void glSeparableFilter2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,java.nio.Buffer arg6,java.nio.Buffer arg7)
  {
        checkContext();
downstreamGL.glSeparableFilter2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glSeparableFilter2D");
  }
  public  void glSeparableFilter2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,long arg6,long arg7)
  {
        checkContext();
downstreamGL.glSeparableFilter2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glSeparableFilter2D");
  }
  public  void glSetFenceAPPLE(int arg0)
  {
        checkContext();
downstreamGL.glSetFenceAPPLE(arg0);
    checkGLGetError("glSetFenceAPPLE");
  }
  public  void glSetFenceNV(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glSetFenceNV(arg0,arg1);
    checkGLGetError("glSetFenceNV");
  }
  public  void glSetFragmentShaderConstantATI(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glSetFragmentShaderConstantATI(arg0,arg1);
    checkGLGetError("glSetFragmentShaderConstantATI");
  }
  public  void glSetFragmentShaderConstantATI(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glSetFragmentShaderConstantATI(arg0,arg1,arg2);
    checkGLGetError("glSetFragmentShaderConstantATI");
  }
  public  void glSetInvariantEXT(int arg0,int arg1,java.nio.Buffer arg2)
  {
        checkContext();
downstreamGL.glSetInvariantEXT(arg0,arg1,arg2);
    checkGLGetError("glSetInvariantEXT");
  }
  public  void glSetLocalConstantEXT(int arg0,int arg1,java.nio.Buffer arg2)
  {
        checkContext();
downstreamGL.glSetLocalConstantEXT(arg0,arg1,arg2);
    checkGLGetError("glSetLocalConstantEXT");
  }
  public  void glShadeModel(int arg0)
  {
        checkContext();
downstreamGL.glShadeModel(arg0);
    checkGLGetError("glShadeModel");
  }
  public  void glShaderOp1EXT(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glShaderOp1EXT(arg0,arg1,arg2);
    checkGLGetError("glShaderOp1EXT");
  }
  public  void glShaderOp2EXT(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glShaderOp2EXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glShaderOp2EXT");
  }
  public  void glShaderOp3EXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glShaderOp3EXT(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glShaderOp3EXT");
  }
  public  void glShaderSource(int arg0,int arg1,java.lang.String[] arg2,java.nio.IntBuffer arg3)
  {
        checkContext();
downstreamGL.glShaderSource(arg0,arg1,arg2,arg3);
    checkGLGetError("glShaderSource");
  }
  public  void glShaderSource(int arg0,int arg1,java.lang.String[] arg2,int[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glShaderSource(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glShaderSource");
  }
  public  void glShaderSourceARB(int arg0,int arg1,java.lang.String[] arg2,java.nio.IntBuffer arg3)
  {
        checkContext();
downstreamGL.glShaderSourceARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glShaderSourceARB");
  }
  public  void glShaderSourceARB(int arg0,int arg1,java.lang.String[] arg2,int[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glShaderSourceARB(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glShaderSourceARB");
  }
  public  void glSharpenTexFuncSGIS(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glSharpenTexFuncSGIS(arg0,arg1,arg2);
    checkGLGetError("glSharpenTexFuncSGIS");
  }
  public  void glSharpenTexFuncSGIS(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glSharpenTexFuncSGIS(arg0,arg1,arg2,arg3);
    checkGLGetError("glSharpenTexFuncSGIS");
  }
  public  void glSpriteParameterfSGIX(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glSpriteParameterfSGIX(arg0,arg1);
    checkGLGetError("glSpriteParameterfSGIX");
  }
  public  void glSpriteParameterfvSGIX(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glSpriteParameterfvSGIX(arg0,arg1);
    checkGLGetError("glSpriteParameterfvSGIX");
  }
  public  void glSpriteParameterfvSGIX(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glSpriteParameterfvSGIX(arg0,arg1,arg2);
    checkGLGetError("glSpriteParameterfvSGIX");
  }
  public  void glSpriteParameteriSGIX(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glSpriteParameteriSGIX(arg0,arg1);
    checkGLGetError("glSpriteParameteriSGIX");
  }
  public  void glSpriteParameterivSGIX(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glSpriteParameterivSGIX(arg0,arg1);
    checkGLGetError("glSpriteParameterivSGIX");
  }
  public  void glSpriteParameterivSGIX(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glSpriteParameterivSGIX(arg0,arg1,arg2);
    checkGLGetError("glSpriteParameterivSGIX");
  }
  public  void glStartInstrumentsSGIX()
  {
        checkContext();
downstreamGL.glStartInstrumentsSGIX();
    checkGLGetError("glStartInstrumentsSGIX");
  }
  public  void glStencilClearTagEXT(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glStencilClearTagEXT(arg0,arg1);
    checkGLGetError("glStencilClearTagEXT");
  }
  public  void glStencilFunc(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glStencilFunc(arg0,arg1,arg2);
    checkGLGetError("glStencilFunc");
  }
  public  void glStencilFuncSeparate(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glStencilFuncSeparate(arg0,arg1,arg2,arg3);
    checkGLGetError("glStencilFuncSeparate");
  }
  public  void glStencilFuncSeparateATI(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glStencilFuncSeparateATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glStencilFuncSeparateATI");
  }
  public  void glStencilMask(int arg0)
  {
        checkContext();
downstreamGL.glStencilMask(arg0);
    checkGLGetError("glStencilMask");
  }
  public  void glStencilMaskSeparate(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glStencilMaskSeparate(arg0,arg1);
    checkGLGetError("glStencilMaskSeparate");
  }
  public  void glStencilOp(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glStencilOp(arg0,arg1,arg2);
    checkGLGetError("glStencilOp");
  }
  public  void glStencilOpSeparate(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glStencilOpSeparate(arg0,arg1,arg2,arg3);
    checkGLGetError("glStencilOpSeparate");
  }
  public  void glStencilOpSeparateATI(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glStencilOpSeparateATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glStencilOpSeparateATI");
  }
  public  void glStopInstrumentsSGIX(int arg0)
  {
        checkContext();
downstreamGL.glStopInstrumentsSGIX(arg0);
    checkGLGetError("glStopInstrumentsSGIX");
  }
  public  void glStringMarkerGREMEDY(int arg0,java.nio.Buffer arg1)
  {
        checkContext();
downstreamGL.glStringMarkerGREMEDY(arg0,arg1);
    checkGLGetError("glStringMarkerGREMEDY");
  }
  public  void glSwapAPPLE()
  {
        checkContext();
downstreamGL.glSwapAPPLE();
    checkGLGetError("glSwapAPPLE");
  }
  public  void glSwizzleEXT(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
        checkContext();
downstreamGL.glSwizzleEXT(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glSwizzleEXT");
  }
  public  void glTagSampleBufferSGIX()
  {
        checkContext();
downstreamGL.glTagSampleBufferSGIX();
    checkGLGetError("glTagSampleBufferSGIX");
  }
  public  void glTbufferMask3DFX(int arg0)
  {
        checkContext();
downstreamGL.glTbufferMask3DFX(arg0);
    checkGLGetError("glTbufferMask3DFX");
  }
  public  boolean glTestFenceAPPLE(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glTestFenceAPPLE(arg0);
    checkGLGetError("glTestFenceAPPLE");
    return _res;
  }
  public  boolean glTestFenceNV(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glTestFenceNV(arg0);
    checkGLGetError("glTestFenceNV");
    return _res;
  }
  public  boolean glTestObjectAPPLE(int arg0,int arg1)
  {
        checkContext();
boolean _res = downstreamGL.glTestObjectAPPLE(arg0,arg1);
    checkGLGetError("glTestObjectAPPLE");
    return _res;
  }
  public  void glTexBufferEXT(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glTexBufferEXT(arg0,arg1,arg2);
    checkGLGetError("glTexBufferEXT");
  }
  public  void glTexBumpParameterfvATI(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glTexBumpParameterfvATI(arg0,arg1);
    checkGLGetError("glTexBumpParameterfvATI");
  }
  public  void glTexBumpParameterfvATI(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glTexBumpParameterfvATI(arg0,arg1,arg2);
    checkGLGetError("glTexBumpParameterfvATI");
  }
  public  void glTexBumpParameterivATI(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glTexBumpParameterivATI(arg0,arg1);
    checkGLGetError("glTexBumpParameterivATI");
  }
  public  void glTexBumpParameterivATI(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glTexBumpParameterivATI(arg0,arg1,arg2);
    checkGLGetError("glTexBumpParameterivATI");
  }
  public  void glTexCoord1d(double arg0)
  {
        checkContext();
downstreamGL.glTexCoord1d(arg0);
    checkGLGetError("glTexCoord1d");
  }
  public  void glTexCoord1dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord1dv(arg0);
    checkGLGetError("glTexCoord1dv");
  }
  public  void glTexCoord1dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord1dv(arg0,arg1);
    checkGLGetError("glTexCoord1dv");
  }
  public  void glTexCoord1f(float arg0)
  {
        checkContext();
downstreamGL.glTexCoord1f(arg0);
    checkGLGetError("glTexCoord1f");
  }
  public  void glTexCoord1fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord1fv(arg0);
    checkGLGetError("glTexCoord1fv");
  }
  public  void glTexCoord1fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord1fv(arg0,arg1);
    checkGLGetError("glTexCoord1fv");
  }
  public  void glTexCoord1hNV(short arg0)
  {
        checkContext();
downstreamGL.glTexCoord1hNV(arg0);
    checkGLGetError("glTexCoord1hNV");
  }
  public  void glTexCoord1hvNV(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord1hvNV(arg0);
    checkGLGetError("glTexCoord1hvNV");
  }
  public  void glTexCoord1hvNV(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord1hvNV(arg0,arg1);
    checkGLGetError("glTexCoord1hvNV");
  }
  public  void glTexCoord1i(int arg0)
  {
        checkContext();
downstreamGL.glTexCoord1i(arg0);
    checkGLGetError("glTexCoord1i");
  }
  public  void glTexCoord1iv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord1iv(arg0);
    checkGLGetError("glTexCoord1iv");
  }
  public  void glTexCoord1iv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord1iv(arg0,arg1);
    checkGLGetError("glTexCoord1iv");
  }
  public  void glTexCoord1s(short arg0)
  {
        checkContext();
downstreamGL.glTexCoord1s(arg0);
    checkGLGetError("glTexCoord1s");
  }
  public  void glTexCoord1sv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord1sv(arg0);
    checkGLGetError("glTexCoord1sv");
  }
  public  void glTexCoord1sv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord1sv(arg0,arg1);
    checkGLGetError("glTexCoord1sv");
  }
  public  void glTexCoord2d(double arg0,double arg1)
  {
        checkContext();
downstreamGL.glTexCoord2d(arg0,arg1);
    checkGLGetError("glTexCoord2d");
  }
  public  void glTexCoord2dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord2dv(arg0);
    checkGLGetError("glTexCoord2dv");
  }
  public  void glTexCoord2dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord2dv(arg0,arg1);
    checkGLGetError("glTexCoord2dv");
  }
  public  void glTexCoord2f(float arg0,float arg1)
  {
        checkContext();
downstreamGL.glTexCoord2f(arg0,arg1);
    checkGLGetError("glTexCoord2f");
  }
  public  void glTexCoord2fColor3fVertex3fSUN(float arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7)
  {
        checkContext();
downstreamGL.glTexCoord2fColor3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glTexCoord2fColor3fVertex3fSUN");
  }
  public  void glTexCoord2fColor3fVertex3fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glTexCoord2fColor3fVertex3fvSUN(arg0,arg1,arg2);
    checkGLGetError("glTexCoord2fColor3fVertex3fvSUN");
  }
  public  void glTexCoord2fColor3fVertex3fvSUN(float[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glTexCoord2fColor3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glTexCoord2fColor3fVertex3fvSUN");
  }
  public  void glTexCoord2fColor4fNormal3fVertex3fSUN(float arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7,float arg8,float arg9,float arg10,float arg11)
  {
        checkContext();
downstreamGL.glTexCoord2fColor4fNormal3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11);
    checkGLGetError("glTexCoord2fColor4fNormal3fVertex3fSUN");
  }
  public  void glTexCoord2fColor4fNormal3fVertex3fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glTexCoord2fColor4fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexCoord2fColor4fNormal3fVertex3fvSUN");
  }
  public  void glTexCoord2fColor4fNormal3fVertex3fvSUN(float[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5,float[] arg6,int arg7)
  {
        checkContext();
downstreamGL.glTexCoord2fColor4fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glTexCoord2fColor4fNormal3fVertex3fvSUN");
  }
  public  void glTexCoord2fColor4ubVertex3fSUN(float arg0,float arg1,byte arg2,byte arg3,byte arg4,byte arg5,float arg6,float arg7,float arg8)
  {
        checkContext();
downstreamGL.glTexCoord2fColor4ubVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    checkGLGetError("glTexCoord2fColor4ubVertex3fSUN");
  }
  public  void glTexCoord2fColor4ubVertex3fvSUN(java.nio.FloatBuffer arg0,java.nio.ByteBuffer arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glTexCoord2fColor4ubVertex3fvSUN(arg0,arg1,arg2);
    checkGLGetError("glTexCoord2fColor4ubVertex3fvSUN");
  }
  public  void glTexCoord2fColor4ubVertex3fvSUN(float[] arg0,int arg1,byte[] arg2,int arg3,float[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glTexCoord2fColor4ubVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glTexCoord2fColor4ubVertex3fvSUN");
  }
  public  void glTexCoord2fNormal3fVertex3fSUN(float arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7)
  {
        checkContext();
downstreamGL.glTexCoord2fNormal3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glTexCoord2fNormal3fVertex3fSUN");
  }
  public  void glTexCoord2fNormal3fVertex3fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glTexCoord2fNormal3fVertex3fvSUN(arg0,arg1,arg2);
    checkGLGetError("glTexCoord2fNormal3fVertex3fvSUN");
  }
  public  void glTexCoord2fNormal3fVertex3fvSUN(float[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5)
  {
        checkContext();
downstreamGL.glTexCoord2fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glTexCoord2fNormal3fVertex3fvSUN");
  }
  public  void glTexCoord2fVertex3fSUN(float arg0,float arg1,float arg2,float arg3,float arg4)
  {
        checkContext();
downstreamGL.glTexCoord2fVertex3fSUN(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glTexCoord2fVertex3fSUN");
  }
  public  void glTexCoord2fVertex3fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glTexCoord2fVertex3fvSUN(arg0,arg1);
    checkGLGetError("glTexCoord2fVertex3fvSUN");
  }
  public  void glTexCoord2fVertex3fvSUN(float[] arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glTexCoord2fVertex3fvSUN(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexCoord2fVertex3fvSUN");
  }
  public  void glTexCoord2fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord2fv(arg0);
    checkGLGetError("glTexCoord2fv");
  }
  public  void glTexCoord2fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord2fv(arg0,arg1);
    checkGLGetError("glTexCoord2fv");
  }
  public  void glTexCoord2hNV(short arg0,short arg1)
  {
        checkContext();
downstreamGL.glTexCoord2hNV(arg0,arg1);
    checkGLGetError("glTexCoord2hNV");
  }
  public  void glTexCoord2hvNV(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord2hvNV(arg0);
    checkGLGetError("glTexCoord2hvNV");
  }
  public  void glTexCoord2hvNV(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord2hvNV(arg0,arg1);
    checkGLGetError("glTexCoord2hvNV");
  }
  public  void glTexCoord2i(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord2i(arg0,arg1);
    checkGLGetError("glTexCoord2i");
  }
  public  void glTexCoord2iv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord2iv(arg0);
    checkGLGetError("glTexCoord2iv");
  }
  public  void glTexCoord2iv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord2iv(arg0,arg1);
    checkGLGetError("glTexCoord2iv");
  }
  public  void glTexCoord2s(short arg0,short arg1)
  {
        checkContext();
downstreamGL.glTexCoord2s(arg0,arg1);
    checkGLGetError("glTexCoord2s");
  }
  public  void glTexCoord2sv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord2sv(arg0);
    checkGLGetError("glTexCoord2sv");
  }
  public  void glTexCoord2sv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord2sv(arg0,arg1);
    checkGLGetError("glTexCoord2sv");
  }
  public  void glTexCoord3d(double arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glTexCoord3d(arg0,arg1,arg2);
    checkGLGetError("glTexCoord3d");
  }
  public  void glTexCoord3dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord3dv(arg0);
    checkGLGetError("glTexCoord3dv");
  }
  public  void glTexCoord3dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord3dv(arg0,arg1);
    checkGLGetError("glTexCoord3dv");
  }
  public  void glTexCoord3f(float arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glTexCoord3f(arg0,arg1,arg2);
    checkGLGetError("glTexCoord3f");
  }
  public  void glTexCoord3fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord3fv(arg0);
    checkGLGetError("glTexCoord3fv");
  }
  public  void glTexCoord3fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord3fv(arg0,arg1);
    checkGLGetError("glTexCoord3fv");
  }
  public  void glTexCoord3hNV(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glTexCoord3hNV(arg0,arg1,arg2);
    checkGLGetError("glTexCoord3hNV");
  }
  public  void glTexCoord3hvNV(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord3hvNV(arg0);
    checkGLGetError("glTexCoord3hvNV");
  }
  public  void glTexCoord3hvNV(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord3hvNV(arg0,arg1);
    checkGLGetError("glTexCoord3hvNV");
  }
  public  void glTexCoord3i(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glTexCoord3i(arg0,arg1,arg2);
    checkGLGetError("glTexCoord3i");
  }
  public  void glTexCoord3iv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord3iv(arg0);
    checkGLGetError("glTexCoord3iv");
  }
  public  void glTexCoord3iv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord3iv(arg0,arg1);
    checkGLGetError("glTexCoord3iv");
  }
  public  void glTexCoord3s(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glTexCoord3s(arg0,arg1,arg2);
    checkGLGetError("glTexCoord3s");
  }
  public  void glTexCoord3sv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord3sv(arg0);
    checkGLGetError("glTexCoord3sv");
  }
  public  void glTexCoord3sv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord3sv(arg0,arg1);
    checkGLGetError("glTexCoord3sv");
  }
  public  void glTexCoord4d(double arg0,double arg1,double arg2,double arg3)
  {
        checkContext();
downstreamGL.glTexCoord4d(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexCoord4d");
  }
  public  void glTexCoord4dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord4dv(arg0);
    checkGLGetError("glTexCoord4dv");
  }
  public  void glTexCoord4dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord4dv(arg0,arg1);
    checkGLGetError("glTexCoord4dv");
  }
  public  void glTexCoord4f(float arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glTexCoord4f(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexCoord4f");
  }
  public  void glTexCoord4fColor4fNormal3fVertex4fSUN(float arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7,float arg8,float arg9,float arg10,float arg11,float arg12,float arg13,float arg14)
  {
        checkContext();
downstreamGL.glTexCoord4fColor4fNormal3fVertex4fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14);
    checkGLGetError("glTexCoord4fColor4fNormal3fVertex4fSUN");
  }
  public  void glTexCoord4fColor4fNormal3fVertex4fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glTexCoord4fColor4fNormal3fVertex4fvSUN(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexCoord4fColor4fNormal3fVertex4fvSUN");
  }
  public  void glTexCoord4fColor4fNormal3fVertex4fvSUN(float[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5,float[] arg6,int arg7)
  {
        checkContext();
downstreamGL.glTexCoord4fColor4fNormal3fVertex4fvSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glTexCoord4fColor4fNormal3fVertex4fvSUN");
  }
  public  void glTexCoord4fVertex4fSUN(float arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7)
  {
        checkContext();
downstreamGL.glTexCoord4fVertex4fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glTexCoord4fVertex4fSUN");
  }
  public  void glTexCoord4fVertex4fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glTexCoord4fVertex4fvSUN(arg0,arg1);
    checkGLGetError("glTexCoord4fVertex4fvSUN");
  }
  public  void glTexCoord4fVertex4fvSUN(float[] arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glTexCoord4fVertex4fvSUN(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexCoord4fVertex4fvSUN");
  }
  public  void glTexCoord4fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord4fv(arg0);
    checkGLGetError("glTexCoord4fv");
  }
  public  void glTexCoord4fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord4fv(arg0,arg1);
    checkGLGetError("glTexCoord4fv");
  }
  public  void glTexCoord4hNV(short arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glTexCoord4hNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexCoord4hNV");
  }
  public  void glTexCoord4hvNV(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord4hvNV(arg0);
    checkGLGetError("glTexCoord4hvNV");
  }
  public  void glTexCoord4hvNV(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord4hvNV(arg0,arg1);
    checkGLGetError("glTexCoord4hvNV");
  }
  public  void glTexCoord4i(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glTexCoord4i(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexCoord4i");
  }
  public  void glTexCoord4iv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord4iv(arg0);
    checkGLGetError("glTexCoord4iv");
  }
  public  void glTexCoord4iv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord4iv(arg0,arg1);
    checkGLGetError("glTexCoord4iv");
  }
  public  void glTexCoord4s(short arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glTexCoord4s(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexCoord4s");
  }
  public  void glTexCoord4sv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glTexCoord4sv(arg0);
    checkGLGetError("glTexCoord4sv");
  }
  public  void glTexCoord4sv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glTexCoord4sv(arg0,arg1);
    checkGLGetError("glTexCoord4sv");
  }
  public  void glTexCoordPointer(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
        checkContext();
downstreamGL.glTexCoordPointer(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexCoordPointer");
  }
  public  void glTexCoordPointer(int arg0,int arg1,int arg2,long arg3)
  {
        checkContext();
downstreamGL.glTexCoordPointer(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexCoordPointer");
  }
  public  void glTexEnvf(int arg0,int arg1,float arg2)
  {
        checkContext();
downstreamGL.glTexEnvf(arg0,arg1,arg2);
    checkGLGetError("glTexEnvf");
  }
  public  void glTexEnvfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glTexEnvfv(arg0,arg1,arg2);
    checkGLGetError("glTexEnvfv");
  }
  public  void glTexEnvfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glTexEnvfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexEnvfv");
  }
  public  void glTexEnvi(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glTexEnvi(arg0,arg1,arg2);
    checkGLGetError("glTexEnvi");
  }
  public  void glTexEnviv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glTexEnviv(arg0,arg1,arg2);
    checkGLGetError("glTexEnviv");
  }
  public  void glTexEnviv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glTexEnviv(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexEnviv");
  }
  public  void glTexFilterFuncSGIS(int arg0,int arg1,int arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glTexFilterFuncSGIS(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexFilterFuncSGIS");
  }
  public  void glTexFilterFuncSGIS(int arg0,int arg1,int arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glTexFilterFuncSGIS(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glTexFilterFuncSGIS");
  }
  public  void glTexGend(int arg0,int arg1,double arg2)
  {
        checkContext();
downstreamGL.glTexGend(arg0,arg1,arg2);
    checkGLGetError("glTexGend");
  }
  public  void glTexGendv(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
        checkContext();
downstreamGL.glTexGendv(arg0,arg1,arg2);
    checkGLGetError("glTexGendv");
  }
  public  void glTexGendv(int arg0,int arg1,double[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glTexGendv(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexGendv");
  }
  public  void glTexGenf(int arg0,int arg1,float arg2)
  {
        checkContext();
downstreamGL.glTexGenf(arg0,arg1,arg2);
    checkGLGetError("glTexGenf");
  }
  public  void glTexGenfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glTexGenfv(arg0,arg1,arg2);
    checkGLGetError("glTexGenfv");
  }
  public  void glTexGenfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glTexGenfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexGenfv");
  }
  public  void glTexGeni(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glTexGeni(arg0,arg1,arg2);
    checkGLGetError("glTexGeni");
  }
  public  void glTexGeniv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glTexGeniv(arg0,arg1,arg2);
    checkGLGetError("glTexGeniv");
  }
  public  void glTexGeniv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glTexGeniv(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexGeniv");
  }
  public  void glTexImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,java.nio.Buffer arg7)
  {
        checkContext();
downstreamGL.glTexImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glTexImage1D");
  }
  public  void glTexImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,long arg7)
  {
        checkContext();
downstreamGL.glTexImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    checkGLGetError("glTexImage1D");
  }
  public  void glTexImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,java.nio.Buffer arg8)
  {
        checkContext();
downstreamGL.glTexImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    checkGLGetError("glTexImage2D");
  }
  public  void glTexImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,long arg8)
  {
        checkContext();
downstreamGL.glTexImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    checkGLGetError("glTexImage2D");
  }
  public  void glTexImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,java.nio.Buffer arg9)
  {
        checkContext();
downstreamGL.glTexImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    checkGLGetError("glTexImage3D");
  }
  public  void glTexImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,long arg9)
  {
        checkContext();
downstreamGL.glTexImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    checkGLGetError("glTexImage3D");
  }
  public  void glTexImage4DSGIS(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,java.nio.Buffer arg10)
  {
        checkContext();
downstreamGL.glTexImage4DSGIS(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    checkGLGetError("glTexImage4DSGIS");
  }
  public  void glTexParameterIivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glTexParameterIivEXT(arg0,arg1,arg2);
    checkGLGetError("glTexParameterIivEXT");
  }
  public  void glTexParameterIivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glTexParameterIivEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexParameterIivEXT");
  }
  public  void glTexParameterIuivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glTexParameterIuivEXT(arg0,arg1,arg2);
    checkGLGetError("glTexParameterIuivEXT");
  }
  public  void glTexParameterIuivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glTexParameterIuivEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexParameterIuivEXT");
  }
  public  void glTexParameterf(int arg0,int arg1,float arg2)
  {
        checkContext();
downstreamGL.glTexParameterf(arg0,arg1,arg2);
    checkGLGetError("glTexParameterf");
  }
  public  void glTexParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glTexParameterfv(arg0,arg1,arg2);
    checkGLGetError("glTexParameterfv");
  }
  public  void glTexParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glTexParameterfv(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexParameterfv");
  }
  public  void glTexParameteri(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glTexParameteri(arg0,arg1,arg2);
    checkGLGetError("glTexParameteri");
  }
  public  void glTexParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glTexParameteriv(arg0,arg1,arg2);
    checkGLGetError("glTexParameteriv");
  }
  public  void glTexParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glTexParameteriv(arg0,arg1,arg2,arg3);
    checkGLGetError("glTexParameteriv");
  }
  public  void glTexSubImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,java.nio.Buffer arg6)
  {
        checkContext();
downstreamGL.glTexSubImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glTexSubImage1D");
  }
  public  void glTexSubImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,long arg6)
  {
        checkContext();
downstreamGL.glTexSubImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glTexSubImage1D");
  }
  public  void glTexSubImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,java.nio.Buffer arg8)
  {
        checkContext();
downstreamGL.glTexSubImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    checkGLGetError("glTexSubImage2D");
  }
  public  void glTexSubImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,long arg8)
  {
        checkContext();
downstreamGL.glTexSubImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    checkGLGetError("glTexSubImage2D");
  }
  public  void glTexSubImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,java.nio.Buffer arg10)
  {
        checkContext();
downstreamGL.glTexSubImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    checkGLGetError("glTexSubImage3D");
  }
  public  void glTexSubImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,long arg10)
  {
        checkContext();
downstreamGL.glTexSubImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    checkGLGetError("glTexSubImage3D");
  }
  public  void glTexSubImage4DSGIS(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,int arg10,int arg11,java.nio.Buffer arg12)
  {
        checkContext();
downstreamGL.glTexSubImage4DSGIS(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12);
    checkGLGetError("glTexSubImage4DSGIS");
  }
  public  void glTextureColorMaskSGIS(boolean arg0,boolean arg1,boolean arg2,boolean arg3)
  {
        checkContext();
downstreamGL.glTextureColorMaskSGIS(arg0,arg1,arg2,arg3);
    checkGLGetError("glTextureColorMaskSGIS");
  }
  public  void glTextureLightEXT(int arg0)
  {
        checkContext();
downstreamGL.glTextureLightEXT(arg0);
    checkGLGetError("glTextureLightEXT");
  }
  public  void glTextureMaterialEXT(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glTextureMaterialEXT(arg0,arg1);
    checkGLGetError("glTextureMaterialEXT");
  }
  public  void glTextureNormalEXT(int arg0)
  {
        checkContext();
downstreamGL.glTextureNormalEXT(arg0);
    checkGLGetError("glTextureNormalEXT");
  }
  public  void glTextureRangeAPPLE(int arg0,int arg1,java.nio.Buffer arg2)
  {
        checkContext();
downstreamGL.glTextureRangeAPPLE(arg0,arg1,arg2);
    checkGLGetError("glTextureRangeAPPLE");
  }
  public  void glTrackMatrixNV(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glTrackMatrixNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glTrackMatrixNV");
  }
  public  void glTransformFeedbackAttribsNV(int arg0,java.nio.IntBuffer arg1,int arg2)
  {
        checkContext();
downstreamGL.glTransformFeedbackAttribsNV(arg0,arg1,arg2);
    checkGLGetError("glTransformFeedbackAttribsNV");
  }
  public  void glTransformFeedbackAttribsNV(int arg0,int[] arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glTransformFeedbackAttribsNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glTransformFeedbackAttribsNV");
  }
  public  void glTransformFeedbackVaryingsNV(int arg0,int arg1,java.nio.IntBuffer arg2,int arg3)
  {
        checkContext();
downstreamGL.glTransformFeedbackVaryingsNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glTransformFeedbackVaryingsNV");
  }
  public  void glTransformFeedbackVaryingsNV(int arg0,int arg1,int[] arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glTransformFeedbackVaryingsNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glTransformFeedbackVaryingsNV");
  }
  public  void glTranslated(double arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glTranslated(arg0,arg1,arg2);
    checkGLGetError("glTranslated");
  }
  public  void glTranslatef(float arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glTranslatef(arg0,arg1,arg2);
    checkGLGetError("glTranslatef");
  }
  public  void glUniform1f(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glUniform1f(arg0,arg1);
    checkGLGetError("glUniform1f");
  }
  public  void glUniform1fARB(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glUniform1fARB(arg0,arg1);
    checkGLGetError("glUniform1fARB");
  }
  public  void glUniform1fv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform1fv(arg0,arg1,arg2);
    checkGLGetError("glUniform1fv");
  }
  public  void glUniform1fv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform1fv(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform1fv");
  }
  public  void glUniform1fvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform1fvARB(arg0,arg1,arg2);
    checkGLGetError("glUniform1fvARB");
  }
  public  void glUniform1fvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform1fvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform1fvARB");
  }
  public  void glUniform1i(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glUniform1i(arg0,arg1);
    checkGLGetError("glUniform1i");
  }
  public  void glUniform1iARB(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glUniform1iARB(arg0,arg1);
    checkGLGetError("glUniform1iARB");
  }
  public  void glUniform1iv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform1iv(arg0,arg1,arg2);
    checkGLGetError("glUniform1iv");
  }
  public  void glUniform1iv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform1iv(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform1iv");
  }
  public  void glUniform1ivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform1ivARB(arg0,arg1,arg2);
    checkGLGetError("glUniform1ivARB");
  }
  public  void glUniform1ivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform1ivARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform1ivARB");
  }
  public  void glUniform1uiEXT(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glUniform1uiEXT(arg0,arg1);
    checkGLGetError("glUniform1uiEXT");
  }
  public  void glUniform1uivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform1uivEXT(arg0,arg1,arg2);
    checkGLGetError("glUniform1uivEXT");
  }
  public  void glUniform1uivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform1uivEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform1uivEXT");
  }
  public  void glUniform2f(int arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glUniform2f(arg0,arg1,arg2);
    checkGLGetError("glUniform2f");
  }
  public  void glUniform2fARB(int arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glUniform2fARB(arg0,arg1,arg2);
    checkGLGetError("glUniform2fARB");
  }
  public  void glUniform2fv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform2fv(arg0,arg1,arg2);
    checkGLGetError("glUniform2fv");
  }
  public  void glUniform2fv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform2fv(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform2fv");
  }
  public  void glUniform2fvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform2fvARB(arg0,arg1,arg2);
    checkGLGetError("glUniform2fvARB");
  }
  public  void glUniform2fvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform2fvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform2fvARB");
  }
  public  void glUniform2i(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glUniform2i(arg0,arg1,arg2);
    checkGLGetError("glUniform2i");
  }
  public  void glUniform2iARB(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glUniform2iARB(arg0,arg1,arg2);
    checkGLGetError("glUniform2iARB");
  }
  public  void glUniform2iv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform2iv(arg0,arg1,arg2);
    checkGLGetError("glUniform2iv");
  }
  public  void glUniform2iv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform2iv(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform2iv");
  }
  public  void glUniform2ivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform2ivARB(arg0,arg1,arg2);
    checkGLGetError("glUniform2ivARB");
  }
  public  void glUniform2ivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform2ivARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform2ivARB");
  }
  public  void glUniform2uiEXT(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glUniform2uiEXT(arg0,arg1,arg2);
    checkGLGetError("glUniform2uiEXT");
  }
  public  void glUniform2uivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform2uivEXT(arg0,arg1,arg2);
    checkGLGetError("glUniform2uivEXT");
  }
  public  void glUniform2uivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform2uivEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform2uivEXT");
  }
  public  void glUniform3f(int arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glUniform3f(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform3f");
  }
  public  void glUniform3fARB(int arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glUniform3fARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform3fARB");
  }
  public  void glUniform3fv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform3fv(arg0,arg1,arg2);
    checkGLGetError("glUniform3fv");
  }
  public  void glUniform3fv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform3fv(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform3fv");
  }
  public  void glUniform3fvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform3fvARB(arg0,arg1,arg2);
    checkGLGetError("glUniform3fvARB");
  }
  public  void glUniform3fvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform3fvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform3fvARB");
  }
  public  void glUniform3i(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform3i(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform3i");
  }
  public  void glUniform3iARB(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform3iARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform3iARB");
  }
  public  void glUniform3iv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform3iv(arg0,arg1,arg2);
    checkGLGetError("glUniform3iv");
  }
  public  void glUniform3iv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform3iv(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform3iv");
  }
  public  void glUniform3ivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform3ivARB(arg0,arg1,arg2);
    checkGLGetError("glUniform3ivARB");
  }
  public  void glUniform3ivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform3ivARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform3ivARB");
  }
  public  void glUniform3uiEXT(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform3uiEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform3uiEXT");
  }
  public  void glUniform3uivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform3uivEXT(arg0,arg1,arg2);
    checkGLGetError("glUniform3uivEXT");
  }
  public  void glUniform3uivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform3uivEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform3uivEXT");
  }
  public  void glUniform4f(int arg0,float arg1,float arg2,float arg3,float arg4)
  {
        checkContext();
downstreamGL.glUniform4f(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUniform4f");
  }
  public  void glUniform4fARB(int arg0,float arg1,float arg2,float arg3,float arg4)
  {
        checkContext();
downstreamGL.glUniform4fARB(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUniform4fARB");
  }
  public  void glUniform4fv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform4fv(arg0,arg1,arg2);
    checkGLGetError("glUniform4fv");
  }
  public  void glUniform4fv(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform4fv(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform4fv");
  }
  public  void glUniform4fvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform4fvARB(arg0,arg1,arg2);
    checkGLGetError("glUniform4fvARB");
  }
  public  void glUniform4fvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform4fvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform4fvARB");
  }
  public  void glUniform4i(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glUniform4i(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUniform4i");
  }
  public  void glUniform4iARB(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glUniform4iARB(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUniform4iARB");
  }
  public  void glUniform4iv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform4iv(arg0,arg1,arg2);
    checkGLGetError("glUniform4iv");
  }
  public  void glUniform4iv(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform4iv(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform4iv");
  }
  public  void glUniform4ivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform4ivARB(arg0,arg1,arg2);
    checkGLGetError("glUniform4ivARB");
  }
  public  void glUniform4ivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform4ivARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform4ivARB");
  }
  public  void glUniform4uiEXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glUniform4uiEXT(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUniform4uiEXT");
  }
  public  void glUniform4uivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
        checkContext();
downstreamGL.glUniform4uivEXT(arg0,arg1,arg2);
    checkGLGetError("glUniform4uivEXT");
  }
  public  void glUniform4uivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glUniform4uivEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniform4uivEXT");
  }
  public  void glUniformBufferEXT(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glUniformBufferEXT(arg0,arg1,arg2);
    checkGLGetError("glUniformBufferEXT");
  }
  public  void glUniformMatrix2fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glUniformMatrix2fv(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniformMatrix2fv");
  }
  public  void glUniformMatrix2fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glUniformMatrix2fv(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUniformMatrix2fv");
  }
  public  void glUniformMatrix2fvARB(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glUniformMatrix2fvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniformMatrix2fvARB");
  }
  public  void glUniformMatrix2fvARB(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glUniformMatrix2fvARB(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUniformMatrix2fvARB");
  }
  public  void glUniformMatrix2x3fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glUniformMatrix2x3fv(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniformMatrix2x3fv");
  }
  public  void glUniformMatrix2x3fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glUniformMatrix2x3fv(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUniformMatrix2x3fv");
  }
  public  void glUniformMatrix2x4fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glUniformMatrix2x4fv(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniformMatrix2x4fv");
  }
  public  void glUniformMatrix2x4fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glUniformMatrix2x4fv(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUniformMatrix2x4fv");
  }
  public  void glUniformMatrix3fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glUniformMatrix3fv(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniformMatrix3fv");
  }
  public  void glUniformMatrix3fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glUniformMatrix3fv(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUniformMatrix3fv");
  }
  public  void glUniformMatrix3fvARB(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glUniformMatrix3fvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniformMatrix3fvARB");
  }
  public  void glUniformMatrix3fvARB(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glUniformMatrix3fvARB(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUniformMatrix3fvARB");
  }
  public  void glUniformMatrix3x2fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glUniformMatrix3x2fv(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniformMatrix3x2fv");
  }
  public  void glUniformMatrix3x2fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glUniformMatrix3x2fv(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUniformMatrix3x2fv");
  }
  public  void glUniformMatrix3x4fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glUniformMatrix3x4fv(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniformMatrix3x4fv");
  }
  public  void glUniformMatrix3x4fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glUniformMatrix3x4fv(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUniformMatrix3x4fv");
  }
  public  void glUniformMatrix4fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glUniformMatrix4fv(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniformMatrix4fv");
  }
  public  void glUniformMatrix4fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glUniformMatrix4fv(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUniformMatrix4fv");
  }
  public  void glUniformMatrix4fvARB(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glUniformMatrix4fvARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniformMatrix4fvARB");
  }
  public  void glUniformMatrix4fvARB(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glUniformMatrix4fvARB(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUniformMatrix4fvARB");
  }
  public  void glUniformMatrix4x2fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glUniformMatrix4x2fv(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniformMatrix4x2fv");
  }
  public  void glUniformMatrix4x2fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glUniformMatrix4x2fv(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUniformMatrix4x2fv");
  }
  public  void glUniformMatrix4x3fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
        checkContext();
downstreamGL.glUniformMatrix4x3fv(arg0,arg1,arg2,arg3);
    checkGLGetError("glUniformMatrix4x3fv");
  }
  public  void glUniformMatrix4x3fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
        checkContext();
downstreamGL.glUniformMatrix4x3fv(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUniformMatrix4x3fv");
  }
  public  void glUnlockArraysEXT()
  {
        checkContext();
downstreamGL.glUnlockArraysEXT();
    checkGLGetError("glUnlockArraysEXT");
  }
  public  boolean glUnmapBuffer(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glUnmapBuffer(arg0);
    checkGLGetError("glUnmapBuffer");
    return _res;
  }
  public  boolean glUnmapBufferARB(int arg0)
  {
        checkContext();
boolean _res = downstreamGL.glUnmapBufferARB(arg0);
    checkGLGetError("glUnmapBufferARB");
    return _res;
  }
  public  void glUpdateObjectBufferATI(int arg0,int arg1,int arg2,java.nio.Buffer arg3,int arg4)
  {
        checkContext();
downstreamGL.glUpdateObjectBufferATI(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glUpdateObjectBufferATI");
  }
  public  void glUseProgram(int arg0)
  {
        checkContext();
downstreamGL.glUseProgram(arg0);
    checkGLGetError("glUseProgram");
  }
  public  void glUseProgramObjectARB(int arg0)
  {
        checkContext();
downstreamGL.glUseProgramObjectARB(arg0);
    checkGLGetError("glUseProgramObjectARB");
  }
  public  void glValidateProgram(int arg0)
  {
        checkContext();
downstreamGL.glValidateProgram(arg0);
    checkGLGetError("glValidateProgram");
  }
  public  void glValidateProgramARB(int arg0)
  {
        checkContext();
downstreamGL.glValidateProgramARB(arg0);
    checkGLGetError("glValidateProgramARB");
  }
  public  void glVariantArrayObjectATI(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glVariantArrayObjectATI(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVariantArrayObjectATI");
  }
  public  void glVariantPointerEXT(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
        checkContext();
downstreamGL.glVariantPointerEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glVariantPointerEXT");
  }
  public  void glVariantPointerEXT(int arg0,int arg1,int arg2,long arg3)
  {
        checkContext();
downstreamGL.glVariantPointerEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glVariantPointerEXT");
  }
  public  void glVariantbvEXT(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glVariantbvEXT(arg0,arg1);
    checkGLGetError("glVariantbvEXT");
  }
  public  void glVariantbvEXT(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVariantbvEXT(arg0,arg1,arg2);
    checkGLGetError("glVariantbvEXT");
  }
  public  void glVariantdvEXT(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glVariantdvEXT(arg0,arg1);
    checkGLGetError("glVariantdvEXT");
  }
  public  void glVariantdvEXT(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVariantdvEXT(arg0,arg1,arg2);
    checkGLGetError("glVariantdvEXT");
  }
  public  void glVariantfvEXT(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glVariantfvEXT(arg0,arg1);
    checkGLGetError("glVariantfvEXT");
  }
  public  void glVariantfvEXT(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVariantfvEXT(arg0,arg1,arg2);
    checkGLGetError("glVariantfvEXT");
  }
  public  void glVariantivEXT(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVariantivEXT(arg0,arg1);
    checkGLGetError("glVariantivEXT");
  }
  public  void glVariantivEXT(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVariantivEXT(arg0,arg1,arg2);
    checkGLGetError("glVariantivEXT");
  }
  public  void glVariantsvEXT(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVariantsvEXT(arg0,arg1);
    checkGLGetError("glVariantsvEXT");
  }
  public  void glVariantsvEXT(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVariantsvEXT(arg0,arg1,arg2);
    checkGLGetError("glVariantsvEXT");
  }
  public  void glVariantubvEXT(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glVariantubvEXT(arg0,arg1);
    checkGLGetError("glVariantubvEXT");
  }
  public  void glVariantubvEXT(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVariantubvEXT(arg0,arg1,arg2);
    checkGLGetError("glVariantubvEXT");
  }
  public  void glVariantuivEXT(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVariantuivEXT(arg0,arg1);
    checkGLGetError("glVariantuivEXT");
  }
  public  void glVariantuivEXT(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVariantuivEXT(arg0,arg1,arg2);
    checkGLGetError("glVariantuivEXT");
  }
  public  void glVariantusvEXT(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVariantusvEXT(arg0,arg1);
    checkGLGetError("glVariantusvEXT");
  }
  public  void glVariantusvEXT(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVariantusvEXT(arg0,arg1,arg2);
    checkGLGetError("glVariantusvEXT");
  }
  public  void glVertex2d(double arg0,double arg1)
  {
        checkContext();
downstreamGL.glVertex2d(arg0,arg1);
    checkGLGetError("glVertex2d");
  }
  public  void glVertex2dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glVertex2dv(arg0);
    checkGLGetError("glVertex2dv");
  }
  public  void glVertex2dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertex2dv(arg0,arg1);
    checkGLGetError("glVertex2dv");
  }
  public  void glVertex2f(float arg0,float arg1)
  {
        checkContext();
downstreamGL.glVertex2f(arg0,arg1);
    checkGLGetError("glVertex2f");
  }
  public  void glVertex2fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glVertex2fv(arg0);
    checkGLGetError("glVertex2fv");
  }
  public  void glVertex2fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertex2fv(arg0,arg1);
    checkGLGetError("glVertex2fv");
  }
  public  void glVertex2hNV(short arg0,short arg1)
  {
        checkContext();
downstreamGL.glVertex2hNV(arg0,arg1);
    checkGLGetError("glVertex2hNV");
  }
  public  void glVertex2hvNV(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glVertex2hvNV(arg0);
    checkGLGetError("glVertex2hvNV");
  }
  public  void glVertex2hvNV(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertex2hvNV(arg0,arg1);
    checkGLGetError("glVertex2hvNV");
  }
  public  void glVertex2i(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertex2i(arg0,arg1);
    checkGLGetError("glVertex2i");
  }
  public  void glVertex2iv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glVertex2iv(arg0);
    checkGLGetError("glVertex2iv");
  }
  public  void glVertex2iv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertex2iv(arg0,arg1);
    checkGLGetError("glVertex2iv");
  }
  public  void glVertex2s(short arg0,short arg1)
  {
        checkContext();
downstreamGL.glVertex2s(arg0,arg1);
    checkGLGetError("glVertex2s");
  }
  public  void glVertex2sv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glVertex2sv(arg0);
    checkGLGetError("glVertex2sv");
  }
  public  void glVertex2sv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertex2sv(arg0,arg1);
    checkGLGetError("glVertex2sv");
  }
  public  void glVertex3d(double arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glVertex3d(arg0,arg1,arg2);
    checkGLGetError("glVertex3d");
  }
  public  void glVertex3dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glVertex3dv(arg0);
    checkGLGetError("glVertex3dv");
  }
  public  void glVertex3dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertex3dv(arg0,arg1);
    checkGLGetError("glVertex3dv");
  }
  public  void glVertex3f(float arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glVertex3f(arg0,arg1,arg2);
    checkGLGetError("glVertex3f");
  }
  public  void glVertex3fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glVertex3fv(arg0);
    checkGLGetError("glVertex3fv");
  }
  public  void glVertex3fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertex3fv(arg0,arg1);
    checkGLGetError("glVertex3fv");
  }
  public  void glVertex3hNV(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glVertex3hNV(arg0,arg1,arg2);
    checkGLGetError("glVertex3hNV");
  }
  public  void glVertex3hvNV(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glVertex3hvNV(arg0);
    checkGLGetError("glVertex3hvNV");
  }
  public  void glVertex3hvNV(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertex3hvNV(arg0,arg1);
    checkGLGetError("glVertex3hvNV");
  }
  public  void glVertex3i(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertex3i(arg0,arg1,arg2);
    checkGLGetError("glVertex3i");
  }
  public  void glVertex3iv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glVertex3iv(arg0);
    checkGLGetError("glVertex3iv");
  }
  public  void glVertex3iv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertex3iv(arg0,arg1);
    checkGLGetError("glVertex3iv");
  }
  public  void glVertex3s(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glVertex3s(arg0,arg1,arg2);
    checkGLGetError("glVertex3s");
  }
  public  void glVertex3sv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glVertex3sv(arg0);
    checkGLGetError("glVertex3sv");
  }
  public  void glVertex3sv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertex3sv(arg0,arg1);
    checkGLGetError("glVertex3sv");
  }
  public  void glVertex4d(double arg0,double arg1,double arg2,double arg3)
  {
        checkContext();
downstreamGL.glVertex4d(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertex4d");
  }
  public  void glVertex4dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glVertex4dv(arg0);
    checkGLGetError("glVertex4dv");
  }
  public  void glVertex4dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertex4dv(arg0,arg1);
    checkGLGetError("glVertex4dv");
  }
  public  void glVertex4f(float arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glVertex4f(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertex4f");
  }
  public  void glVertex4fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glVertex4fv(arg0);
    checkGLGetError("glVertex4fv");
  }
  public  void glVertex4fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertex4fv(arg0,arg1);
    checkGLGetError("glVertex4fv");
  }
  public  void glVertex4hNV(short arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glVertex4hNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertex4hNV");
  }
  public  void glVertex4hvNV(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glVertex4hvNV(arg0);
    checkGLGetError("glVertex4hvNV");
  }
  public  void glVertex4hvNV(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertex4hvNV(arg0,arg1);
    checkGLGetError("glVertex4hvNV");
  }
  public  void glVertex4i(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertex4i(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertex4i");
  }
  public  void glVertex4iv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glVertex4iv(arg0);
    checkGLGetError("glVertex4iv");
  }
  public  void glVertex4iv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertex4iv(arg0,arg1);
    checkGLGetError("glVertex4iv");
  }
  public  void glVertex4s(short arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glVertex4s(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertex4s");
  }
  public  void glVertex4sv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glVertex4sv(arg0);
    checkGLGetError("glVertex4sv");
  }
  public  void glVertex4sv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertex4sv(arg0,arg1);
    checkGLGetError("glVertex4sv");
  }
  public  void glVertexArrayParameteriAPPLE(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertexArrayParameteriAPPLE(arg0,arg1);
    checkGLGetError("glVertexArrayParameteriAPPLE");
  }
  public  void glVertexArrayRangeAPPLE(int arg0,java.nio.Buffer arg1)
  {
        checkContext();
downstreamGL.glVertexArrayRangeAPPLE(arg0,arg1);
    checkGLGetError("glVertexArrayRangeAPPLE");
  }
  public  void glVertexArrayRangeNV(int arg0,java.nio.Buffer arg1)
  {
        checkContext();
downstreamGL.glVertexArrayRangeNV(arg0,arg1);
    checkGLGetError("glVertexArrayRangeNV");
  }
  public  void glVertexAttrib1d(int arg0,double arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1d(arg0,arg1);
    checkGLGetError("glVertexAttrib1d");
  }
  public  void glVertexAttrib1dARB(int arg0,double arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1dARB(arg0,arg1);
    checkGLGetError("glVertexAttrib1dARB");
  }
  public  void glVertexAttrib1dNV(int arg0,double arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1dNV(arg0,arg1);
    checkGLGetError("glVertexAttrib1dNV");
  }
  public  void glVertexAttrib1dv(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1dv(arg0,arg1);
    checkGLGetError("glVertexAttrib1dv");
  }
  public  void glVertexAttrib1dv(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib1dv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib1dv");
  }
  public  void glVertexAttrib1dvARB(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1dvARB(arg0,arg1);
    checkGLGetError("glVertexAttrib1dvARB");
  }
  public  void glVertexAttrib1dvARB(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib1dvARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib1dvARB");
  }
  public  void glVertexAttrib1dvNV(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1dvNV(arg0,arg1);
    checkGLGetError("glVertexAttrib1dvNV");
  }
  public  void glVertexAttrib1dvNV(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib1dvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib1dvNV");
  }
  public  void glVertexAttrib1f(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1f(arg0,arg1);
    checkGLGetError("glVertexAttrib1f");
  }
  public  void glVertexAttrib1fARB(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1fARB(arg0,arg1);
    checkGLGetError("glVertexAttrib1fARB");
  }
  public  void glVertexAttrib1fNV(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1fNV(arg0,arg1);
    checkGLGetError("glVertexAttrib1fNV");
  }
  public  void glVertexAttrib1fv(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1fv(arg0,arg1);
    checkGLGetError("glVertexAttrib1fv");
  }
  public  void glVertexAttrib1fv(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib1fv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib1fv");
  }
  public  void glVertexAttrib1fvARB(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1fvARB(arg0,arg1);
    checkGLGetError("glVertexAttrib1fvARB");
  }
  public  void glVertexAttrib1fvARB(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib1fvARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib1fvARB");
  }
  public  void glVertexAttrib1fvNV(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1fvNV(arg0,arg1);
    checkGLGetError("glVertexAttrib1fvNV");
  }
  public  void glVertexAttrib1fvNV(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib1fvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib1fvNV");
  }
  public  void glVertexAttrib1hNV(int arg0,short arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1hNV(arg0,arg1);
    checkGLGetError("glVertexAttrib1hNV");
  }
  public  void glVertexAttrib1hvNV(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1hvNV(arg0,arg1);
    checkGLGetError("glVertexAttrib1hvNV");
  }
  public  void glVertexAttrib1hvNV(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib1hvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib1hvNV");
  }
  public  void glVertexAttrib1s(int arg0,short arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1s(arg0,arg1);
    checkGLGetError("glVertexAttrib1s");
  }
  public  void glVertexAttrib1sARB(int arg0,short arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1sARB(arg0,arg1);
    checkGLGetError("glVertexAttrib1sARB");
  }
  public  void glVertexAttrib1sNV(int arg0,short arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1sNV(arg0,arg1);
    checkGLGetError("glVertexAttrib1sNV");
  }
  public  void glVertexAttrib1sv(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1sv(arg0,arg1);
    checkGLGetError("glVertexAttrib1sv");
  }
  public  void glVertexAttrib1sv(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib1sv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib1sv");
  }
  public  void glVertexAttrib1svARB(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1svARB(arg0,arg1);
    checkGLGetError("glVertexAttrib1svARB");
  }
  public  void glVertexAttrib1svARB(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib1svARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib1svARB");
  }
  public  void glVertexAttrib1svNV(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib1svNV(arg0,arg1);
    checkGLGetError("glVertexAttrib1svNV");
  }
  public  void glVertexAttrib1svNV(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib1svNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib1svNV");
  }
  public  void glVertexAttrib2d(int arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2d(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2d");
  }
  public  void glVertexAttrib2dARB(int arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2dARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2dARB");
  }
  public  void glVertexAttrib2dNV(int arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2dNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2dNV");
  }
  public  void glVertexAttrib2dv(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib2dv(arg0,arg1);
    checkGLGetError("glVertexAttrib2dv");
  }
  public  void glVertexAttrib2dv(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2dv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2dv");
  }
  public  void glVertexAttrib2dvARB(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib2dvARB(arg0,arg1);
    checkGLGetError("glVertexAttrib2dvARB");
  }
  public  void glVertexAttrib2dvARB(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2dvARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2dvARB");
  }
  public  void glVertexAttrib2dvNV(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib2dvNV(arg0,arg1);
    checkGLGetError("glVertexAttrib2dvNV");
  }
  public  void glVertexAttrib2dvNV(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2dvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2dvNV");
  }
  public  void glVertexAttrib2f(int arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2f(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2f");
  }
  public  void glVertexAttrib2fARB(int arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2fARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2fARB");
  }
  public  void glVertexAttrib2fNV(int arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2fNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2fNV");
  }
  public  void glVertexAttrib2fv(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib2fv(arg0,arg1);
    checkGLGetError("glVertexAttrib2fv");
  }
  public  void glVertexAttrib2fv(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2fv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2fv");
  }
  public  void glVertexAttrib2fvARB(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib2fvARB(arg0,arg1);
    checkGLGetError("glVertexAttrib2fvARB");
  }
  public  void glVertexAttrib2fvARB(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2fvARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2fvARB");
  }
  public  void glVertexAttrib2fvNV(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib2fvNV(arg0,arg1);
    checkGLGetError("glVertexAttrib2fvNV");
  }
  public  void glVertexAttrib2fvNV(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2fvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2fvNV");
  }
  public  void glVertexAttrib2hNV(int arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2hNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2hNV");
  }
  public  void glVertexAttrib2hvNV(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib2hvNV(arg0,arg1);
    checkGLGetError("glVertexAttrib2hvNV");
  }
  public  void glVertexAttrib2hvNV(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2hvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2hvNV");
  }
  public  void glVertexAttrib2s(int arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2s(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2s");
  }
  public  void glVertexAttrib2sARB(int arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2sARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2sARB");
  }
  public  void glVertexAttrib2sNV(int arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2sNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2sNV");
  }
  public  void glVertexAttrib2sv(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib2sv(arg0,arg1);
    checkGLGetError("glVertexAttrib2sv");
  }
  public  void glVertexAttrib2sv(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2sv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2sv");
  }
  public  void glVertexAttrib2svARB(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib2svARB(arg0,arg1);
    checkGLGetError("glVertexAttrib2svARB");
  }
  public  void glVertexAttrib2svARB(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2svARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2svARB");
  }
  public  void glVertexAttrib2svNV(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib2svNV(arg0,arg1);
    checkGLGetError("glVertexAttrib2svNV");
  }
  public  void glVertexAttrib2svNV(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib2svNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib2svNV");
  }
  public  void glVertexAttrib3d(int arg0,double arg1,double arg2,double arg3)
  {
        checkContext();
downstreamGL.glVertexAttrib3d(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttrib3d");
  }
  public  void glVertexAttrib3dARB(int arg0,double arg1,double arg2,double arg3)
  {
        checkContext();
downstreamGL.glVertexAttrib3dARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttrib3dARB");
  }
  public  void glVertexAttrib3dNV(int arg0,double arg1,double arg2,double arg3)
  {
        checkContext();
downstreamGL.glVertexAttrib3dNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttrib3dNV");
  }
  public  void glVertexAttrib3dv(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib3dv(arg0,arg1);
    checkGLGetError("glVertexAttrib3dv");
  }
  public  void glVertexAttrib3dv(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib3dv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib3dv");
  }
  public  void glVertexAttrib3dvARB(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib3dvARB(arg0,arg1);
    checkGLGetError("glVertexAttrib3dvARB");
  }
  public  void glVertexAttrib3dvARB(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib3dvARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib3dvARB");
  }
  public  void glVertexAttrib3dvNV(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib3dvNV(arg0,arg1);
    checkGLGetError("glVertexAttrib3dvNV");
  }
  public  void glVertexAttrib3dvNV(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib3dvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib3dvNV");
  }
  public  void glVertexAttrib3f(int arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glVertexAttrib3f(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttrib3f");
  }
  public  void glVertexAttrib3fARB(int arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glVertexAttrib3fARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttrib3fARB");
  }
  public  void glVertexAttrib3fNV(int arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glVertexAttrib3fNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttrib3fNV");
  }
  public  void glVertexAttrib3fv(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib3fv(arg0,arg1);
    checkGLGetError("glVertexAttrib3fv");
  }
  public  void glVertexAttrib3fv(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib3fv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib3fv");
  }
  public  void glVertexAttrib3fvARB(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib3fvARB(arg0,arg1);
    checkGLGetError("glVertexAttrib3fvARB");
  }
  public  void glVertexAttrib3fvARB(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib3fvARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib3fvARB");
  }
  public  void glVertexAttrib3fvNV(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib3fvNV(arg0,arg1);
    checkGLGetError("glVertexAttrib3fvNV");
  }
  public  void glVertexAttrib3fvNV(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib3fvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib3fvNV");
  }
  public  void glVertexAttrib3hNV(int arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glVertexAttrib3hNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttrib3hNV");
  }
  public  void glVertexAttrib3hvNV(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib3hvNV(arg0,arg1);
    checkGLGetError("glVertexAttrib3hvNV");
  }
  public  void glVertexAttrib3hvNV(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib3hvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib3hvNV");
  }
  public  void glVertexAttrib3s(int arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glVertexAttrib3s(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttrib3s");
  }
  public  void glVertexAttrib3sARB(int arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glVertexAttrib3sARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttrib3sARB");
  }
  public  void glVertexAttrib3sNV(int arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glVertexAttrib3sNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttrib3sNV");
  }
  public  void glVertexAttrib3sv(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib3sv(arg0,arg1);
    checkGLGetError("glVertexAttrib3sv");
  }
  public  void glVertexAttrib3sv(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib3sv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib3sv");
  }
  public  void glVertexAttrib3svARB(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib3svARB(arg0,arg1);
    checkGLGetError("glVertexAttrib3svARB");
  }
  public  void glVertexAttrib3svARB(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib3svARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib3svARB");
  }
  public  void glVertexAttrib3svNV(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib3svNV(arg0,arg1);
    checkGLGetError("glVertexAttrib3svNV");
  }
  public  void glVertexAttrib3svNV(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib3svNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib3svNV");
  }
  public  void glVertexAttrib4Nbv(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4Nbv(arg0,arg1);
    checkGLGetError("glVertexAttrib4Nbv");
  }
  public  void glVertexAttrib4Nbv(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4Nbv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4Nbv");
  }
  public  void glVertexAttrib4NbvARB(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4NbvARB(arg0,arg1);
    checkGLGetError("glVertexAttrib4NbvARB");
  }
  public  void glVertexAttrib4NbvARB(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4NbvARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4NbvARB");
  }
  public  void glVertexAttrib4Niv(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4Niv(arg0,arg1);
    checkGLGetError("glVertexAttrib4Niv");
  }
  public  void glVertexAttrib4Niv(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4Niv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4Niv");
  }
  public  void glVertexAttrib4NivARB(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4NivARB(arg0,arg1);
    checkGLGetError("glVertexAttrib4NivARB");
  }
  public  void glVertexAttrib4NivARB(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4NivARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4NivARB");
  }
  public  void glVertexAttrib4Nsv(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4Nsv(arg0,arg1);
    checkGLGetError("glVertexAttrib4Nsv");
  }
  public  void glVertexAttrib4Nsv(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4Nsv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4Nsv");
  }
  public  void glVertexAttrib4NsvARB(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4NsvARB(arg0,arg1);
    checkGLGetError("glVertexAttrib4NsvARB");
  }
  public  void glVertexAttrib4NsvARB(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4NsvARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4NsvARB");
  }
  public  void glVertexAttrib4Nub(int arg0,byte arg1,byte arg2,byte arg3,byte arg4)
  {
        checkContext();
downstreamGL.glVertexAttrib4Nub(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttrib4Nub");
  }
  public  void glVertexAttrib4NubARB(int arg0,byte arg1,byte arg2,byte arg3,byte arg4)
  {
        checkContext();
downstreamGL.glVertexAttrib4NubARB(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttrib4NubARB");
  }
  public  void glVertexAttrib4Nubv(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4Nubv(arg0,arg1);
    checkGLGetError("glVertexAttrib4Nubv");
  }
  public  void glVertexAttrib4Nubv(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4Nubv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4Nubv");
  }
  public  void glVertexAttrib4NubvARB(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4NubvARB(arg0,arg1);
    checkGLGetError("glVertexAttrib4NubvARB");
  }
  public  void glVertexAttrib4NubvARB(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4NubvARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4NubvARB");
  }
  public  void glVertexAttrib4Nuiv(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4Nuiv(arg0,arg1);
    checkGLGetError("glVertexAttrib4Nuiv");
  }
  public  void glVertexAttrib4Nuiv(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4Nuiv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4Nuiv");
  }
  public  void glVertexAttrib4NuivARB(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4NuivARB(arg0,arg1);
    checkGLGetError("glVertexAttrib4NuivARB");
  }
  public  void glVertexAttrib4NuivARB(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4NuivARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4NuivARB");
  }
  public  void glVertexAttrib4Nusv(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4Nusv(arg0,arg1);
    checkGLGetError("glVertexAttrib4Nusv");
  }
  public  void glVertexAttrib4Nusv(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4Nusv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4Nusv");
  }
  public  void glVertexAttrib4NusvARB(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4NusvARB(arg0,arg1);
    checkGLGetError("glVertexAttrib4NusvARB");
  }
  public  void glVertexAttrib4NusvARB(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4NusvARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4NusvARB");
  }
  public  void glVertexAttrib4bv(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4bv(arg0,arg1);
    checkGLGetError("glVertexAttrib4bv");
  }
  public  void glVertexAttrib4bv(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4bv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4bv");
  }
  public  void glVertexAttrib4bvARB(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4bvARB(arg0,arg1);
    checkGLGetError("glVertexAttrib4bvARB");
  }
  public  void glVertexAttrib4bvARB(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4bvARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4bvARB");
  }
  public  void glVertexAttrib4d(int arg0,double arg1,double arg2,double arg3,double arg4)
  {
        checkContext();
downstreamGL.glVertexAttrib4d(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttrib4d");
  }
  public  void glVertexAttrib4dARB(int arg0,double arg1,double arg2,double arg3,double arg4)
  {
        checkContext();
downstreamGL.glVertexAttrib4dARB(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttrib4dARB");
  }
  public  void glVertexAttrib4dNV(int arg0,double arg1,double arg2,double arg3,double arg4)
  {
        checkContext();
downstreamGL.glVertexAttrib4dNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttrib4dNV");
  }
  public  void glVertexAttrib4dv(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4dv(arg0,arg1);
    checkGLGetError("glVertexAttrib4dv");
  }
  public  void glVertexAttrib4dv(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4dv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4dv");
  }
  public  void glVertexAttrib4dvARB(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4dvARB(arg0,arg1);
    checkGLGetError("glVertexAttrib4dvARB");
  }
  public  void glVertexAttrib4dvARB(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4dvARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4dvARB");
  }
  public  void glVertexAttrib4dvNV(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4dvNV(arg0,arg1);
    checkGLGetError("glVertexAttrib4dvNV");
  }
  public  void glVertexAttrib4dvNV(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4dvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4dvNV");
  }
  public  void glVertexAttrib4f(int arg0,float arg1,float arg2,float arg3,float arg4)
  {
        checkContext();
downstreamGL.glVertexAttrib4f(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttrib4f");
  }
  public  void glVertexAttrib4fARB(int arg0,float arg1,float arg2,float arg3,float arg4)
  {
        checkContext();
downstreamGL.glVertexAttrib4fARB(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttrib4fARB");
  }
  public  void glVertexAttrib4fNV(int arg0,float arg1,float arg2,float arg3,float arg4)
  {
        checkContext();
downstreamGL.glVertexAttrib4fNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttrib4fNV");
  }
  public  void glVertexAttrib4fv(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4fv(arg0,arg1);
    checkGLGetError("glVertexAttrib4fv");
  }
  public  void glVertexAttrib4fv(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4fv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4fv");
  }
  public  void glVertexAttrib4fvARB(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4fvARB(arg0,arg1);
    checkGLGetError("glVertexAttrib4fvARB");
  }
  public  void glVertexAttrib4fvARB(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4fvARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4fvARB");
  }
  public  void glVertexAttrib4fvNV(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4fvNV(arg0,arg1);
    checkGLGetError("glVertexAttrib4fvNV");
  }
  public  void glVertexAttrib4fvNV(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4fvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4fvNV");
  }
  public  void glVertexAttrib4hNV(int arg0,short arg1,short arg2,short arg3,short arg4)
  {
        checkContext();
downstreamGL.glVertexAttrib4hNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttrib4hNV");
  }
  public  void glVertexAttrib4hvNV(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4hvNV(arg0,arg1);
    checkGLGetError("glVertexAttrib4hvNV");
  }
  public  void glVertexAttrib4hvNV(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4hvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4hvNV");
  }
  public  void glVertexAttrib4iv(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4iv(arg0,arg1);
    checkGLGetError("glVertexAttrib4iv");
  }
  public  void glVertexAttrib4iv(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4iv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4iv");
  }
  public  void glVertexAttrib4ivARB(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4ivARB(arg0,arg1);
    checkGLGetError("glVertexAttrib4ivARB");
  }
  public  void glVertexAttrib4ivARB(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4ivARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4ivARB");
  }
  public  void glVertexAttrib4s(int arg0,short arg1,short arg2,short arg3,short arg4)
  {
        checkContext();
downstreamGL.glVertexAttrib4s(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttrib4s");
  }
  public  void glVertexAttrib4sARB(int arg0,short arg1,short arg2,short arg3,short arg4)
  {
        checkContext();
downstreamGL.glVertexAttrib4sARB(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttrib4sARB");
  }
  public  void glVertexAttrib4sNV(int arg0,short arg1,short arg2,short arg3,short arg4)
  {
        checkContext();
downstreamGL.glVertexAttrib4sNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttrib4sNV");
  }
  public  void glVertexAttrib4sv(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4sv(arg0,arg1);
    checkGLGetError("glVertexAttrib4sv");
  }
  public  void glVertexAttrib4sv(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4sv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4sv");
  }
  public  void glVertexAttrib4svARB(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4svARB(arg0,arg1);
    checkGLGetError("glVertexAttrib4svARB");
  }
  public  void glVertexAttrib4svARB(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4svARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4svARB");
  }
  public  void glVertexAttrib4svNV(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4svNV(arg0,arg1);
    checkGLGetError("glVertexAttrib4svNV");
  }
  public  void glVertexAttrib4svNV(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4svNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4svNV");
  }
  public  void glVertexAttrib4ubNV(int arg0,byte arg1,byte arg2,byte arg3,byte arg4)
  {
        checkContext();
downstreamGL.glVertexAttrib4ubNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttrib4ubNV");
  }
  public  void glVertexAttrib4ubv(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4ubv(arg0,arg1);
    checkGLGetError("glVertexAttrib4ubv");
  }
  public  void glVertexAttrib4ubv(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4ubv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4ubv");
  }
  public  void glVertexAttrib4ubvARB(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4ubvARB(arg0,arg1);
    checkGLGetError("glVertexAttrib4ubvARB");
  }
  public  void glVertexAttrib4ubvARB(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4ubvARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4ubvARB");
  }
  public  void glVertexAttrib4ubvNV(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4ubvNV(arg0,arg1);
    checkGLGetError("glVertexAttrib4ubvNV");
  }
  public  void glVertexAttrib4ubvNV(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4ubvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4ubvNV");
  }
  public  void glVertexAttrib4uiv(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4uiv(arg0,arg1);
    checkGLGetError("glVertexAttrib4uiv");
  }
  public  void glVertexAttrib4uiv(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4uiv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4uiv");
  }
  public  void glVertexAttrib4uivARB(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4uivARB(arg0,arg1);
    checkGLGetError("glVertexAttrib4uivARB");
  }
  public  void glVertexAttrib4uivARB(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4uivARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4uivARB");
  }
  public  void glVertexAttrib4usv(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4usv(arg0,arg1);
    checkGLGetError("glVertexAttrib4usv");
  }
  public  void glVertexAttrib4usv(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4usv(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4usv");
  }
  public  void glVertexAttrib4usvARB(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttrib4usvARB(arg0,arg1);
    checkGLGetError("glVertexAttrib4usvARB");
  }
  public  void glVertexAttrib4usvARB(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttrib4usvARB(arg0,arg1,arg2);
    checkGLGetError("glVertexAttrib4usvARB");
  }
  public  void glVertexAttribArrayObjectATI(int arg0,int arg1,int arg2,boolean arg3,int arg4,int arg5,int arg6)
  {
        checkContext();
downstreamGL.glVertexAttribArrayObjectATI(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    checkGLGetError("glVertexAttribArrayObjectATI");
  }
  public  void glVertexAttribI1iEXT(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertexAttribI1iEXT(arg0,arg1);
    checkGLGetError("glVertexAttribI1iEXT");
  }
  public  void glVertexAttribI1ivEXT(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttribI1ivEXT(arg0,arg1);
    checkGLGetError("glVertexAttribI1ivEXT");
  }
  public  void glVertexAttribI1ivEXT(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttribI1ivEXT(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribI1ivEXT");
  }
  public  void glVertexAttribI1uiEXT(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertexAttribI1uiEXT(arg0,arg1);
    checkGLGetError("glVertexAttribI1uiEXT");
  }
  public  void glVertexAttribI1uivEXT(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttribI1uivEXT(arg0,arg1);
    checkGLGetError("glVertexAttribI1uivEXT");
  }
  public  void glVertexAttribI1uivEXT(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttribI1uivEXT(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribI1uivEXT");
  }
  public  void glVertexAttribI2iEXT(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttribI2iEXT(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribI2iEXT");
  }
  public  void glVertexAttribI2ivEXT(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttribI2ivEXT(arg0,arg1);
    checkGLGetError("glVertexAttribI2ivEXT");
  }
  public  void glVertexAttribI2ivEXT(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttribI2ivEXT(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribI2ivEXT");
  }
  public  void glVertexAttribI2uiEXT(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttribI2uiEXT(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribI2uiEXT");
  }
  public  void glVertexAttribI2uivEXT(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttribI2uivEXT(arg0,arg1);
    checkGLGetError("glVertexAttribI2uivEXT");
  }
  public  void glVertexAttribI2uivEXT(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttribI2uivEXT(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribI2uivEXT");
  }
  public  void glVertexAttribI3iEXT(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribI3iEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribI3iEXT");
  }
  public  void glVertexAttribI3ivEXT(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttribI3ivEXT(arg0,arg1);
    checkGLGetError("glVertexAttribI3ivEXT");
  }
  public  void glVertexAttribI3ivEXT(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttribI3ivEXT(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribI3ivEXT");
  }
  public  void glVertexAttribI3uiEXT(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribI3uiEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribI3uiEXT");
  }
  public  void glVertexAttribI3uivEXT(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttribI3uivEXT(arg0,arg1);
    checkGLGetError("glVertexAttribI3uivEXT");
  }
  public  void glVertexAttribI3uivEXT(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttribI3uivEXT(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribI3uivEXT");
  }
  public  void glVertexAttribI4bvEXT(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttribI4bvEXT(arg0,arg1);
    checkGLGetError("glVertexAttribI4bvEXT");
  }
  public  void glVertexAttribI4bvEXT(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttribI4bvEXT(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribI4bvEXT");
  }
  public  void glVertexAttribI4iEXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glVertexAttribI4iEXT(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttribI4iEXT");
  }
  public  void glVertexAttribI4ivEXT(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttribI4ivEXT(arg0,arg1);
    checkGLGetError("glVertexAttribI4ivEXT");
  }
  public  void glVertexAttribI4ivEXT(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttribI4ivEXT(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribI4ivEXT");
  }
  public  void glVertexAttribI4svEXT(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttribI4svEXT(arg0,arg1);
    checkGLGetError("glVertexAttribI4svEXT");
  }
  public  void glVertexAttribI4svEXT(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttribI4svEXT(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribI4svEXT");
  }
  public  void glVertexAttribI4ubvEXT(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttribI4ubvEXT(arg0,arg1);
    checkGLGetError("glVertexAttribI4ubvEXT");
  }
  public  void glVertexAttribI4ubvEXT(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttribI4ubvEXT(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribI4ubvEXT");
  }
  public  void glVertexAttribI4uiEXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glVertexAttribI4uiEXT(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttribI4uiEXT");
  }
  public  void glVertexAttribI4uivEXT(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttribI4uivEXT(arg0,arg1);
    checkGLGetError("glVertexAttribI4uivEXT");
  }
  public  void glVertexAttribI4uivEXT(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttribI4uivEXT(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribI4uivEXT");
  }
  public  void glVertexAttribI4usvEXT(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexAttribI4usvEXT(arg0,arg1);
    checkGLGetError("glVertexAttribI4usvEXT");
  }
  public  void glVertexAttribI4usvEXT(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexAttribI4usvEXT(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribI4usvEXT");
  }
  public  void glVertexAttribIPointerEXT(int arg0,int arg1,int arg2,int arg3,java.nio.Buffer arg4)
  {
        checkContext();
downstreamGL.glVertexAttribIPointerEXT(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttribIPointerEXT");
  }
  public  void glVertexAttribPointer(int arg0,int arg1,int arg2,boolean arg3,int arg4,java.nio.Buffer arg5)
  {
        checkContext();
downstreamGL.glVertexAttribPointer(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glVertexAttribPointer");
  }
  public  void glVertexAttribPointer(int arg0,int arg1,int arg2,boolean arg3,int arg4,long arg5)
  {
        checkContext();
downstreamGL.glVertexAttribPointer(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glVertexAttribPointer");
  }
  public  void glVertexAttribPointerARB(int arg0,int arg1,int arg2,boolean arg3,int arg4,java.nio.Buffer arg5)
  {
        checkContext();
downstreamGL.glVertexAttribPointerARB(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glVertexAttribPointerARB");
  }
  public  void glVertexAttribPointerARB(int arg0,int arg1,int arg2,boolean arg3,int arg4,long arg5)
  {
        checkContext();
downstreamGL.glVertexAttribPointerARB(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glVertexAttribPointerARB");
  }
  public  void glVertexAttribPointerNV(int arg0,int arg1,int arg2,int arg3,java.nio.Buffer arg4)
  {
        checkContext();
downstreamGL.glVertexAttribPointerNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttribPointerNV");
  }
  public  void glVertexAttribPointerNV(int arg0,int arg1,int arg2,int arg3,long arg4)
  {
        checkContext();
downstreamGL.glVertexAttribPointerNV(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexAttribPointerNV");
  }
  public  void glVertexAttribs1dvNV(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
        checkContext();
downstreamGL.glVertexAttribs1dvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribs1dvNV");
  }
  public  void glVertexAttribs1dvNV(int arg0,int arg1,double[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribs1dvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribs1dvNV");
  }
  public  void glVertexAttribs1fvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glVertexAttribs1fvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribs1fvNV");
  }
  public  void glVertexAttribs1fvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribs1fvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribs1fvNV");
  }
  public  void glVertexAttribs1hvNV(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
        checkContext();
downstreamGL.glVertexAttribs1hvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribs1hvNV");
  }
  public  void glVertexAttribs1hvNV(int arg0,int arg1,short[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribs1hvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribs1hvNV");
  }
  public  void glVertexAttribs1svNV(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
        checkContext();
downstreamGL.glVertexAttribs1svNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribs1svNV");
  }
  public  void glVertexAttribs1svNV(int arg0,int arg1,short[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribs1svNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribs1svNV");
  }
  public  void glVertexAttribs2dvNV(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
        checkContext();
downstreamGL.glVertexAttribs2dvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribs2dvNV");
  }
  public  void glVertexAttribs2dvNV(int arg0,int arg1,double[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribs2dvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribs2dvNV");
  }
  public  void glVertexAttribs2fvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glVertexAttribs2fvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribs2fvNV");
  }
  public  void glVertexAttribs2fvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribs2fvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribs2fvNV");
  }
  public  void glVertexAttribs2hvNV(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
        checkContext();
downstreamGL.glVertexAttribs2hvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribs2hvNV");
  }
  public  void glVertexAttribs2hvNV(int arg0,int arg1,short[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribs2hvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribs2hvNV");
  }
  public  void glVertexAttribs2svNV(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
        checkContext();
downstreamGL.glVertexAttribs2svNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribs2svNV");
  }
  public  void glVertexAttribs2svNV(int arg0,int arg1,short[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribs2svNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribs2svNV");
  }
  public  void glVertexAttribs3dvNV(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
        checkContext();
downstreamGL.glVertexAttribs3dvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribs3dvNV");
  }
  public  void glVertexAttribs3dvNV(int arg0,int arg1,double[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribs3dvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribs3dvNV");
  }
  public  void glVertexAttribs3fvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glVertexAttribs3fvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribs3fvNV");
  }
  public  void glVertexAttribs3fvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribs3fvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribs3fvNV");
  }
  public  void glVertexAttribs3hvNV(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
        checkContext();
downstreamGL.glVertexAttribs3hvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribs3hvNV");
  }
  public  void glVertexAttribs3hvNV(int arg0,int arg1,short[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribs3hvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribs3hvNV");
  }
  public  void glVertexAttribs3svNV(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
        checkContext();
downstreamGL.glVertexAttribs3svNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribs3svNV");
  }
  public  void glVertexAttribs3svNV(int arg0,int arg1,short[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribs3svNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribs3svNV");
  }
  public  void glVertexAttribs4dvNV(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
        checkContext();
downstreamGL.glVertexAttribs4dvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribs4dvNV");
  }
  public  void glVertexAttribs4dvNV(int arg0,int arg1,double[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribs4dvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribs4dvNV");
  }
  public  void glVertexAttribs4fvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
        checkContext();
downstreamGL.glVertexAttribs4fvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribs4fvNV");
  }
  public  void glVertexAttribs4fvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribs4fvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribs4fvNV");
  }
  public  void glVertexAttribs4hvNV(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
        checkContext();
downstreamGL.glVertexAttribs4hvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribs4hvNV");
  }
  public  void glVertexAttribs4hvNV(int arg0,int arg1,short[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribs4hvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribs4hvNV");
  }
  public  void glVertexAttribs4svNV(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
        checkContext();
downstreamGL.glVertexAttribs4svNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribs4svNV");
  }
  public  void glVertexAttribs4svNV(int arg0,int arg1,short[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribs4svNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribs4svNV");
  }
  public  void glVertexAttribs4ubvNV(int arg0,int arg1,java.nio.ByteBuffer arg2)
  {
        checkContext();
downstreamGL.glVertexAttribs4ubvNV(arg0,arg1,arg2);
    checkGLGetError("glVertexAttribs4ubvNV");
  }
  public  void glVertexAttribs4ubvNV(int arg0,int arg1,byte[] arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexAttribs4ubvNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexAttribs4ubvNV");
  }
  public  void glVertexBlendARB(int arg0)
  {
        checkContext();
downstreamGL.glVertexBlendARB(arg0);
    checkGLGetError("glVertexBlendARB");
  }
  public  void glVertexBlendEnvfATI(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glVertexBlendEnvfATI(arg0,arg1);
    checkGLGetError("glVertexBlendEnvfATI");
  }
  public  void glVertexBlendEnviATI(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertexBlendEnviATI(arg0,arg1);
    checkGLGetError("glVertexBlendEnviATI");
  }
  public  void glVertexPointer(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
        checkContext();
downstreamGL.glVertexPointer(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexPointer");
  }
  public  void glVertexPointer(int arg0,int arg1,int arg2,long arg3)
  {
        checkContext();
downstreamGL.glVertexPointer(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexPointer");
  }
  public  void glVertexStream1dATI(int arg0,double arg1)
  {
        checkContext();
downstreamGL.glVertexStream1dATI(arg0,arg1);
    checkGLGetError("glVertexStream1dATI");
  }
  public  void glVertexStream1dvATI(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexStream1dvATI(arg0,arg1);
    checkGLGetError("glVertexStream1dvATI");
  }
  public  void glVertexStream1dvATI(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexStream1dvATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream1dvATI");
  }
  public  void glVertexStream1fATI(int arg0,float arg1)
  {
        checkContext();
downstreamGL.glVertexStream1fATI(arg0,arg1);
    checkGLGetError("glVertexStream1fATI");
  }
  public  void glVertexStream1fvATI(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexStream1fvATI(arg0,arg1);
    checkGLGetError("glVertexStream1fvATI");
  }
  public  void glVertexStream1fvATI(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexStream1fvATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream1fvATI");
  }
  public  void glVertexStream1iATI(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertexStream1iATI(arg0,arg1);
    checkGLGetError("glVertexStream1iATI");
  }
  public  void glVertexStream1ivATI(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexStream1ivATI(arg0,arg1);
    checkGLGetError("glVertexStream1ivATI");
  }
  public  void glVertexStream1ivATI(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexStream1ivATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream1ivATI");
  }
  public  void glVertexStream1sATI(int arg0,short arg1)
  {
        checkContext();
downstreamGL.glVertexStream1sATI(arg0,arg1);
    checkGLGetError("glVertexStream1sATI");
  }
  public  void glVertexStream1svATI(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexStream1svATI(arg0,arg1);
    checkGLGetError("glVertexStream1svATI");
  }
  public  void glVertexStream1svATI(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexStream1svATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream1svATI");
  }
  public  void glVertexStream2dATI(int arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glVertexStream2dATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream2dATI");
  }
  public  void glVertexStream2dvATI(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexStream2dvATI(arg0,arg1);
    checkGLGetError("glVertexStream2dvATI");
  }
  public  void glVertexStream2dvATI(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexStream2dvATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream2dvATI");
  }
  public  void glVertexStream2fATI(int arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glVertexStream2fATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream2fATI");
  }
  public  void glVertexStream2fvATI(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexStream2fvATI(arg0,arg1);
    checkGLGetError("glVertexStream2fvATI");
  }
  public  void glVertexStream2fvATI(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexStream2fvATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream2fvATI");
  }
  public  void glVertexStream2iATI(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexStream2iATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream2iATI");
  }
  public  void glVertexStream2ivATI(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexStream2ivATI(arg0,arg1);
    checkGLGetError("glVertexStream2ivATI");
  }
  public  void glVertexStream2ivATI(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexStream2ivATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream2ivATI");
  }
  public  void glVertexStream2sATI(int arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glVertexStream2sATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream2sATI");
  }
  public  void glVertexStream2svATI(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexStream2svATI(arg0,arg1);
    checkGLGetError("glVertexStream2svATI");
  }
  public  void glVertexStream2svATI(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexStream2svATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream2svATI");
  }
  public  void glVertexStream3dATI(int arg0,double arg1,double arg2,double arg3)
  {
        checkContext();
downstreamGL.glVertexStream3dATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexStream3dATI");
  }
  public  void glVertexStream3dvATI(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexStream3dvATI(arg0,arg1);
    checkGLGetError("glVertexStream3dvATI");
  }
  public  void glVertexStream3dvATI(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexStream3dvATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream3dvATI");
  }
  public  void glVertexStream3fATI(int arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glVertexStream3fATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexStream3fATI");
  }
  public  void glVertexStream3fvATI(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexStream3fvATI(arg0,arg1);
    checkGLGetError("glVertexStream3fvATI");
  }
  public  void glVertexStream3fvATI(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexStream3fvATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream3fvATI");
  }
  public  void glVertexStream3iATI(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glVertexStream3iATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexStream3iATI");
  }
  public  void glVertexStream3ivATI(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexStream3ivATI(arg0,arg1);
    checkGLGetError("glVertexStream3ivATI");
  }
  public  void glVertexStream3ivATI(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexStream3ivATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream3ivATI");
  }
  public  void glVertexStream3sATI(int arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glVertexStream3sATI(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexStream3sATI");
  }
  public  void glVertexStream3svATI(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexStream3svATI(arg0,arg1);
    checkGLGetError("glVertexStream3svATI");
  }
  public  void glVertexStream3svATI(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexStream3svATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream3svATI");
  }
  public  void glVertexStream4dATI(int arg0,double arg1,double arg2,double arg3,double arg4)
  {
        checkContext();
downstreamGL.glVertexStream4dATI(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexStream4dATI");
  }
  public  void glVertexStream4dvATI(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexStream4dvATI(arg0,arg1);
    checkGLGetError("glVertexStream4dvATI");
  }
  public  void glVertexStream4dvATI(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexStream4dvATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream4dvATI");
  }
  public  void glVertexStream4fATI(int arg0,float arg1,float arg2,float arg3,float arg4)
  {
        checkContext();
downstreamGL.glVertexStream4fATI(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexStream4fATI");
  }
  public  void glVertexStream4fvATI(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexStream4fvATI(arg0,arg1);
    checkGLGetError("glVertexStream4fvATI");
  }
  public  void glVertexStream4fvATI(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexStream4fvATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream4fvATI");
  }
  public  void glVertexStream4iATI(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
        checkContext();
downstreamGL.glVertexStream4iATI(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexStream4iATI");
  }
  public  void glVertexStream4ivATI(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexStream4ivATI(arg0,arg1);
    checkGLGetError("glVertexStream4ivATI");
  }
  public  void glVertexStream4ivATI(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexStream4ivATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream4ivATI");
  }
  public  void glVertexStream4sATI(int arg0,short arg1,short arg2,short arg3,short arg4)
  {
        checkContext();
downstreamGL.glVertexStream4sATI(arg0,arg1,arg2,arg3,arg4);
    checkGLGetError("glVertexStream4sATI");
  }
  public  void glVertexStream4svATI(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glVertexStream4svATI(arg0,arg1);
    checkGLGetError("glVertexStream4svATI");
  }
  public  void glVertexStream4svATI(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glVertexStream4svATI(arg0,arg1,arg2);
    checkGLGetError("glVertexStream4svATI");
  }
  public  void glVertexWeightPointerEXT(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
        checkContext();
downstreamGL.glVertexWeightPointerEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexWeightPointerEXT");
  }
  public  void glVertexWeightPointerEXT(int arg0,int arg1,int arg2,long arg3)
  {
        checkContext();
downstreamGL.glVertexWeightPointerEXT(arg0,arg1,arg2,arg3);
    checkGLGetError("glVertexWeightPointerEXT");
  }
  public  void glVertexWeightfEXT(float arg0)
  {
        checkContext();
downstreamGL.glVertexWeightfEXT(arg0);
    checkGLGetError("glVertexWeightfEXT");
  }
  public  void glVertexWeightfvEXT(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glVertexWeightfvEXT(arg0);
    checkGLGetError("glVertexWeightfvEXT");
  }
  public  void glVertexWeightfvEXT(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertexWeightfvEXT(arg0,arg1);
    checkGLGetError("glVertexWeightfvEXT");
  }
  public  void glVertexWeighthNV(short arg0)
  {
        checkContext();
downstreamGL.glVertexWeighthNV(arg0);
    checkGLGetError("glVertexWeighthNV");
  }
  public  void glVertexWeighthvNV(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glVertexWeighthvNV(arg0);
    checkGLGetError("glVertexWeighthvNV");
  }
  public  void glVertexWeighthvNV(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glVertexWeighthvNV(arg0,arg1);
    checkGLGetError("glVertexWeighthvNV");
  }
  public  void glViewport(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glViewport(arg0,arg1,arg2,arg3);
    checkGLGetError("glViewport");
  }
  public  void glWeightPointerARB(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
        checkContext();
downstreamGL.glWeightPointerARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glWeightPointerARB");
  }
  public  void glWeightPointerARB(int arg0,int arg1,int arg2,long arg3)
  {
        checkContext();
downstreamGL.glWeightPointerARB(arg0,arg1,arg2,arg3);
    checkGLGetError("glWeightPointerARB");
  }
  public  void glWeightbvARB(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glWeightbvARB(arg0,arg1);
    checkGLGetError("glWeightbvARB");
  }
  public  void glWeightbvARB(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glWeightbvARB(arg0,arg1,arg2);
    checkGLGetError("glWeightbvARB");
  }
  public  void glWeightdvARB(int arg0,java.nio.DoubleBuffer arg1)
  {
        checkContext();
downstreamGL.glWeightdvARB(arg0,arg1);
    checkGLGetError("glWeightdvARB");
  }
  public  void glWeightdvARB(int arg0,double[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glWeightdvARB(arg0,arg1,arg2);
    checkGLGetError("glWeightdvARB");
  }
  public  void glWeightfvARB(int arg0,java.nio.FloatBuffer arg1)
  {
        checkContext();
downstreamGL.glWeightfvARB(arg0,arg1);
    checkGLGetError("glWeightfvARB");
  }
  public  void glWeightfvARB(int arg0,float[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glWeightfvARB(arg0,arg1,arg2);
    checkGLGetError("glWeightfvARB");
  }
  public  void glWeightivARB(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glWeightivARB(arg0,arg1);
    checkGLGetError("glWeightivARB");
  }
  public  void glWeightivARB(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glWeightivARB(arg0,arg1,arg2);
    checkGLGetError("glWeightivARB");
  }
  public  void glWeightsvARB(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glWeightsvARB(arg0,arg1);
    checkGLGetError("glWeightsvARB");
  }
  public  void glWeightsvARB(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glWeightsvARB(arg0,arg1,arg2);
    checkGLGetError("glWeightsvARB");
  }
  public  void glWeightubvARB(int arg0,java.nio.ByteBuffer arg1)
  {
        checkContext();
downstreamGL.glWeightubvARB(arg0,arg1);
    checkGLGetError("glWeightubvARB");
  }
  public  void glWeightubvARB(int arg0,byte[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glWeightubvARB(arg0,arg1,arg2);
    checkGLGetError("glWeightubvARB");
  }
  public  void glWeightuivARB(int arg0,java.nio.IntBuffer arg1)
  {
        checkContext();
downstreamGL.glWeightuivARB(arg0,arg1);
    checkGLGetError("glWeightuivARB");
  }
  public  void glWeightuivARB(int arg0,int[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glWeightuivARB(arg0,arg1,arg2);
    checkGLGetError("glWeightuivARB");
  }
  public  void glWeightusvARB(int arg0,java.nio.ShortBuffer arg1)
  {
        checkContext();
downstreamGL.glWeightusvARB(arg0,arg1);
    checkGLGetError("glWeightusvARB");
  }
  public  void glWeightusvARB(int arg0,short[] arg1,int arg2)
  {
        checkContext();
downstreamGL.glWeightusvARB(arg0,arg1,arg2);
    checkGLGetError("glWeightusvARB");
  }
  public  void glWindowPos2d(double arg0,double arg1)
  {
        checkContext();
downstreamGL.glWindowPos2d(arg0,arg1);
    checkGLGetError("glWindowPos2d");
  }
  public  void glWindowPos2dARB(double arg0,double arg1)
  {
        checkContext();
downstreamGL.glWindowPos2dARB(arg0,arg1);
    checkGLGetError("glWindowPos2dARB");
  }
  public  void glWindowPos2dMESA(double arg0,double arg1)
  {
        checkContext();
downstreamGL.glWindowPos2dMESA(arg0,arg1);
    checkGLGetError("glWindowPos2dMESA");
  }
  public  void glWindowPos2dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos2dv(arg0);
    checkGLGetError("glWindowPos2dv");
  }
  public  void glWindowPos2dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos2dv(arg0,arg1);
    checkGLGetError("glWindowPos2dv");
  }
  public  void glWindowPos2dvARB(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos2dvARB(arg0);
    checkGLGetError("glWindowPos2dvARB");
  }
  public  void glWindowPos2dvARB(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos2dvARB(arg0,arg1);
    checkGLGetError("glWindowPos2dvARB");
  }
  public  void glWindowPos2dvMESA(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos2dvMESA(arg0);
    checkGLGetError("glWindowPos2dvMESA");
  }
  public  void glWindowPos2dvMESA(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos2dvMESA(arg0,arg1);
    checkGLGetError("glWindowPos2dvMESA");
  }
  public  void glWindowPos2f(float arg0,float arg1)
  {
        checkContext();
downstreamGL.glWindowPos2f(arg0,arg1);
    checkGLGetError("glWindowPos2f");
  }
  public  void glWindowPos2fARB(float arg0,float arg1)
  {
        checkContext();
downstreamGL.glWindowPos2fARB(arg0,arg1);
    checkGLGetError("glWindowPos2fARB");
  }
  public  void glWindowPos2fMESA(float arg0,float arg1)
  {
        checkContext();
downstreamGL.glWindowPos2fMESA(arg0,arg1);
    checkGLGetError("glWindowPos2fMESA");
  }
  public  void glWindowPos2fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos2fv(arg0);
    checkGLGetError("glWindowPos2fv");
  }
  public  void glWindowPos2fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos2fv(arg0,arg1);
    checkGLGetError("glWindowPos2fv");
  }
  public  void glWindowPos2fvARB(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos2fvARB(arg0);
    checkGLGetError("glWindowPos2fvARB");
  }
  public  void glWindowPos2fvARB(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos2fvARB(arg0,arg1);
    checkGLGetError("glWindowPos2fvARB");
  }
  public  void glWindowPos2fvMESA(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos2fvMESA(arg0);
    checkGLGetError("glWindowPos2fvMESA");
  }
  public  void glWindowPos2fvMESA(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos2fvMESA(arg0,arg1);
    checkGLGetError("glWindowPos2fvMESA");
  }
  public  void glWindowPos2i(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos2i(arg0,arg1);
    checkGLGetError("glWindowPos2i");
  }
  public  void glWindowPos2iARB(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos2iARB(arg0,arg1);
    checkGLGetError("glWindowPos2iARB");
  }
  public  void glWindowPos2iMESA(int arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos2iMESA(arg0,arg1);
    checkGLGetError("glWindowPos2iMESA");
  }
  public  void glWindowPos2iv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos2iv(arg0);
    checkGLGetError("glWindowPos2iv");
  }
  public  void glWindowPos2iv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos2iv(arg0,arg1);
    checkGLGetError("glWindowPos2iv");
  }
  public  void glWindowPos2ivARB(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos2ivARB(arg0);
    checkGLGetError("glWindowPos2ivARB");
  }
  public  void glWindowPos2ivARB(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos2ivARB(arg0,arg1);
    checkGLGetError("glWindowPos2ivARB");
  }
  public  void glWindowPos2ivMESA(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos2ivMESA(arg0);
    checkGLGetError("glWindowPos2ivMESA");
  }
  public  void glWindowPos2ivMESA(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos2ivMESA(arg0,arg1);
    checkGLGetError("glWindowPos2ivMESA");
  }
  public  void glWindowPos2s(short arg0,short arg1)
  {
        checkContext();
downstreamGL.glWindowPos2s(arg0,arg1);
    checkGLGetError("glWindowPos2s");
  }
  public  void glWindowPos2sARB(short arg0,short arg1)
  {
        checkContext();
downstreamGL.glWindowPos2sARB(arg0,arg1);
    checkGLGetError("glWindowPos2sARB");
  }
  public  void glWindowPos2sMESA(short arg0,short arg1)
  {
        checkContext();
downstreamGL.glWindowPos2sMESA(arg0,arg1);
    checkGLGetError("glWindowPos2sMESA");
  }
  public  void glWindowPos2sv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos2sv(arg0);
    checkGLGetError("glWindowPos2sv");
  }
  public  void glWindowPos2sv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos2sv(arg0,arg1);
    checkGLGetError("glWindowPos2sv");
  }
  public  void glWindowPos2svARB(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos2svARB(arg0);
    checkGLGetError("glWindowPos2svARB");
  }
  public  void glWindowPos2svARB(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos2svARB(arg0,arg1);
    checkGLGetError("glWindowPos2svARB");
  }
  public  void glWindowPos2svMESA(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos2svMESA(arg0);
    checkGLGetError("glWindowPos2svMESA");
  }
  public  void glWindowPos2svMESA(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos2svMESA(arg0,arg1);
    checkGLGetError("glWindowPos2svMESA");
  }
  public  void glWindowPos3d(double arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glWindowPos3d(arg0,arg1,arg2);
    checkGLGetError("glWindowPos3d");
  }
  public  void glWindowPos3dARB(double arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glWindowPos3dARB(arg0,arg1,arg2);
    checkGLGetError("glWindowPos3dARB");
  }
  public  void glWindowPos3dMESA(double arg0,double arg1,double arg2)
  {
        checkContext();
downstreamGL.glWindowPos3dMESA(arg0,arg1,arg2);
    checkGLGetError("glWindowPos3dMESA");
  }
  public  void glWindowPos3dv(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos3dv(arg0);
    checkGLGetError("glWindowPos3dv");
  }
  public  void glWindowPos3dv(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos3dv(arg0,arg1);
    checkGLGetError("glWindowPos3dv");
  }
  public  void glWindowPos3dvARB(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos3dvARB(arg0);
    checkGLGetError("glWindowPos3dvARB");
  }
  public  void glWindowPos3dvARB(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos3dvARB(arg0,arg1);
    checkGLGetError("glWindowPos3dvARB");
  }
  public  void glWindowPos3dvMESA(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos3dvMESA(arg0);
    checkGLGetError("glWindowPos3dvMESA");
  }
  public  void glWindowPos3dvMESA(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos3dvMESA(arg0,arg1);
    checkGLGetError("glWindowPos3dvMESA");
  }
  public  void glWindowPos3f(float arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glWindowPos3f(arg0,arg1,arg2);
    checkGLGetError("glWindowPos3f");
  }
  public  void glWindowPos3fARB(float arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glWindowPos3fARB(arg0,arg1,arg2);
    checkGLGetError("glWindowPos3fARB");
  }
  public  void glWindowPos3fMESA(float arg0,float arg1,float arg2)
  {
        checkContext();
downstreamGL.glWindowPos3fMESA(arg0,arg1,arg2);
    checkGLGetError("glWindowPos3fMESA");
  }
  public  void glWindowPos3fv(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos3fv(arg0);
    checkGLGetError("glWindowPos3fv");
  }
  public  void glWindowPos3fv(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos3fv(arg0,arg1);
    checkGLGetError("glWindowPos3fv");
  }
  public  void glWindowPos3fvARB(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos3fvARB(arg0);
    checkGLGetError("glWindowPos3fvARB");
  }
  public  void glWindowPos3fvARB(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos3fvARB(arg0,arg1);
    checkGLGetError("glWindowPos3fvARB");
  }
  public  void glWindowPos3fvMESA(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos3fvMESA(arg0);
    checkGLGetError("glWindowPos3fvMESA");
  }
  public  void glWindowPos3fvMESA(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos3fvMESA(arg0,arg1);
    checkGLGetError("glWindowPos3fvMESA");
  }
  public  void glWindowPos3i(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glWindowPos3i(arg0,arg1,arg2);
    checkGLGetError("glWindowPos3i");
  }
  public  void glWindowPos3iARB(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glWindowPos3iARB(arg0,arg1,arg2);
    checkGLGetError("glWindowPos3iARB");
  }
  public  void glWindowPos3iMESA(int arg0,int arg1,int arg2)
  {
        checkContext();
downstreamGL.glWindowPos3iMESA(arg0,arg1,arg2);
    checkGLGetError("glWindowPos3iMESA");
  }
  public  void glWindowPos3iv(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos3iv(arg0);
    checkGLGetError("glWindowPos3iv");
  }
  public  void glWindowPos3iv(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos3iv(arg0,arg1);
    checkGLGetError("glWindowPos3iv");
  }
  public  void glWindowPos3ivARB(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos3ivARB(arg0);
    checkGLGetError("glWindowPos3ivARB");
  }
  public  void glWindowPos3ivARB(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos3ivARB(arg0,arg1);
    checkGLGetError("glWindowPos3ivARB");
  }
  public  void glWindowPos3ivMESA(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos3ivMESA(arg0);
    checkGLGetError("glWindowPos3ivMESA");
  }
  public  void glWindowPos3ivMESA(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos3ivMESA(arg0,arg1);
    checkGLGetError("glWindowPos3ivMESA");
  }
  public  void glWindowPos3s(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glWindowPos3s(arg0,arg1,arg2);
    checkGLGetError("glWindowPos3s");
  }
  public  void glWindowPos3sARB(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glWindowPos3sARB(arg0,arg1,arg2);
    checkGLGetError("glWindowPos3sARB");
  }
  public  void glWindowPos3sMESA(short arg0,short arg1,short arg2)
  {
        checkContext();
downstreamGL.glWindowPos3sMESA(arg0,arg1,arg2);
    checkGLGetError("glWindowPos3sMESA");
  }
  public  void glWindowPos3sv(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos3sv(arg0);
    checkGLGetError("glWindowPos3sv");
  }
  public  void glWindowPos3sv(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos3sv(arg0,arg1);
    checkGLGetError("glWindowPos3sv");
  }
  public  void glWindowPos3svARB(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos3svARB(arg0);
    checkGLGetError("glWindowPos3svARB");
  }
  public  void glWindowPos3svARB(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos3svARB(arg0,arg1);
    checkGLGetError("glWindowPos3svARB");
  }
  public  void glWindowPos3svMESA(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos3svMESA(arg0);
    checkGLGetError("glWindowPos3svMESA");
  }
  public  void glWindowPos3svMESA(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos3svMESA(arg0,arg1);
    checkGLGetError("glWindowPos3svMESA");
  }
  public  void glWindowPos4dMESA(double arg0,double arg1,double arg2,double arg3)
  {
        checkContext();
downstreamGL.glWindowPos4dMESA(arg0,arg1,arg2,arg3);
    checkGLGetError("glWindowPos4dMESA");
  }
  public  void glWindowPos4dvMESA(java.nio.DoubleBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos4dvMESA(arg0);
    checkGLGetError("glWindowPos4dvMESA");
  }
  public  void glWindowPos4dvMESA(double[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos4dvMESA(arg0,arg1);
    checkGLGetError("glWindowPos4dvMESA");
  }
  public  void glWindowPos4fMESA(float arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
downstreamGL.glWindowPos4fMESA(arg0,arg1,arg2,arg3);
    checkGLGetError("glWindowPos4fMESA");
  }
  public  void glWindowPos4fvMESA(java.nio.FloatBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos4fvMESA(arg0);
    checkGLGetError("glWindowPos4fvMESA");
  }
  public  void glWindowPos4fvMESA(float[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos4fvMESA(arg0,arg1);
    checkGLGetError("glWindowPos4fvMESA");
  }
  public  void glWindowPos4iMESA(int arg0,int arg1,int arg2,int arg3)
  {
        checkContext();
downstreamGL.glWindowPos4iMESA(arg0,arg1,arg2,arg3);
    checkGLGetError("glWindowPos4iMESA");
  }
  public  void glWindowPos4ivMESA(java.nio.IntBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos4ivMESA(arg0);
    checkGLGetError("glWindowPos4ivMESA");
  }
  public  void glWindowPos4ivMESA(int[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos4ivMESA(arg0,arg1);
    checkGLGetError("glWindowPos4ivMESA");
  }
  public  void glWindowPos4sMESA(short arg0,short arg1,short arg2,short arg3)
  {
        checkContext();
downstreamGL.glWindowPos4sMESA(arg0,arg1,arg2,arg3);
    checkGLGetError("glWindowPos4sMESA");
  }
  public  void glWindowPos4svMESA(java.nio.ShortBuffer arg0)
  {
        checkContext();
downstreamGL.glWindowPos4svMESA(arg0);
    checkGLGetError("glWindowPos4svMESA");
  }
  public  void glWindowPos4svMESA(short[] arg0,int arg1)
  {
        checkContext();
downstreamGL.glWindowPos4svMESA(arg0,arg1);
    checkGLGetError("glWindowPos4svMESA");
  }
  public  void glWriteMaskEXT(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
        checkContext();
downstreamGL.glWriteMaskEXT(arg0,arg1,arg2,arg3,arg4,arg5);
    checkGLGetError("glWriteMaskEXT");
  }
  public  boolean isFunctionAvailable(java.lang.String arg0)
  {
        checkContext();
boolean _res = downstreamGL.isFunctionAvailable(arg0);
    checkGLGetError("isFunctionAvailable");
    return _res;
  }
  public  boolean isExtensionAvailable(java.lang.String arg0)
  {
        checkContext();
boolean _res = downstreamGL.isExtensionAvailable(arg0);
    checkGLGetError("isExtensionAvailable");
    return _res;
  }
  public  java.nio.ByteBuffer glAllocateMemoryNV(int arg0,float arg1,float arg2,float arg3)
  {
        checkContext();
java.nio.ByteBuffer _res = downstreamGL.glAllocateMemoryNV(arg0,arg1,arg2,arg3);
    checkGLGetError("glAllocateMemoryNV");
    return _res;
  }
  public  void setSwapInterval(int arg0)
  {
        checkContext();
downstreamGL.setSwapInterval(arg0);
    checkGLGetError("setSwapInterval");
  }
  public  java.lang.Object getPlatformGLExtensions()
  {
        checkContext();
java.lang.Object _res = downstreamGL.getPlatformGLExtensions();
    checkGLGetError("getPlatformGLExtensions");
    return _res;
  }
  public  java.lang.Object getExtension(java.lang.String arg0)
  {
        checkContext();
java.lang.Object _res = downstreamGL.getExtension(arg0);
    checkGLGetError("getExtension");
    return _res;
  }
  private void checkGLGetError(String caller)
  {
    if (insideBeginEndPair) {
      return;
    }

    // Debug code to make sure the pipeline is working; leave commented out unless testing this class
    //System.err.println("Checking for GL errors after call to " + caller + "()");

    int err = downstreamGL.glGetError();
    if (err == GL_NO_ERROR) { return; }

    StringBuffer buf = new StringBuffer(
      "glGetError() returned the following error codes after a call to " + caller + "(): ");

    // Loop repeatedly to allow for distributed GL implementations,
    // as detailed in the glGetError() specification
    int recursionDepth = 10;
    do {
      switch (err) {
        case GL_INVALID_ENUM: buf.append("GL_INVALID_ENUM "); break;
        case GL_INVALID_VALUE: buf.append("GL_INVALID_VALUE "); break;
        case GL_INVALID_OPERATION: buf.append("GL_INVALID_OPERATION "); break;
        case GL_STACK_OVERFLOW: buf.append("GL_STACK_OVERFLOW "); break;
        case GL_STACK_UNDERFLOW: buf.append("GL_STACK_UNDERFLOW "); break;
        case GL_OUT_OF_MEMORY: buf.append("GL_OUT_OF_MEMORY "); break;
        case GL_NO_ERROR: throw new InternalError("Should not be treating GL_NO_ERROR as error");
        default: throw new InternalError("Unknown glGetError() return value: " + err);
      }
    } while ((--recursionDepth >= 0) && (err = downstreamGL.glGetError()) != GL_NO_ERROR);
    throw new GLException(buf.toString());
  }
  /** True if the pipeline is inside a glBegin/glEnd pair.*/
  private boolean insideBeginEndPair = false;

  private void checkContext() {
    GLContext currentContext = GLContext.getCurrent();
    if (currentContext == null) {
      throw new GLException("No OpenGL context is current on this thread");
    }
    if ((_context != null) && (_context != currentContext)) {
      throw new GLException("This GL object is being incorrectly used with a different GLContext than that which created it");
    }
  }
  private GLContext _context;

  private GL downstreamGL;
} // end class DebugGL
