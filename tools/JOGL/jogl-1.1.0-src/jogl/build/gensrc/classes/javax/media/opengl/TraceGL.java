package javax.media.opengl;

import java.io.*;
import com.sun.gluegen.runtime.*;

/** <P> Composable pipeline which wraps an underlying {@link GL} implementation,
    providing tracing information to a user-specified {@link java.io.PrintStream}
    before and after each OpenGL method call. Sample code which installs this pipeline: </P>

<PRE>
     drawable.setGL(new TraceGL(drawable.getGL(), System.err));
</PRE>
*/
public class TraceGL implements GL
{
  public TraceGL(GL downstreamGL, PrintStream stream)
  {
    if (downstreamGL == null) {
      throw new IllegalArgumentException("null downstreamGL");
    }
    this.downstreamGL = downstreamGL;
    this.stream = stream;
  }

  public  void glAccum(int arg0,float arg1)
  {
    printIndent();
    print("glAccum("+arg0+","+arg1+")");
    downstreamGL.glAccum(arg0,arg1);
    println("");
  }
  public  void glActiveStencilFaceEXT(int arg0)
  {
    printIndent();
    print("glActiveStencilFaceEXT("+arg0+")");
    downstreamGL.glActiveStencilFaceEXT(arg0);
    println("");
  }
  public  void glActiveTexture(int arg0)
  {
    printIndent();
    print("glActiveTexture("+arg0+")");
    downstreamGL.glActiveTexture(arg0);
    println("");
  }
  public  void glActiveVaryingNV(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glActiveVaryingNV("+arg0+","+arg1+")");
    downstreamGL.glActiveVaryingNV(arg0,arg1);
    println("");
  }
  public  void glActiveVaryingNV(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glActiveVaryingNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glActiveVaryingNV(arg0,arg1,arg2);
    println("");
  }
  public  void glAlphaFragmentOp1ATI(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print("glAlphaFragmentOp1ATI("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glAlphaFragmentOp1ATI(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glAlphaFragmentOp2ATI(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8)
  {
    printIndent();
    print("glAlphaFragmentOp2ATI("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+")");
    downstreamGL.glAlphaFragmentOp2ATI(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  public  void glAlphaFragmentOp3ATI(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,int arg10,int arg11)
  {
    printIndent();
    print("glAlphaFragmentOp3ATI("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+","+arg11+")");
    downstreamGL.glAlphaFragmentOp3ATI(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11);
    println("");
  }
  public  void glAlphaFunc(int arg0,float arg1)
  {
    printIndent();
    print("glAlphaFunc("+arg0+","+arg1+")");
    downstreamGL.glAlphaFunc(arg0,arg1);
    println("");
  }
  public  void glApplyTextureEXT(int arg0)
  {
    printIndent();
    print("glApplyTextureEXT("+arg0+")");
    downstreamGL.glApplyTextureEXT(arg0);
    println("");
  }
  public  boolean glAreProgramsResidentNV(int arg0,java.nio.IntBuffer arg1,java.nio.ByteBuffer arg2)
  {
    printIndent();
    print("glAreProgramsResidentNV("+arg0+","+arg1+","+arg2+")");
    boolean _res = downstreamGL.glAreProgramsResidentNV(arg0,arg1,arg2);
    println(" = "+_res);
    return _res;
  }
  public  boolean glAreProgramsResidentNV(int arg0,int[] arg1,int arg2,byte[] arg3,int arg4)
  {
    printIndent();
    print("glAreProgramsResidentNV("+arg0+","+dumpArray(arg1)+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    boolean _res = downstreamGL.glAreProgramsResidentNV(arg0,arg1,arg2,arg3,arg4);
    println(" = "+_res);
    return _res;
  }
  public  boolean glAreTexturesResident(int arg0,java.nio.IntBuffer arg1,java.nio.ByteBuffer arg2)
  {
    printIndent();
    print("glAreTexturesResident("+arg0+","+arg1+","+arg2+")");
    boolean _res = downstreamGL.glAreTexturesResident(arg0,arg1,arg2);
    println(" = "+_res);
    return _res;
  }
  public  boolean glAreTexturesResident(int arg0,int[] arg1,int arg2,byte[] arg3,int arg4)
  {
    printIndent();
    print("glAreTexturesResident("+arg0+","+dumpArray(arg1)+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    boolean _res = downstreamGL.glAreTexturesResident(arg0,arg1,arg2,arg3,arg4);
    println(" = "+_res);
    return _res;
  }
  public  void glArrayElement(int arg0)
  {
    printIndent();
    print("glArrayElement("+arg0+")");
    downstreamGL.glArrayElement(arg0);
    println("");
  }
  public  void glArrayObjectATI(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print("glArrayObjectATI("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glArrayObjectATI(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glAsyncMarkerSGIX(int arg0)
  {
    printIndent();
    print("glAsyncMarkerSGIX("+arg0+")");
    downstreamGL.glAsyncMarkerSGIX(arg0);
    println("");
  }
  public  void glAttachObjectARB(int arg0,int arg1)
  {
    printIndent();
    print("glAttachObjectARB("+arg0+","+arg1+")");
    downstreamGL.glAttachObjectARB(arg0,arg1);
    println("");
  }
  public  void glAttachShader(int arg0,int arg1)
  {
    printIndent();
    print("glAttachShader("+arg0+","+arg1+")");
    downstreamGL.glAttachShader(arg0,arg1);
    println("");
  }
  public  void glBegin(int arg0)
  {
    printIndent();
    print("glBegin("+arg0+")");
    downstreamGL.glBegin(arg0);
    println("");
  }
  public  void glBeginFragmentShaderATI()
  {
    printIndent();
    print("glBeginFragmentShaderATI("+")");
    downstreamGL.glBeginFragmentShaderATI();
    println("");
  }
  public  void glBeginOcclusionQueryNV(int arg0)
  {
    printIndent();
    print("glBeginOcclusionQueryNV("+arg0+")");
    downstreamGL.glBeginOcclusionQueryNV(arg0);
    println("");
  }
  public  void glBeginQuery(int arg0,int arg1)
  {
    printIndent();
    print("glBeginQuery("+arg0+","+arg1+")");
    downstreamGL.glBeginQuery(arg0,arg1);
    println("");
  }
  public  void glBeginQueryARB(int arg0,int arg1)
  {
    printIndent();
    print("glBeginQueryARB("+arg0+","+arg1+")");
    downstreamGL.glBeginQueryARB(arg0,arg1);
    println("");
  }
  public  void glBeginTransformFeedbackNV(int arg0)
  {
    printIndent();
    print("glBeginTransformFeedbackNV("+arg0+")");
    downstreamGL.glBeginTransformFeedbackNV(arg0);
    println("");
  }
  public  void glBeginVertexShaderEXT()
  {
    printIndent();
    print("glBeginVertexShaderEXT("+")");
    downstreamGL.glBeginVertexShaderEXT();
    println("");
  }
  public  void glBindAttribLocation(int arg0,int arg1,java.lang.String arg2)
  {
    printIndent();
    print("glBindAttribLocation("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glBindAttribLocation(arg0,arg1,arg2);
    println("");
  }
  public  void glBindAttribLocationARB(int arg0,int arg1,java.lang.String arg2)
  {
    printIndent();
    print("glBindAttribLocationARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glBindAttribLocationARB(arg0,arg1,arg2);
    println("");
  }
  public  void glBindBuffer(int arg0,int arg1)
  {
    printIndent();
    print("glBindBuffer("+arg0+","+arg1+")");
    downstreamGL.glBindBuffer(arg0,arg1);
    println("");
  }
  public  void glBindBufferARB(int arg0,int arg1)
  {
    printIndent();
    print("glBindBufferARB("+arg0+","+arg1+")");
    downstreamGL.glBindBufferARB(arg0,arg1);
    println("");
  }
  public  void glBindBufferBaseNV(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glBindBufferBaseNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glBindBufferBaseNV(arg0,arg1,arg2);
    println("");
  }
  public  void glBindBufferOffsetNV(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glBindBufferOffsetNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glBindBufferOffsetNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glBindBufferRangeNV(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glBindBufferRangeNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glBindBufferRangeNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glBindFragDataLocationEXT(int arg0,int arg1,java.nio.ByteBuffer arg2)
  {
    printIndent();
    print("glBindFragDataLocationEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glBindFragDataLocationEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glBindFragDataLocationEXT(int arg0,int arg1,byte[] arg2,int arg3)
  {
    printIndent();
    print("glBindFragDataLocationEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glBindFragDataLocationEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glBindFragmentShaderATI(int arg0)
  {
    printIndent();
    print("glBindFragmentShaderATI("+arg0+")");
    downstreamGL.glBindFragmentShaderATI(arg0);
    println("");
  }
  public  void glBindFramebufferEXT(int arg0,int arg1)
  {
    printIndent();
    print("glBindFramebufferEXT("+arg0+","+arg1+")");
    downstreamGL.glBindFramebufferEXT(arg0,arg1);
    println("");
  }
  public  int glBindLightParameterEXT(int arg0,int arg1)
  {
    printIndent();
    print("glBindLightParameterEXT("+arg0+","+arg1+")");
    int _res = downstreamGL.glBindLightParameterEXT(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  int glBindMaterialParameterEXT(int arg0,int arg1)
  {
    printIndent();
    print("glBindMaterialParameterEXT("+arg0+","+arg1+")");
    int _res = downstreamGL.glBindMaterialParameterEXT(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  int glBindParameterEXT(int arg0)
  {
    printIndent();
    print("glBindParameterEXT("+arg0+")");
    int _res = downstreamGL.glBindParameterEXT(arg0);
    println(" = "+_res);
    return _res;
  }
  public  void glBindProgramARB(int arg0,int arg1)
  {
    printIndent();
    print("glBindProgramARB("+arg0+","+arg1+")");
    downstreamGL.glBindProgramARB(arg0,arg1);
    println("");
  }
  public  void glBindProgramNV(int arg0,int arg1)
  {
    printIndent();
    print("glBindProgramNV("+arg0+","+arg1+")");
    downstreamGL.glBindProgramNV(arg0,arg1);
    println("");
  }
  public  void glBindRenderbufferEXT(int arg0,int arg1)
  {
    printIndent();
    print("glBindRenderbufferEXT("+arg0+","+arg1+")");
    downstreamGL.glBindRenderbufferEXT(arg0,arg1);
    println("");
  }
  public  int glBindTexGenParameterEXT(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glBindTexGenParameterEXT("+arg0+","+arg1+","+arg2+")");
    int _res = downstreamGL.glBindTexGenParameterEXT(arg0,arg1,arg2);
    println(" = "+_res);
    return _res;
  }
  public  void glBindTexture(int arg0,int arg1)
  {
    printIndent();
    print("glBindTexture("+arg0+","+arg1+")");
    downstreamGL.glBindTexture(arg0,arg1);
    println("");
  }
  public  int glBindTextureUnitParameterEXT(int arg0,int arg1)
  {
    printIndent();
    print("glBindTextureUnitParameterEXT("+arg0+","+arg1+")");
    int _res = downstreamGL.glBindTextureUnitParameterEXT(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  void glBindVertexArrayAPPLE(int arg0)
  {
    printIndent();
    print("glBindVertexArrayAPPLE("+arg0+")");
    downstreamGL.glBindVertexArrayAPPLE(arg0);
    println("");
  }
  public  void glBindVertexShaderEXT(int arg0)
  {
    printIndent();
    print("glBindVertexShaderEXT("+arg0+")");
    downstreamGL.glBindVertexShaderEXT(arg0);
    println("");
  }
  public  void glBitmap(int arg0,int arg1,float arg2,float arg3,float arg4,float arg5,java.nio.ByteBuffer arg6)
  {
    printIndent();
    print("glBitmap("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glBitmap(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glBitmap(int arg0,int arg1,float arg2,float arg3,float arg4,float arg5,byte[] arg6,int arg7)
  {
    printIndent();
    print("glBitmap("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+dumpArray(arg6)+","+arg7+")");
    downstreamGL.glBitmap(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glBitmap(int arg0,int arg1,float arg2,float arg3,float arg4,float arg5,long arg6)
  {
    printIndent();
    print("glBitmap("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glBitmap(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glBlendColor(float arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glBlendColor("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glBlendColor(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glBlendEquation(int arg0)
  {
    printIndent();
    print("glBlendEquation("+arg0+")");
    downstreamGL.glBlendEquation(arg0);
    println("");
  }
  public  void glBlendEquationSeparate(int arg0,int arg1)
  {
    printIndent();
    print("glBlendEquationSeparate("+arg0+","+arg1+")");
    downstreamGL.glBlendEquationSeparate(arg0,arg1);
    println("");
  }
  public  void glBlendEquationSeparateEXT(int arg0,int arg1)
  {
    printIndent();
    print("glBlendEquationSeparateEXT("+arg0+","+arg1+")");
    downstreamGL.glBlendEquationSeparateEXT(arg0,arg1);
    println("");
  }
  public  void glBlendFunc(int arg0,int arg1)
  {
    printIndent();
    print("glBlendFunc("+arg0+","+arg1+")");
    downstreamGL.glBlendFunc(arg0,arg1);
    println("");
  }
  public  void glBlendFuncSeparate(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glBlendFuncSeparate("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glBlendFuncSeparate(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glBlendFuncSeparateEXT(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glBlendFuncSeparateEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glBlendFuncSeparateEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glBlendFuncSeparateINGR(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glBlendFuncSeparateINGR("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glBlendFuncSeparateINGR(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glBlitFramebufferEXT(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9)
  {
    printIndent();
    print("glBlitFramebufferEXT("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+")");
    downstreamGL.glBlitFramebufferEXT(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    println("");
  }
  public  void glBufferData(int arg0,int arg1,java.nio.Buffer arg2,int arg3)
  {
    printIndent();
    print("glBufferData("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glBufferData(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glBufferDataARB(int arg0,int arg1,java.nio.Buffer arg2,int arg3)
  {
    printIndent();
    print("glBufferDataARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glBufferDataARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glBufferParameteriAPPLE(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glBufferParameteriAPPLE("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glBufferParameteriAPPLE(arg0,arg1,arg2);
    println("");
  }
  public  int glBufferRegionEnabled()
  {
    printIndent();
    print("glBufferRegionEnabled("+")");
    int _res = downstreamGL.glBufferRegionEnabled();
    println(" = "+_res);
    return _res;
  }
  public  void glBufferSubData(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print("glBufferSubData("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glBufferSubData(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glBufferSubDataARB(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print("glBufferSubDataARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glBufferSubDataARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glCallList(int arg0)
  {
    printIndent();
    print("glCallList("+arg0+")");
    downstreamGL.glCallList(arg0);
    println("");
  }
  public  void glCallLists(int arg0,int arg1,java.nio.Buffer arg2)
  {
    printIndent();
    print("glCallLists("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glCallLists(arg0,arg1,arg2);
    println("");
  }
  public  int glCheckFramebufferStatusEXT(int arg0)
  {
    printIndent();
    print("glCheckFramebufferStatusEXT("+arg0+")");
    int _res = downstreamGL.glCheckFramebufferStatusEXT(arg0);
    println(" = "+_res);
    return _res;
  }
  public  void glClampColorARB(int arg0,int arg1)
  {
    printIndent();
    print("glClampColorARB("+arg0+","+arg1+")");
    downstreamGL.glClampColorARB(arg0,arg1);
    println("");
  }
  public  void glClear(int arg0)
  {
    printIndent();
    print("glClear("+arg0+")");
    downstreamGL.glClear(arg0);
    println("");
  }
  public  void glClearAccum(float arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glClearAccum("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glClearAccum(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glClearColor(float arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glClearColor("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glClearColor(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glClearColorIiEXT(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glClearColorIiEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glClearColorIiEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glClearColorIuiEXT(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glClearColorIuiEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glClearColorIuiEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glClearDepth(double arg0)
  {
    printIndent();
    print("glClearDepth("+arg0+")");
    downstreamGL.glClearDepth(arg0);
    println("");
  }
  public  void glClearDepthdNV(double arg0)
  {
    printIndent();
    print("glClearDepthdNV("+arg0+")");
    downstreamGL.glClearDepthdNV(arg0);
    println("");
  }
  public  void glClearIndex(float arg0)
  {
    printIndent();
    print("glClearIndex("+arg0+")");
    downstreamGL.glClearIndex(arg0);
    println("");
  }
  public  void glClearStencil(int arg0)
  {
    printIndent();
    print("glClearStencil("+arg0+")");
    downstreamGL.glClearStencil(arg0);
    println("");
  }
  public  void glClientActiveTexture(int arg0)
  {
    printIndent();
    print("glClientActiveTexture("+arg0+")");
    downstreamGL.glClientActiveTexture(arg0);
    println("");
  }
  public  void glClientActiveVertexStreamATI(int arg0)
  {
    printIndent();
    print("glClientActiveVertexStreamATI("+arg0+")");
    downstreamGL.glClientActiveVertexStreamATI(arg0);
    println("");
  }
  public  void glClipPlane(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glClipPlane("+arg0+","+arg1+")");
    downstreamGL.glClipPlane(arg0,arg1);
    println("");
  }
  public  void glClipPlane(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glClipPlane("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glClipPlane(arg0,arg1,arg2);
    println("");
  }
  public  void glColor3b(byte arg0,byte arg1,byte arg2)
  {
    printIndent();
    print("glColor3b("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glColor3b(arg0,arg1,arg2);
    println("");
  }
  public  void glColor3bv(java.nio.ByteBuffer arg0)
  {
    printIndent();
    print("glColor3bv("+arg0+")");
    downstreamGL.glColor3bv(arg0);
    println("");
  }
  public  void glColor3bv(byte[] arg0,int arg1)
  {
    printIndent();
    print("glColor3bv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor3bv(arg0,arg1);
    println("");
  }
  public  void glColor3d(double arg0,double arg1,double arg2)
  {
    printIndent();
    print("glColor3d("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glColor3d(arg0,arg1,arg2);
    println("");
  }
  public  void glColor3dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glColor3dv("+arg0+")");
    downstreamGL.glColor3dv(arg0);
    println("");
  }
  public  void glColor3dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glColor3dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor3dv(arg0,arg1);
    println("");
  }
  public  void glColor3f(float arg0,float arg1,float arg2)
  {
    printIndent();
    print("glColor3f("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glColor3f(arg0,arg1,arg2);
    println("");
  }
  public  void glColor3fVertex3fSUN(float arg0,float arg1,float arg2,float arg3,float arg4,float arg5)
  {
    printIndent();
    print("glColor3fVertex3fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glColor3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glColor3fVertex3fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glColor3fVertex3fvSUN("+arg0+","+arg1+")");
    downstreamGL.glColor3fVertex3fvSUN(arg0,arg1);
    println("");
  }
  public  void glColor3fVertex3fvSUN(float[] arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glColor3fVertex3fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glColor3fVertex3fvSUN(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glColor3fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glColor3fv("+arg0+")");
    downstreamGL.glColor3fv(arg0);
    println("");
  }
  public  void glColor3fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glColor3fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor3fv(arg0,arg1);
    println("");
  }
  public  void glColor3hNV(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glColor3hNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glColor3hNV(arg0,arg1,arg2);
    println("");
  }
  public  void glColor3hvNV(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glColor3hvNV("+arg0+")");
    downstreamGL.glColor3hvNV(arg0);
    println("");
  }
  public  void glColor3hvNV(short[] arg0,int arg1)
  {
    printIndent();
    print("glColor3hvNV("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor3hvNV(arg0,arg1);
    println("");
  }
  public  void glColor3i(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glColor3i("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glColor3i(arg0,arg1,arg2);
    println("");
  }
  public  void glColor3iv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glColor3iv("+arg0+")");
    downstreamGL.glColor3iv(arg0);
    println("");
  }
  public  void glColor3iv(int[] arg0,int arg1)
  {
    printIndent();
    print("glColor3iv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor3iv(arg0,arg1);
    println("");
  }
  public  void glColor3s(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glColor3s("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glColor3s(arg0,arg1,arg2);
    println("");
  }
  public  void glColor3sv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glColor3sv("+arg0+")");
    downstreamGL.glColor3sv(arg0);
    println("");
  }
  public  void glColor3sv(short[] arg0,int arg1)
  {
    printIndent();
    print("glColor3sv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor3sv(arg0,arg1);
    println("");
  }
  public  void glColor3ub(byte arg0,byte arg1,byte arg2)
  {
    printIndent();
    print("glColor3ub("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glColor3ub(arg0,arg1,arg2);
    println("");
  }
  public  void glColor3ubv(java.nio.ByteBuffer arg0)
  {
    printIndent();
    print("glColor3ubv("+arg0+")");
    downstreamGL.glColor3ubv(arg0);
    println("");
  }
  public  void glColor3ubv(byte[] arg0,int arg1)
  {
    printIndent();
    print("glColor3ubv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor3ubv(arg0,arg1);
    println("");
  }
  public  void glColor3ui(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glColor3ui("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glColor3ui(arg0,arg1,arg2);
    println("");
  }
  public  void glColor3uiv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glColor3uiv("+arg0+")");
    downstreamGL.glColor3uiv(arg0);
    println("");
  }
  public  void glColor3uiv(int[] arg0,int arg1)
  {
    printIndent();
    print("glColor3uiv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor3uiv(arg0,arg1);
    println("");
  }
  public  void glColor3us(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glColor3us("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glColor3us(arg0,arg1,arg2);
    println("");
  }
  public  void glColor3usv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glColor3usv("+arg0+")");
    downstreamGL.glColor3usv(arg0);
    println("");
  }
  public  void glColor3usv(short[] arg0,int arg1)
  {
    printIndent();
    print("glColor3usv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor3usv(arg0,arg1);
    println("");
  }
  public  void glColor4b(byte arg0,byte arg1,byte arg2,byte arg3)
  {
    printIndent();
    print("glColor4b("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glColor4b(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glColor4bv(java.nio.ByteBuffer arg0)
  {
    printIndent();
    print("glColor4bv("+arg0+")");
    downstreamGL.glColor4bv(arg0);
    println("");
  }
  public  void glColor4bv(byte[] arg0,int arg1)
  {
    printIndent();
    print("glColor4bv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor4bv(arg0,arg1);
    println("");
  }
  public  void glColor4d(double arg0,double arg1,double arg2,double arg3)
  {
    printIndent();
    print("glColor4d("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glColor4d(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glColor4dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glColor4dv("+arg0+")");
    downstreamGL.glColor4dv(arg0);
    println("");
  }
  public  void glColor4dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glColor4dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor4dv(arg0,arg1);
    println("");
  }
  public  void glColor4f(float arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glColor4f("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glColor4f(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glColor4fNormal3fVertex3fSUN(float arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7,float arg8,float arg9)
  {
    printIndent();
    print("glColor4fNormal3fVertex3fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+")");
    downstreamGL.glColor4fNormal3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    println("");
  }
  public  void glColor4fNormal3fVertex3fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glColor4fNormal3fVertex3fvSUN("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glColor4fNormal3fVertex3fvSUN(arg0,arg1,arg2);
    println("");
  }
  public  void glColor4fNormal3fVertex3fvSUN(float[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5)
  {
    printIndent();
    print("glColor4fNormal3fVertex3fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glColor4fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glColor4fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glColor4fv("+arg0+")");
    downstreamGL.glColor4fv(arg0);
    println("");
  }
  public  void glColor4fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glColor4fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor4fv(arg0,arg1);
    println("");
  }
  public  void glColor4hNV(short arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glColor4hNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glColor4hNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glColor4hvNV(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glColor4hvNV("+arg0+")");
    downstreamGL.glColor4hvNV(arg0);
    println("");
  }
  public  void glColor4hvNV(short[] arg0,int arg1)
  {
    printIndent();
    print("glColor4hvNV("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor4hvNV(arg0,arg1);
    println("");
  }
  public  void glColor4i(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glColor4i("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glColor4i(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glColor4iv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glColor4iv("+arg0+")");
    downstreamGL.glColor4iv(arg0);
    println("");
  }
  public  void glColor4iv(int[] arg0,int arg1)
  {
    printIndent();
    print("glColor4iv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor4iv(arg0,arg1);
    println("");
  }
  public  void glColor4s(short arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glColor4s("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glColor4s(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glColor4sv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glColor4sv("+arg0+")");
    downstreamGL.glColor4sv(arg0);
    println("");
  }
  public  void glColor4sv(short[] arg0,int arg1)
  {
    printIndent();
    print("glColor4sv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor4sv(arg0,arg1);
    println("");
  }
  public  void glColor4ub(byte arg0,byte arg1,byte arg2,byte arg3)
  {
    printIndent();
    print("glColor4ub("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glColor4ub(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glColor4ubVertex2fSUN(byte arg0,byte arg1,byte arg2,byte arg3,float arg4,float arg5)
  {
    printIndent();
    print("glColor4ubVertex2fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glColor4ubVertex2fSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glColor4ubVertex2fvSUN(java.nio.ByteBuffer arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glColor4ubVertex2fvSUN("+arg0+","+arg1+")");
    downstreamGL.glColor4ubVertex2fvSUN(arg0,arg1);
    println("");
  }
  public  void glColor4ubVertex2fvSUN(byte[] arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glColor4ubVertex2fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glColor4ubVertex2fvSUN(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glColor4ubVertex3fSUN(byte arg0,byte arg1,byte arg2,byte arg3,float arg4,float arg5,float arg6)
  {
    printIndent();
    print("glColor4ubVertex3fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glColor4ubVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glColor4ubVertex3fvSUN(java.nio.ByteBuffer arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glColor4ubVertex3fvSUN("+arg0+","+arg1+")");
    downstreamGL.glColor4ubVertex3fvSUN(arg0,arg1);
    println("");
  }
  public  void glColor4ubVertex3fvSUN(byte[] arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glColor4ubVertex3fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glColor4ubVertex3fvSUN(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glColor4ubv(java.nio.ByteBuffer arg0)
  {
    printIndent();
    print("glColor4ubv("+arg0+")");
    downstreamGL.glColor4ubv(arg0);
    println("");
  }
  public  void glColor4ubv(byte[] arg0,int arg1)
  {
    printIndent();
    print("glColor4ubv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor4ubv(arg0,arg1);
    println("");
  }
  public  void glColor4ui(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glColor4ui("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glColor4ui(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glColor4uiv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glColor4uiv("+arg0+")");
    downstreamGL.glColor4uiv(arg0);
    println("");
  }
  public  void glColor4uiv(int[] arg0,int arg1)
  {
    printIndent();
    print("glColor4uiv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor4uiv(arg0,arg1);
    println("");
  }
  public  void glColor4us(short arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glColor4us("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glColor4us(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glColor4usv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glColor4usv("+arg0+")");
    downstreamGL.glColor4usv(arg0);
    println("");
  }
  public  void glColor4usv(short[] arg0,int arg1)
  {
    printIndent();
    print("glColor4usv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glColor4usv(arg0,arg1);
    println("");
  }
  public  void glColorFragmentOp1ATI(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6)
  {
    printIndent();
    print("glColorFragmentOp1ATI("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glColorFragmentOp1ATI(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glColorFragmentOp2ATI(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9)
  {
    printIndent();
    print("glColorFragmentOp2ATI("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+")");
    downstreamGL.glColorFragmentOp2ATI(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    println("");
  }
  public  void glColorFragmentOp3ATI(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,int arg10,int arg11,int arg12)
  {
    printIndent();
    print("glColorFragmentOp3ATI("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+","+arg11+","+arg12+")");
    downstreamGL.glColorFragmentOp3ATI(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12);
    println("");
  }
  public  void glColorMask(boolean arg0,boolean arg1,boolean arg2,boolean arg3)
  {
    printIndent();
    print("glColorMask("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glColorMask(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glColorMaskIndexedEXT(int arg0,boolean arg1,boolean arg2,boolean arg3,boolean arg4)
  {
    printIndent();
    print("glColorMaskIndexedEXT("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glColorMaskIndexedEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glColorMaterial(int arg0,int arg1)
  {
    printIndent();
    print("glColorMaterial("+arg0+","+arg1+")");
    downstreamGL.glColorMaterial(arg0,arg1);
    println("");
  }
  public  void glColorPointer(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print("glColorPointer("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glColorPointer(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glColorPointer(int arg0,int arg1,int arg2,long arg3)
  {
    printIndent();
    print("glColorPointer("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glColorPointer(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glColorSubTable(int arg0,int arg1,int arg2,int arg3,int arg4,java.nio.Buffer arg5)
  {
    printIndent();
    print("glColorSubTable("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glColorSubTable(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glColorSubTable(int arg0,int arg1,int arg2,int arg3,int arg4,long arg5)
  {
    printIndent();
    print("glColorSubTable("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glColorSubTable(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glColorTable(int arg0,int arg1,int arg2,int arg3,int arg4,java.nio.Buffer arg5)
  {
    printIndent();
    print("glColorTable("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glColorTable(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glColorTable(int arg0,int arg1,int arg2,int arg3,int arg4,long arg5)
  {
    printIndent();
    print("glColorTable("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glColorTable(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glColorTableEXT(int arg0,int arg1,int arg2,int arg3,int arg4,java.nio.Buffer arg5)
  {
    printIndent();
    print("glColorTableEXT("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glColorTableEXT(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glColorTableParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glColorTableParameterfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glColorTableParameterfv(arg0,arg1,arg2);
    println("");
  }
  public  void glColorTableParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glColorTableParameterfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glColorTableParameterfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glColorTableParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glColorTableParameteriv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glColorTableParameteriv(arg0,arg1,arg2);
    println("");
  }
  public  void glColorTableParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glColorTableParameteriv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glColorTableParameteriv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glCombinerInputNV(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print("glCombinerInputNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glCombinerInputNV(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glCombinerOutputNV(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,boolean arg7,boolean arg8,boolean arg9)
  {
    printIndent();
    print("glCombinerOutputNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+")");
    downstreamGL.glCombinerOutputNV(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    println("");
  }
  public  void glCombinerParameterfNV(int arg0,float arg1)
  {
    printIndent();
    print("glCombinerParameterfNV("+arg0+","+arg1+")");
    downstreamGL.glCombinerParameterfNV(arg0,arg1);
    println("");
  }
  public  void glCombinerParameterfvNV(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glCombinerParameterfvNV("+arg0+","+arg1+")");
    downstreamGL.glCombinerParameterfvNV(arg0,arg1);
    println("");
  }
  public  void glCombinerParameterfvNV(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glCombinerParameterfvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glCombinerParameterfvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glCombinerParameteriNV(int arg0,int arg1)
  {
    printIndent();
    print("glCombinerParameteriNV("+arg0+","+arg1+")");
    downstreamGL.glCombinerParameteriNV(arg0,arg1);
    println("");
  }
  public  void glCombinerParameterivNV(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glCombinerParameterivNV("+arg0+","+arg1+")");
    downstreamGL.glCombinerParameterivNV(arg0,arg1);
    println("");
  }
  public  void glCombinerParameterivNV(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glCombinerParameterivNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glCombinerParameterivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glCombinerStageParameterfvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glCombinerStageParameterfvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glCombinerStageParameterfvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glCombinerStageParameterfvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glCombinerStageParameterfvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glCombinerStageParameterfvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glCompileShader(int arg0)
  {
    printIndent();
    print("glCompileShader("+arg0+")");
    downstreamGL.glCompileShader(arg0);
    println("");
  }
  public  void glCompileShaderARB(int arg0)
  {
    printIndent();
    print("glCompileShaderARB("+arg0+")");
    downstreamGL.glCompileShaderARB(arg0);
    println("");
  }
  public  void glCompressedTexImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,java.nio.Buffer arg6)
  {
    printIndent();
    print("glCompressedTexImage1D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glCompressedTexImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glCompressedTexImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,long arg6)
  {
    printIndent();
    print("glCompressedTexImage1D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glCompressedTexImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glCompressedTexImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,java.nio.Buffer arg7)
  {
    printIndent();
    print("glCompressedTexImage2D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+")");
    downstreamGL.glCompressedTexImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glCompressedTexImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,long arg7)
  {
    printIndent();
    print("glCompressedTexImage2D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+")");
    downstreamGL.glCompressedTexImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glCompressedTexImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,java.nio.Buffer arg8)
  {
    printIndent();
    print("glCompressedTexImage3D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+")");
    downstreamGL.glCompressedTexImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  public  void glCompressedTexImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,long arg8)
  {
    printIndent();
    print("glCompressedTexImage3D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+")");
    downstreamGL.glCompressedTexImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  public  void glCompressedTexSubImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,java.nio.Buffer arg6)
  {
    printIndent();
    print("glCompressedTexSubImage1D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glCompressedTexSubImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glCompressedTexSubImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,long arg6)
  {
    printIndent();
    print("glCompressedTexSubImage1D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glCompressedTexSubImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glCompressedTexSubImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,java.nio.Buffer arg8)
  {
    printIndent();
    print("glCompressedTexSubImage2D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+")");
    downstreamGL.glCompressedTexSubImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  public  void glCompressedTexSubImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,long arg8)
  {
    printIndent();
    print("glCompressedTexSubImage2D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+")");
    downstreamGL.glCompressedTexSubImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  public  void glCompressedTexSubImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,java.nio.Buffer arg10)
  {
    printIndent();
    print("glCompressedTexSubImage3D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+")");
    downstreamGL.glCompressedTexSubImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  public  void glCompressedTexSubImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,long arg10)
  {
    printIndent();
    print("glCompressedTexSubImage3D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+")");
    downstreamGL.glCompressedTexSubImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  public  void glConvolutionFilter1D(int arg0,int arg1,int arg2,int arg3,int arg4,java.nio.Buffer arg5)
  {
    printIndent();
    print("glConvolutionFilter1D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glConvolutionFilter1D(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glConvolutionFilter1D(int arg0,int arg1,int arg2,int arg3,int arg4,long arg5)
  {
    printIndent();
    print("glConvolutionFilter1D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glConvolutionFilter1D(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glConvolutionFilter2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,java.nio.Buffer arg6)
  {
    printIndent();
    print("glConvolutionFilter2D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glConvolutionFilter2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glConvolutionFilter2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,long arg6)
  {
    printIndent();
    print("glConvolutionFilter2D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glConvolutionFilter2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glConvolutionParameterf(int arg0,int arg1,float arg2)
  {
    printIndent();
    print("glConvolutionParameterf("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glConvolutionParameterf(arg0,arg1,arg2);
    println("");
  }
  public  void glConvolutionParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glConvolutionParameterfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glConvolutionParameterfv(arg0,arg1,arg2);
    println("");
  }
  public  void glConvolutionParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glConvolutionParameterfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glConvolutionParameterfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glConvolutionParameteri(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glConvolutionParameteri("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glConvolutionParameteri(arg0,arg1,arg2);
    println("");
  }
  public  void glConvolutionParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glConvolutionParameteriv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glConvolutionParameteriv(arg0,arg1,arg2);
    println("");
  }
  public  void glConvolutionParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glConvolutionParameteriv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glConvolutionParameteriv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glCopyColorSubTable(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glCopyColorSubTable("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glCopyColorSubTable(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glCopyColorTable(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glCopyColorTable("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glCopyColorTable(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glCopyConvolutionFilter1D(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glCopyConvolutionFilter1D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glCopyConvolutionFilter1D(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glCopyConvolutionFilter2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print("glCopyConvolutionFilter2D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glCopyConvolutionFilter2D(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glCopyPixels(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glCopyPixels("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glCopyPixels(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glCopyTexImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6)
  {
    printIndent();
    print("glCopyTexImage1D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glCopyTexImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glCopyTexImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7)
  {
    printIndent();
    print("glCopyTexImage2D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+")");
    downstreamGL.glCopyTexImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glCopyTexSubImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print("glCopyTexSubImage1D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glCopyTexSubImage1D(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glCopyTexSubImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7)
  {
    printIndent();
    print("glCopyTexSubImage2D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+")");
    downstreamGL.glCopyTexSubImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glCopyTexSubImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8)
  {
    printIndent();
    print("glCopyTexSubImage3D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+")");
    downstreamGL.glCopyTexSubImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  public  int glCreateProgram()
  {
    printIndent();
    print("glCreateProgram("+")");
    int _res = downstreamGL.glCreateProgram();
    println(" = "+_res);
    return _res;
  }
  public  int glCreateProgramObjectARB()
  {
    printIndent();
    print("glCreateProgramObjectARB("+")");
    int _res = downstreamGL.glCreateProgramObjectARB();
    println(" = "+_res);
    return _res;
  }
  public  int glCreateShader(int arg0)
  {
    printIndent();
    print("glCreateShader("+arg0+")");
    int _res = downstreamGL.glCreateShader(arg0);
    println(" = "+_res);
    return _res;
  }
  public  int glCreateShaderObjectARB(int arg0)
  {
    printIndent();
    print("glCreateShaderObjectARB("+arg0+")");
    int _res = downstreamGL.glCreateShaderObjectARB(arg0);
    println(" = "+_res);
    return _res;
  }
  public  void glCullFace(int arg0)
  {
    printIndent();
    print("glCullFace("+arg0+")");
    downstreamGL.glCullFace(arg0);
    println("");
  }
  public  void glCullParameterdvEXT(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glCullParameterdvEXT("+arg0+","+arg1+")");
    downstreamGL.glCullParameterdvEXT(arg0,arg1);
    println("");
  }
  public  void glCullParameterdvEXT(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glCullParameterdvEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glCullParameterdvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glCullParameterfvEXT(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glCullParameterfvEXT("+arg0+","+arg1+")");
    downstreamGL.glCullParameterfvEXT(arg0,arg1);
    println("");
  }
  public  void glCullParameterfvEXT(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glCullParameterfvEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glCullParameterfvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glCurrentPaletteMatrixARB(int arg0)
  {
    printIndent();
    print("glCurrentPaletteMatrixARB("+arg0+")");
    downstreamGL.glCurrentPaletteMatrixARB(arg0);
    println("");
  }
  public  void glDeformSGIX(int arg0)
  {
    printIndent();
    print("glDeformSGIX("+arg0+")");
    downstreamGL.glDeformSGIX(arg0);
    println("");
  }
  public  void glDeformationMap3dSGIX(int arg0,double arg1,double arg2,int arg3,int arg4,double arg5,double arg6,int arg7,int arg8,double arg9,double arg10,int arg11,int arg12,java.nio.DoubleBuffer arg13)
  {
    printIndent();
    print("glDeformationMap3dSGIX("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+","+arg11+","+arg12+","+arg13+")");
    downstreamGL.glDeformationMap3dSGIX(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13);
    println("");
  }
  public  void glDeformationMap3dSGIX(int arg0,double arg1,double arg2,int arg3,int arg4,double arg5,double arg6,int arg7,int arg8,double arg9,double arg10,int arg11,int arg12,double[] arg13,int arg14)
  {
    printIndent();
    print("glDeformationMap3dSGIX("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+","+arg11+","+arg12+","+dumpArray(arg13)+","+arg14+")");
    downstreamGL.glDeformationMap3dSGIX(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14);
    println("");
  }
  public  void glDeformationMap3fSGIX(int arg0,float arg1,float arg2,int arg3,int arg4,float arg5,float arg6,int arg7,int arg8,float arg9,float arg10,int arg11,int arg12,java.nio.FloatBuffer arg13)
  {
    printIndent();
    print("glDeformationMap3fSGIX("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+","+arg11+","+arg12+","+arg13+")");
    downstreamGL.glDeformationMap3fSGIX(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13);
    println("");
  }
  public  void glDeformationMap3fSGIX(int arg0,float arg1,float arg2,int arg3,int arg4,float arg5,float arg6,int arg7,int arg8,float arg9,float arg10,int arg11,int arg12,float[] arg13,int arg14)
  {
    printIndent();
    print("glDeformationMap3fSGIX("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+","+arg11+","+arg12+","+dumpArray(arg13)+","+arg14+")");
    downstreamGL.glDeformationMap3fSGIX(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14);
    println("");
  }
  public  void glDeleteAsyncMarkersSGIX(int arg0,int arg1)
  {
    printIndent();
    print("glDeleteAsyncMarkersSGIX("+arg0+","+arg1+")");
    downstreamGL.glDeleteAsyncMarkersSGIX(arg0,arg1);
    println("");
  }
  public  void glDeleteBufferRegion(int arg0)
  {
    printIndent();
    print("glDeleteBufferRegion("+arg0+")");
    downstreamGL.glDeleteBufferRegion(arg0);
    println("");
  }
  public  void glDeleteBuffers(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glDeleteBuffers("+arg0+","+arg1+")");
    downstreamGL.glDeleteBuffers(arg0,arg1);
    println("");
  }
  public  void glDeleteBuffers(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glDeleteBuffers("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glDeleteBuffers(arg0,arg1,arg2);
    println("");
  }
  public  void glDeleteBuffersARB(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glDeleteBuffersARB("+arg0+","+arg1+")");
    downstreamGL.glDeleteBuffersARB(arg0,arg1);
    println("");
  }
  public  void glDeleteBuffersARB(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glDeleteBuffersARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glDeleteBuffersARB(arg0,arg1,arg2);
    println("");
  }
  public  void glDeleteFencesAPPLE(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glDeleteFencesAPPLE("+arg0+","+arg1+")");
    downstreamGL.glDeleteFencesAPPLE(arg0,arg1);
    println("");
  }
  public  void glDeleteFencesAPPLE(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glDeleteFencesAPPLE("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glDeleteFencesAPPLE(arg0,arg1,arg2);
    println("");
  }
  public  void glDeleteFencesNV(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glDeleteFencesNV("+arg0+","+arg1+")");
    downstreamGL.glDeleteFencesNV(arg0,arg1);
    println("");
  }
  public  void glDeleteFencesNV(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glDeleteFencesNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glDeleteFencesNV(arg0,arg1,arg2);
    println("");
  }
  public  void glDeleteFragmentShaderATI(int arg0)
  {
    printIndent();
    print("glDeleteFragmentShaderATI("+arg0+")");
    downstreamGL.glDeleteFragmentShaderATI(arg0);
    println("");
  }
  public  void glDeleteFramebuffersEXT(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glDeleteFramebuffersEXT("+arg0+","+arg1+")");
    downstreamGL.glDeleteFramebuffersEXT(arg0,arg1);
    println("");
  }
  public  void glDeleteFramebuffersEXT(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glDeleteFramebuffersEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glDeleteFramebuffersEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glDeleteLists(int arg0,int arg1)
  {
    printIndent();
    print("glDeleteLists("+arg0+","+arg1+")");
    downstreamGL.glDeleteLists(arg0,arg1);
    println("");
  }
  public  void glDeleteObjectARB(int arg0)
  {
    printIndent();
    print("glDeleteObjectARB("+arg0+")");
    downstreamGL.glDeleteObjectARB(arg0);
    println("");
  }
  public  void glDeleteOcclusionQueriesNV(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glDeleteOcclusionQueriesNV("+arg0+","+arg1+")");
    downstreamGL.glDeleteOcclusionQueriesNV(arg0,arg1);
    println("");
  }
  public  void glDeleteOcclusionQueriesNV(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glDeleteOcclusionQueriesNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glDeleteOcclusionQueriesNV(arg0,arg1,arg2);
    println("");
  }
  public  void glDeleteProgram(int arg0)
  {
    printIndent();
    print("glDeleteProgram("+arg0+")");
    downstreamGL.glDeleteProgram(arg0);
    println("");
  }
  public  void glDeleteProgramsARB(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glDeleteProgramsARB("+arg0+","+arg1+")");
    downstreamGL.glDeleteProgramsARB(arg0,arg1);
    println("");
  }
  public  void glDeleteProgramsARB(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glDeleteProgramsARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glDeleteProgramsARB(arg0,arg1,arg2);
    println("");
  }
  public  void glDeleteProgramsNV(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glDeleteProgramsNV("+arg0+","+arg1+")");
    downstreamGL.glDeleteProgramsNV(arg0,arg1);
    println("");
  }
  public  void glDeleteProgramsNV(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glDeleteProgramsNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glDeleteProgramsNV(arg0,arg1,arg2);
    println("");
  }
  public  void glDeleteQueries(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glDeleteQueries("+arg0+","+arg1+")");
    downstreamGL.glDeleteQueries(arg0,arg1);
    println("");
  }
  public  void glDeleteQueries(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glDeleteQueries("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glDeleteQueries(arg0,arg1,arg2);
    println("");
  }
  public  void glDeleteQueriesARB(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glDeleteQueriesARB("+arg0+","+arg1+")");
    downstreamGL.glDeleteQueriesARB(arg0,arg1);
    println("");
  }
  public  void glDeleteQueriesARB(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glDeleteQueriesARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glDeleteQueriesARB(arg0,arg1,arg2);
    println("");
  }
  public  void glDeleteRenderbuffersEXT(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glDeleteRenderbuffersEXT("+arg0+","+arg1+")");
    downstreamGL.glDeleteRenderbuffersEXT(arg0,arg1);
    println("");
  }
  public  void glDeleteRenderbuffersEXT(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glDeleteRenderbuffersEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glDeleteRenderbuffersEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glDeleteShader(int arg0)
  {
    printIndent();
    print("glDeleteShader("+arg0+")");
    downstreamGL.glDeleteShader(arg0);
    println("");
  }
  public  void glDeleteTextures(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glDeleteTextures("+arg0+","+arg1+")");
    downstreamGL.glDeleteTextures(arg0,arg1);
    println("");
  }
  public  void glDeleteTextures(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glDeleteTextures("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glDeleteTextures(arg0,arg1,arg2);
    println("");
  }
  public  void glDeleteVertexArraysAPPLE(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glDeleteVertexArraysAPPLE("+arg0+","+arg1+")");
    downstreamGL.glDeleteVertexArraysAPPLE(arg0,arg1);
    println("");
  }
  public  void glDeleteVertexArraysAPPLE(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glDeleteVertexArraysAPPLE("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glDeleteVertexArraysAPPLE(arg0,arg1,arg2);
    println("");
  }
  public  void glDeleteVertexShaderEXT(int arg0)
  {
    printIndent();
    print("glDeleteVertexShaderEXT("+arg0+")");
    downstreamGL.glDeleteVertexShaderEXT(arg0);
    println("");
  }
  public  void glDepthBoundsEXT(double arg0,double arg1)
  {
    printIndent();
    print("glDepthBoundsEXT("+arg0+","+arg1+")");
    downstreamGL.glDepthBoundsEXT(arg0,arg1);
    println("");
  }
  public  void glDepthBoundsdNV(double arg0,double arg1)
  {
    printIndent();
    print("glDepthBoundsdNV("+arg0+","+arg1+")");
    downstreamGL.glDepthBoundsdNV(arg0,arg1);
    println("");
  }
  public  void glDepthFunc(int arg0)
  {
    printIndent();
    print("glDepthFunc("+arg0+")");
    downstreamGL.glDepthFunc(arg0);
    println("");
  }
  public  void glDepthMask(boolean arg0)
  {
    printIndent();
    print("glDepthMask("+arg0+")");
    downstreamGL.glDepthMask(arg0);
    println("");
  }
  public  void glDepthRange(double arg0,double arg1)
  {
    printIndent();
    print("glDepthRange("+arg0+","+arg1+")");
    downstreamGL.glDepthRange(arg0,arg1);
    println("");
  }
  public  void glDepthRangedNV(double arg0,double arg1)
  {
    printIndent();
    print("glDepthRangedNV("+arg0+","+arg1+")");
    downstreamGL.glDepthRangedNV(arg0,arg1);
    println("");
  }
  public  void glDetachObjectARB(int arg0,int arg1)
  {
    printIndent();
    print("glDetachObjectARB("+arg0+","+arg1+")");
    downstreamGL.glDetachObjectARB(arg0,arg1);
    println("");
  }
  public  void glDetachShader(int arg0,int arg1)
  {
    printIndent();
    print("glDetachShader("+arg0+","+arg1+")");
    downstreamGL.glDetachShader(arg0,arg1);
    println("");
  }
  public  void glDetailTexFuncSGIS(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glDetailTexFuncSGIS("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glDetailTexFuncSGIS(arg0,arg1,arg2);
    println("");
  }
  public  void glDetailTexFuncSGIS(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glDetailTexFuncSGIS("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glDetailTexFuncSGIS(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glDisable(int arg0)
  {
    printIndent();
    print("glDisable("+arg0+")");
    downstreamGL.glDisable(arg0);
    println("");
  }
  public  void glDisableClientState(int arg0)
  {
    printIndent();
    print("glDisableClientState("+arg0+")");
    downstreamGL.glDisableClientState(arg0);
    println("");
  }
  public  void glDisableIndexedEXT(int arg0,int arg1)
  {
    printIndent();
    print("glDisableIndexedEXT("+arg0+","+arg1+")");
    downstreamGL.glDisableIndexedEXT(arg0,arg1);
    println("");
  }
  public  void glDisableVariantClientStateEXT(int arg0)
  {
    printIndent();
    print("glDisableVariantClientStateEXT("+arg0+")");
    downstreamGL.glDisableVariantClientStateEXT(arg0);
    println("");
  }
  public  void glDisableVertexAttribAPPLE(int arg0,int arg1)
  {
    printIndent();
    print("glDisableVertexAttribAPPLE("+arg0+","+arg1+")");
    downstreamGL.glDisableVertexAttribAPPLE(arg0,arg1);
    println("");
  }
  public  void glDisableVertexAttribArray(int arg0)
  {
    printIndent();
    print("glDisableVertexAttribArray("+arg0+")");
    downstreamGL.glDisableVertexAttribArray(arg0);
    println("");
  }
  public  void glDisableVertexAttribArrayARB(int arg0)
  {
    printIndent();
    print("glDisableVertexAttribArrayARB("+arg0+")");
    downstreamGL.glDisableVertexAttribArrayARB(arg0);
    println("");
  }
  public  void glDrawArrays(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glDrawArrays("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glDrawArrays(arg0,arg1,arg2);
    println("");
  }
  public  void glDrawArraysInstancedEXT(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glDrawArraysInstancedEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glDrawArraysInstancedEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glDrawBuffer(int arg0)
  {
    printIndent();
    print("glDrawBuffer("+arg0+")");
    downstreamGL.glDrawBuffer(arg0);
    println("");
  }
  public  void glDrawBufferRegion(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6)
  {
    printIndent();
    print("glDrawBufferRegion("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glDrawBufferRegion(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glDrawBuffers(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glDrawBuffers("+arg0+","+arg1+")");
    downstreamGL.glDrawBuffers(arg0,arg1);
    println("");
  }
  public  void glDrawBuffers(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glDrawBuffers("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glDrawBuffers(arg0,arg1,arg2);
    println("");
  }
  public  void glDrawBuffersARB(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glDrawBuffersARB("+arg0+","+arg1+")");
    downstreamGL.glDrawBuffersARB(arg0,arg1);
    println("");
  }
  public  void glDrawBuffersARB(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glDrawBuffersARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glDrawBuffersARB(arg0,arg1,arg2);
    println("");
  }
  public  void glDrawBuffersATI(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glDrawBuffersATI("+arg0+","+arg1+")");
    downstreamGL.glDrawBuffersATI(arg0,arg1);
    println("");
  }
  public  void glDrawBuffersATI(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glDrawBuffersATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glDrawBuffersATI(arg0,arg1,arg2);
    println("");
  }
  public  void glDrawElementArrayAPPLE(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glDrawElementArrayAPPLE("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glDrawElementArrayAPPLE(arg0,arg1,arg2);
    println("");
  }
  public  void glDrawElementArrayATI(int arg0,int arg1)
  {
    printIndent();
    print("glDrawElementArrayATI("+arg0+","+arg1+")");
    downstreamGL.glDrawElementArrayATI(arg0,arg1);
    println("");
  }
  public  void glDrawElements(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print("glDrawElements("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glDrawElements(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glDrawElements(int arg0,int arg1,int arg2,long arg3)
  {
    printIndent();
    print("glDrawElements("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glDrawElements(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glDrawElementsInstancedEXT(int arg0,int arg1,int arg2,java.nio.Buffer arg3,int arg4)
  {
    printIndent();
    print("glDrawElementsInstancedEXT("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glDrawElementsInstancedEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glDrawMeshArraysSUN(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glDrawMeshArraysSUN("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glDrawMeshArraysSUN(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glDrawPixels(int arg0,int arg1,int arg2,int arg3,java.nio.Buffer arg4)
  {
    printIndent();
    print("glDrawPixels("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glDrawPixels(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glDrawPixels(int arg0,int arg1,int arg2,int arg3,long arg4)
  {
    printIndent();
    print("glDrawPixels("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glDrawPixels(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glDrawRangeElementArrayAPPLE(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glDrawRangeElementArrayAPPLE("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glDrawRangeElementArrayAPPLE(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glDrawRangeElementArrayATI(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glDrawRangeElementArrayATI("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glDrawRangeElementArrayATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glDrawRangeElements(int arg0,int arg1,int arg2,int arg3,int arg4,java.nio.Buffer arg5)
  {
    printIndent();
    print("glDrawRangeElements("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glDrawRangeElements(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glDrawRangeElements(int arg0,int arg1,int arg2,int arg3,int arg4,long arg5)
  {
    printIndent();
    print("glDrawRangeElements("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glDrawRangeElements(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glEdgeFlag(boolean arg0)
  {
    printIndent();
    print("glEdgeFlag("+arg0+")");
    downstreamGL.glEdgeFlag(arg0);
    println("");
  }
  public  void glEdgeFlagPointer(int arg0,java.nio.Buffer arg1)
  {
    printIndent();
    print("glEdgeFlagPointer("+arg0+","+arg1+")");
    downstreamGL.glEdgeFlagPointer(arg0,arg1);
    println("");
  }
  public  void glEdgeFlagPointer(int arg0,long arg1)
  {
    printIndent();
    print("glEdgeFlagPointer("+arg0+","+arg1+")");
    downstreamGL.glEdgeFlagPointer(arg0,arg1);
    println("");
  }
  public  void glEdgeFlagv(java.nio.ByteBuffer arg0)
  {
    printIndent();
    print("glEdgeFlagv("+arg0+")");
    downstreamGL.glEdgeFlagv(arg0);
    println("");
  }
  public  void glEdgeFlagv(byte[] arg0,int arg1)
  {
    printIndent();
    print("glEdgeFlagv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glEdgeFlagv(arg0,arg1);
    println("");
  }
  public  void glElementPointerAPPLE(int arg0,java.nio.Buffer arg1)
  {
    printIndent();
    print("glElementPointerAPPLE("+arg0+","+arg1+")");
    downstreamGL.glElementPointerAPPLE(arg0,arg1);
    println("");
  }
  public  void glElementPointerATI(int arg0,java.nio.Buffer arg1)
  {
    printIndent();
    print("glElementPointerATI("+arg0+","+arg1+")");
    downstreamGL.glElementPointerATI(arg0,arg1);
    println("");
  }
  public  void glElementPointerATI(int arg0,long arg1)
  {
    printIndent();
    print("glElementPointerATI("+arg0+","+arg1+")");
    downstreamGL.glElementPointerATI(arg0,arg1);
    println("");
  }
  public  void glEnable(int arg0)
  {
    printIndent();
    print("glEnable("+arg0+")");
    downstreamGL.glEnable(arg0);
    println("");
  }
  public  void glEnableClientState(int arg0)
  {
    printIndent();
    print("glEnableClientState("+arg0+")");
    downstreamGL.glEnableClientState(arg0);
    println("");
  }
  public  void glEnableIndexedEXT(int arg0,int arg1)
  {
    printIndent();
    print("glEnableIndexedEXT("+arg0+","+arg1+")");
    downstreamGL.glEnableIndexedEXT(arg0,arg1);
    println("");
  }
  public  void glEnableVariantClientStateEXT(int arg0)
  {
    printIndent();
    print("glEnableVariantClientStateEXT("+arg0+")");
    downstreamGL.glEnableVariantClientStateEXT(arg0);
    println("");
  }
  public  void glEnableVertexAttribAPPLE(int arg0,int arg1)
  {
    printIndent();
    print("glEnableVertexAttribAPPLE("+arg0+","+arg1+")");
    downstreamGL.glEnableVertexAttribAPPLE(arg0,arg1);
    println("");
  }
  public  void glEnableVertexAttribArray(int arg0)
  {
    printIndent();
    print("glEnableVertexAttribArray("+arg0+")");
    downstreamGL.glEnableVertexAttribArray(arg0);
    println("");
  }
  public  void glEnableVertexAttribArrayARB(int arg0)
  {
    printIndent();
    print("glEnableVertexAttribArrayARB("+arg0+")");
    downstreamGL.glEnableVertexAttribArrayARB(arg0);
    println("");
  }
  public  void glEnd()
  {
    indent-=2;
    printIndent();
    print("glEnd("+")");
    downstreamGL.glEnd();
    println("");
  }
  public  void glEndFragmentShaderATI()
  {
    printIndent();
    print("glEndFragmentShaderATI("+")");
    downstreamGL.glEndFragmentShaderATI();
    println("");
  }
  public  void glEndList()
  {
    indent-=2;
    printIndent();
    print("glEndList("+")");
    downstreamGL.glEndList();
    println("");
  }
  public  void glEndOcclusionQueryNV()
  {
    printIndent();
    print("glEndOcclusionQueryNV("+")");
    downstreamGL.glEndOcclusionQueryNV();
    println("");
  }
  public  void glEndQuery(int arg0)
  {
    printIndent();
    print("glEndQuery("+arg0+")");
    downstreamGL.glEndQuery(arg0);
    println("");
  }
  public  void glEndQueryARB(int arg0)
  {
    printIndent();
    print("glEndQueryARB("+arg0+")");
    downstreamGL.glEndQueryARB(arg0);
    println("");
  }
  public  void glEndTransformFeedbackNV()
  {
    printIndent();
    print("glEndTransformFeedbackNV("+")");
    downstreamGL.glEndTransformFeedbackNV();
    println("");
  }
  public  void glEndVertexShaderEXT()
  {
    printIndent();
    print("glEndVertexShaderEXT("+")");
    downstreamGL.glEndVertexShaderEXT();
    println("");
  }
  public  void glEvalCoord1d(double arg0)
  {
    printIndent();
    print("glEvalCoord1d("+arg0+")");
    downstreamGL.glEvalCoord1d(arg0);
    println("");
  }
  public  void glEvalCoord1dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glEvalCoord1dv("+arg0+")");
    downstreamGL.glEvalCoord1dv(arg0);
    println("");
  }
  public  void glEvalCoord1dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glEvalCoord1dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glEvalCoord1dv(arg0,arg1);
    println("");
  }
  public  void glEvalCoord1f(float arg0)
  {
    printIndent();
    print("glEvalCoord1f("+arg0+")");
    downstreamGL.glEvalCoord1f(arg0);
    println("");
  }
  public  void glEvalCoord1fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glEvalCoord1fv("+arg0+")");
    downstreamGL.glEvalCoord1fv(arg0);
    println("");
  }
  public  void glEvalCoord1fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glEvalCoord1fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glEvalCoord1fv(arg0,arg1);
    println("");
  }
  public  void glEvalCoord2d(double arg0,double arg1)
  {
    printIndent();
    print("glEvalCoord2d("+arg0+","+arg1+")");
    downstreamGL.glEvalCoord2d(arg0,arg1);
    println("");
  }
  public  void glEvalCoord2dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glEvalCoord2dv("+arg0+")");
    downstreamGL.glEvalCoord2dv(arg0);
    println("");
  }
  public  void glEvalCoord2dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glEvalCoord2dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glEvalCoord2dv(arg0,arg1);
    println("");
  }
  public  void glEvalCoord2f(float arg0,float arg1)
  {
    printIndent();
    print("glEvalCoord2f("+arg0+","+arg1+")");
    downstreamGL.glEvalCoord2f(arg0,arg1);
    println("");
  }
  public  void glEvalCoord2fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glEvalCoord2fv("+arg0+")");
    downstreamGL.glEvalCoord2fv(arg0);
    println("");
  }
  public  void glEvalCoord2fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glEvalCoord2fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glEvalCoord2fv(arg0,arg1);
    println("");
  }
  public  void glEvalMapsNV(int arg0,int arg1)
  {
    printIndent();
    print("glEvalMapsNV("+arg0+","+arg1+")");
    downstreamGL.glEvalMapsNV(arg0,arg1);
    println("");
  }
  public  void glEvalMesh1(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glEvalMesh1("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glEvalMesh1(arg0,arg1,arg2);
    println("");
  }
  public  void glEvalMesh2(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glEvalMesh2("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glEvalMesh2(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glEvalPoint1(int arg0)
  {
    printIndent();
    print("glEvalPoint1("+arg0+")");
    downstreamGL.glEvalPoint1(arg0);
    println("");
  }
  public  void glEvalPoint2(int arg0,int arg1)
  {
    printIndent();
    print("glEvalPoint2("+arg0+","+arg1+")");
    downstreamGL.glEvalPoint2(arg0,arg1);
    println("");
  }
  public  void glExecuteProgramNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glExecuteProgramNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glExecuteProgramNV(arg0,arg1,arg2);
    println("");
  }
  public  void glExecuteProgramNV(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glExecuteProgramNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glExecuteProgramNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glExtractComponentEXT(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glExtractComponentEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glExtractComponentEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glFeedbackBuffer(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glFeedbackBuffer("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glFeedbackBuffer(arg0,arg1,arg2);
    println("");
  }
  public  void glFinalCombinerInputNV(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glFinalCombinerInputNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glFinalCombinerInputNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glFinish()
  {
    printIndent();
    print("glFinish("+")");
    downstreamGL.glFinish();
    println("");
  }
  public  int glFinishAsyncSGIX(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glFinishAsyncSGIX("+arg0+")");
    int _res = downstreamGL.glFinishAsyncSGIX(arg0);
    println(" = "+_res);
    return _res;
  }
  public  int glFinishAsyncSGIX(int[] arg0,int arg1)
  {
    printIndent();
    print("glFinishAsyncSGIX("+dumpArray(arg0)+","+arg1+")");
    int _res = downstreamGL.glFinishAsyncSGIX(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  void glFinishFenceAPPLE(int arg0)
  {
    printIndent();
    print("glFinishFenceAPPLE("+arg0+")");
    downstreamGL.glFinishFenceAPPLE(arg0);
    println("");
  }
  public  void glFinishFenceNV(int arg0)
  {
    printIndent();
    print("glFinishFenceNV("+arg0+")");
    downstreamGL.glFinishFenceNV(arg0);
    println("");
  }
  public  void glFinishObjectAPPLE(int arg0,int arg1)
  {
    printIndent();
    print("glFinishObjectAPPLE("+arg0+","+arg1+")");
    downstreamGL.glFinishObjectAPPLE(arg0,arg1);
    println("");
  }
  public  void glFinishRenderAPPLE()
  {
    printIndent();
    print("glFinishRenderAPPLE("+")");
    downstreamGL.glFinishRenderAPPLE();
    println("");
  }
  public  void glFinishTextureSUNX()
  {
    printIndent();
    print("glFinishTextureSUNX("+")");
    downstreamGL.glFinishTextureSUNX();
    println("");
  }
  public  void glFlush()
  {
    printIndent();
    print("glFlush("+")");
    downstreamGL.glFlush();
    println("");
  }
  public  void glFlushMappedBufferRangeAPPLE(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glFlushMappedBufferRangeAPPLE("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glFlushMappedBufferRangeAPPLE(arg0,arg1,arg2);
    println("");
  }
  public  void glFlushPixelDataRangeNV(int arg0)
  {
    printIndent();
    print("glFlushPixelDataRangeNV("+arg0+")");
    downstreamGL.glFlushPixelDataRangeNV(arg0);
    println("");
  }
  public  void glFlushRasterSGIX()
  {
    printIndent();
    print("glFlushRasterSGIX("+")");
    downstreamGL.glFlushRasterSGIX();
    println("");
  }
  public  void glFlushRenderAPPLE()
  {
    printIndent();
    print("glFlushRenderAPPLE("+")");
    downstreamGL.glFlushRenderAPPLE();
    println("");
  }
  public  void glFlushVertexArrayRangeAPPLE(int arg0,java.nio.Buffer arg1)
  {
    printIndent();
    print("glFlushVertexArrayRangeAPPLE("+arg0+","+arg1+")");
    downstreamGL.glFlushVertexArrayRangeAPPLE(arg0,arg1);
    println("");
  }
  public  void glFlushVertexArrayRangeNV()
  {
    printIndent();
    print("glFlushVertexArrayRangeNV("+")");
    downstreamGL.glFlushVertexArrayRangeNV();
    println("");
  }
  public  void glFogCoordPointer(int arg0,int arg1,java.nio.Buffer arg2)
  {
    printIndent();
    print("glFogCoordPointer("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glFogCoordPointer(arg0,arg1,arg2);
    println("");
  }
  public  void glFogCoordPointer(int arg0,int arg1,long arg2)
  {
    printIndent();
    print("glFogCoordPointer("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glFogCoordPointer(arg0,arg1,arg2);
    println("");
  }
  public  void glFogCoordPointerEXT(int arg0,int arg1,java.nio.Buffer arg2)
  {
    printIndent();
    print("glFogCoordPointerEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glFogCoordPointerEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glFogCoordPointerEXT(int arg0,int arg1,long arg2)
  {
    printIndent();
    print("glFogCoordPointerEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glFogCoordPointerEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glFogCoordd(double arg0)
  {
    printIndent();
    print("glFogCoordd("+arg0+")");
    downstreamGL.glFogCoordd(arg0);
    println("");
  }
  public  void glFogCoorddEXT(double arg0)
  {
    printIndent();
    print("glFogCoorddEXT("+arg0+")");
    downstreamGL.glFogCoorddEXT(arg0);
    println("");
  }
  public  void glFogCoorddv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glFogCoorddv("+arg0+")");
    downstreamGL.glFogCoorddv(arg0);
    println("");
  }
  public  void glFogCoorddv(double[] arg0,int arg1)
  {
    printIndent();
    print("glFogCoorddv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glFogCoorddv(arg0,arg1);
    println("");
  }
  public  void glFogCoorddvEXT(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glFogCoorddvEXT("+arg0+")");
    downstreamGL.glFogCoorddvEXT(arg0);
    println("");
  }
  public  void glFogCoorddvEXT(double[] arg0,int arg1)
  {
    printIndent();
    print("glFogCoorddvEXT("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glFogCoorddvEXT(arg0,arg1);
    println("");
  }
  public  void glFogCoordf(float arg0)
  {
    printIndent();
    print("glFogCoordf("+arg0+")");
    downstreamGL.glFogCoordf(arg0);
    println("");
  }
  public  void glFogCoordfEXT(float arg0)
  {
    printIndent();
    print("glFogCoordfEXT("+arg0+")");
    downstreamGL.glFogCoordfEXT(arg0);
    println("");
  }
  public  void glFogCoordfv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glFogCoordfv("+arg0+")");
    downstreamGL.glFogCoordfv(arg0);
    println("");
  }
  public  void glFogCoordfv(float[] arg0,int arg1)
  {
    printIndent();
    print("glFogCoordfv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glFogCoordfv(arg0,arg1);
    println("");
  }
  public  void glFogCoordfvEXT(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glFogCoordfvEXT("+arg0+")");
    downstreamGL.glFogCoordfvEXT(arg0);
    println("");
  }
  public  void glFogCoordfvEXT(float[] arg0,int arg1)
  {
    printIndent();
    print("glFogCoordfvEXT("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glFogCoordfvEXT(arg0,arg1);
    println("");
  }
  public  void glFogCoordhNV(short arg0)
  {
    printIndent();
    print("glFogCoordhNV("+arg0+")");
    downstreamGL.glFogCoordhNV(arg0);
    println("");
  }
  public  void glFogCoordhvNV(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glFogCoordhvNV("+arg0+")");
    downstreamGL.glFogCoordhvNV(arg0);
    println("");
  }
  public  void glFogCoordhvNV(short[] arg0,int arg1)
  {
    printIndent();
    print("glFogCoordhvNV("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glFogCoordhvNV(arg0,arg1);
    println("");
  }
  public  void glFogFuncSGIS(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glFogFuncSGIS("+arg0+","+arg1+")");
    downstreamGL.glFogFuncSGIS(arg0,arg1);
    println("");
  }
  public  void glFogFuncSGIS(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glFogFuncSGIS("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glFogFuncSGIS(arg0,arg1,arg2);
    println("");
  }
  public  void glFogf(int arg0,float arg1)
  {
    printIndent();
    print("glFogf("+arg0+","+arg1+")");
    downstreamGL.glFogf(arg0,arg1);
    println("");
  }
  public  void glFogfv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glFogfv("+arg0+","+arg1+")");
    downstreamGL.glFogfv(arg0,arg1);
    println("");
  }
  public  void glFogfv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glFogfv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glFogfv(arg0,arg1,arg2);
    println("");
  }
  public  void glFogi(int arg0,int arg1)
  {
    printIndent();
    print("glFogi("+arg0+","+arg1+")");
    downstreamGL.glFogi(arg0,arg1);
    println("");
  }
  public  void glFogiv(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glFogiv("+arg0+","+arg1+")");
    downstreamGL.glFogiv(arg0,arg1);
    println("");
  }
  public  void glFogiv(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glFogiv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glFogiv(arg0,arg1,arg2);
    println("");
  }
  public  void glFragmentColorMaterialSGIX(int arg0,int arg1)
  {
    printIndent();
    print("glFragmentColorMaterialSGIX("+arg0+","+arg1+")");
    downstreamGL.glFragmentColorMaterialSGIX(arg0,arg1);
    println("");
  }
  public  void glFragmentLightModelfSGIX(int arg0,float arg1)
  {
    printIndent();
    print("glFragmentLightModelfSGIX("+arg0+","+arg1+")");
    downstreamGL.glFragmentLightModelfSGIX(arg0,arg1);
    println("");
  }
  public  void glFragmentLightModelfvSGIX(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glFragmentLightModelfvSGIX("+arg0+","+arg1+")");
    downstreamGL.glFragmentLightModelfvSGIX(arg0,arg1);
    println("");
  }
  public  void glFragmentLightModelfvSGIX(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glFragmentLightModelfvSGIX("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glFragmentLightModelfvSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glFragmentLightModeliSGIX(int arg0,int arg1)
  {
    printIndent();
    print("glFragmentLightModeliSGIX("+arg0+","+arg1+")");
    downstreamGL.glFragmentLightModeliSGIX(arg0,arg1);
    println("");
  }
  public  void glFragmentLightModelivSGIX(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glFragmentLightModelivSGIX("+arg0+","+arg1+")");
    downstreamGL.glFragmentLightModelivSGIX(arg0,arg1);
    println("");
  }
  public  void glFragmentLightModelivSGIX(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glFragmentLightModelivSGIX("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glFragmentLightModelivSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glFragmentLightfSGIX(int arg0,int arg1,float arg2)
  {
    printIndent();
    print("glFragmentLightfSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glFragmentLightfSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glFragmentLightfvSGIX(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glFragmentLightfvSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glFragmentLightfvSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glFragmentLightfvSGIX(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glFragmentLightfvSGIX("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glFragmentLightfvSGIX(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glFragmentLightiSGIX(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glFragmentLightiSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glFragmentLightiSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glFragmentLightivSGIX(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glFragmentLightivSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glFragmentLightivSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glFragmentLightivSGIX(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glFragmentLightivSGIX("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glFragmentLightivSGIX(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glFragmentMaterialfSGIX(int arg0,int arg1,float arg2)
  {
    printIndent();
    print("glFragmentMaterialfSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glFragmentMaterialfSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glFragmentMaterialfvSGIX(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glFragmentMaterialfvSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glFragmentMaterialfvSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glFragmentMaterialfvSGIX(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glFragmentMaterialfvSGIX("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glFragmentMaterialfvSGIX(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glFragmentMaterialiSGIX(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glFragmentMaterialiSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glFragmentMaterialiSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glFragmentMaterialivSGIX(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glFragmentMaterialivSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glFragmentMaterialivSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glFragmentMaterialivSGIX(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glFragmentMaterialivSGIX("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glFragmentMaterialivSGIX(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glFrameZoomSGIX(int arg0)
  {
    printIndent();
    print("glFrameZoomSGIX("+arg0+")");
    downstreamGL.glFrameZoomSGIX(arg0);
    println("");
  }
  public  void glFramebufferRenderbufferEXT(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glFramebufferRenderbufferEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glFramebufferRenderbufferEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glFramebufferTexture1DEXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glFramebufferTexture1DEXT("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glFramebufferTexture1DEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glFramebufferTexture2DEXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glFramebufferTexture2DEXT("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glFramebufferTexture2DEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glFramebufferTexture3DEXT(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print("glFramebufferTexture3DEXT("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glFramebufferTexture3DEXT(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glFramebufferTextureEXT(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glFramebufferTextureEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glFramebufferTextureEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glFramebufferTextureFaceEXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glFramebufferTextureFaceEXT("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glFramebufferTextureFaceEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glFramebufferTextureLayerEXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glFramebufferTextureLayerEXT("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glFramebufferTextureLayerEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glFreeObjectBufferATI(int arg0)
  {
    printIndent();
    print("glFreeObjectBufferATI("+arg0+")");
    downstreamGL.glFreeObjectBufferATI(arg0);
    println("");
  }
  public  void glFrontFace(int arg0)
  {
    printIndent();
    print("glFrontFace("+arg0+")");
    downstreamGL.glFrontFace(arg0);
    println("");
  }
  public  void glFrustum(double arg0,double arg1,double arg2,double arg3,double arg4,double arg5)
  {
    printIndent();
    print("glFrustum("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glFrustum(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  int glGenAsyncMarkersSGIX(int arg0)
  {
    printIndent();
    print("glGenAsyncMarkersSGIX("+arg0+")");
    int _res = downstreamGL.glGenAsyncMarkersSGIX(arg0);
    println(" = "+_res);
    return _res;
  }
  public  void glGenBuffers(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glGenBuffers("+arg0+","+arg1+")");
    downstreamGL.glGenBuffers(arg0,arg1);
    println("");
  }
  public  void glGenBuffers(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glGenBuffers("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGenBuffers(arg0,arg1,arg2);
    println("");
  }
  public  void glGenBuffersARB(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glGenBuffersARB("+arg0+","+arg1+")");
    downstreamGL.glGenBuffersARB(arg0,arg1);
    println("");
  }
  public  void glGenBuffersARB(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glGenBuffersARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGenBuffersARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGenFencesAPPLE(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glGenFencesAPPLE("+arg0+","+arg1+")");
    downstreamGL.glGenFencesAPPLE(arg0,arg1);
    println("");
  }
  public  void glGenFencesAPPLE(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glGenFencesAPPLE("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGenFencesAPPLE(arg0,arg1,arg2);
    println("");
  }
  public  void glGenFencesNV(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glGenFencesNV("+arg0+","+arg1+")");
    downstreamGL.glGenFencesNV(arg0,arg1);
    println("");
  }
  public  void glGenFencesNV(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glGenFencesNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGenFencesNV(arg0,arg1,arg2);
    println("");
  }
  public  int glGenFragmentShadersATI(int arg0)
  {
    printIndent();
    print("glGenFragmentShadersATI("+arg0+")");
    int _res = downstreamGL.glGenFragmentShadersATI(arg0);
    println(" = "+_res);
    return _res;
  }
  public  void glGenFramebuffersEXT(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glGenFramebuffersEXT("+arg0+","+arg1+")");
    downstreamGL.glGenFramebuffersEXT(arg0,arg1);
    println("");
  }
  public  void glGenFramebuffersEXT(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glGenFramebuffersEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGenFramebuffersEXT(arg0,arg1,arg2);
    println("");
  }
  public  int glGenLists(int arg0)
  {
    printIndent();
    print("glGenLists("+arg0+")");
    int _res = downstreamGL.glGenLists(arg0);
    println(" = "+_res);
    return _res;
  }
  public  void glGenOcclusionQueriesNV(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glGenOcclusionQueriesNV("+arg0+","+arg1+")");
    downstreamGL.glGenOcclusionQueriesNV(arg0,arg1);
    println("");
  }
  public  void glGenOcclusionQueriesNV(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glGenOcclusionQueriesNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGenOcclusionQueriesNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGenProgramsARB(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glGenProgramsARB("+arg0+","+arg1+")");
    downstreamGL.glGenProgramsARB(arg0,arg1);
    println("");
  }
  public  void glGenProgramsARB(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glGenProgramsARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGenProgramsARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGenProgramsNV(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glGenProgramsNV("+arg0+","+arg1+")");
    downstreamGL.glGenProgramsNV(arg0,arg1);
    println("");
  }
  public  void glGenProgramsNV(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glGenProgramsNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGenProgramsNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGenQueries(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glGenQueries("+arg0+","+arg1+")");
    downstreamGL.glGenQueries(arg0,arg1);
    println("");
  }
  public  void glGenQueries(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glGenQueries("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGenQueries(arg0,arg1,arg2);
    println("");
  }
  public  void glGenQueriesARB(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glGenQueriesARB("+arg0+","+arg1+")");
    downstreamGL.glGenQueriesARB(arg0,arg1);
    println("");
  }
  public  void glGenQueriesARB(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glGenQueriesARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGenQueriesARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGenRenderbuffersEXT(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glGenRenderbuffersEXT("+arg0+","+arg1+")");
    downstreamGL.glGenRenderbuffersEXT(arg0,arg1);
    println("");
  }
  public  void glGenRenderbuffersEXT(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glGenRenderbuffersEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGenRenderbuffersEXT(arg0,arg1,arg2);
    println("");
  }
  public  int glGenSymbolsEXT(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glGenSymbolsEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    int _res = downstreamGL.glGenSymbolsEXT(arg0,arg1,arg2,arg3);
    println(" = "+_res);
    return _res;
  }
  public  void glGenTextures(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glGenTextures("+arg0+","+arg1+")");
    downstreamGL.glGenTextures(arg0,arg1);
    println("");
  }
  public  void glGenTextures(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glGenTextures("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGenTextures(arg0,arg1,arg2);
    println("");
  }
  public  void glGenVertexArraysAPPLE(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glGenVertexArraysAPPLE("+arg0+","+arg1+")");
    downstreamGL.glGenVertexArraysAPPLE(arg0,arg1);
    println("");
  }
  public  void glGenVertexArraysAPPLE(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glGenVertexArraysAPPLE("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGenVertexArraysAPPLE(arg0,arg1,arg2);
    println("");
  }
  public  int glGenVertexShadersEXT(int arg0)
  {
    printIndent();
    print("glGenVertexShadersEXT("+arg0+")");
    int _res = downstreamGL.glGenVertexShadersEXT(arg0);
    println(" = "+_res);
    return _res;
  }
  public  void glGenerateMipmapEXT(int arg0)
  {
    printIndent();
    print("glGenerateMipmapEXT("+arg0+")");
    downstreamGL.glGenerateMipmapEXT(arg0);
    println("");
  }
  public  void glGetActiveAttrib(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4,java.nio.IntBuffer arg5,java.nio.ByteBuffer arg6)
  {
    printIndent();
    print("glGetActiveAttrib("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glGetActiveAttrib(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glGetActiveAttrib(int arg0,int arg1,int arg2,int[] arg3,int arg4,int[] arg5,int arg6,int[] arg7,int arg8,byte[] arg9,int arg10)
  {
    printIndent();
    print("glGetActiveAttrib("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+","+dumpArray(arg5)+","+arg6+","+dumpArray(arg7)+","+arg8+","+dumpArray(arg9)+","+arg10+")");
    downstreamGL.glGetActiveAttrib(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  public  void glGetActiveAttribARB(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4,java.nio.IntBuffer arg5,java.nio.ByteBuffer arg6)
  {
    printIndent();
    print("glGetActiveAttribARB("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glGetActiveAttribARB(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glGetActiveAttribARB(int arg0,int arg1,int arg2,int[] arg3,int arg4,int[] arg5,int arg6,int[] arg7,int arg8,byte[] arg9,int arg10)
  {
    printIndent();
    print("glGetActiveAttribARB("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+","+dumpArray(arg5)+","+arg6+","+dumpArray(arg7)+","+arg8+","+dumpArray(arg9)+","+arg10+")");
    downstreamGL.glGetActiveAttribARB(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  public  void glGetActiveUniform(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4,java.nio.IntBuffer arg5,java.nio.ByteBuffer arg6)
  {
    printIndent();
    print("glGetActiveUniform("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glGetActiveUniform(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glGetActiveUniform(int arg0,int arg1,int arg2,int[] arg3,int arg4,int[] arg5,int arg6,int[] arg7,int arg8,byte[] arg9,int arg10)
  {
    printIndent();
    print("glGetActiveUniform("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+","+dumpArray(arg5)+","+arg6+","+dumpArray(arg7)+","+arg8+","+dumpArray(arg9)+","+arg10+")");
    downstreamGL.glGetActiveUniform(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  public  void glGetActiveUniformARB(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4,java.nio.IntBuffer arg5,java.nio.ByteBuffer arg6)
  {
    printIndent();
    print("glGetActiveUniformARB("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glGetActiveUniformARB(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glGetActiveUniformARB(int arg0,int arg1,int arg2,int[] arg3,int arg4,int[] arg5,int arg6,int[] arg7,int arg8,byte[] arg9,int arg10)
  {
    printIndent();
    print("glGetActiveUniformARB("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+","+dumpArray(arg5)+","+arg6+","+dumpArray(arg7)+","+arg8+","+dumpArray(arg9)+","+arg10+")");
    downstreamGL.glGetActiveUniformARB(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  public  void glGetActiveVaryingNV(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4,java.nio.IntBuffer arg5,java.nio.ByteBuffer arg6)
  {
    printIndent();
    print("glGetActiveVaryingNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glGetActiveVaryingNV(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glGetActiveVaryingNV(int arg0,int arg1,int arg2,int[] arg3,int arg4,int[] arg5,int arg6,int[] arg7,int arg8,byte[] arg9,int arg10)
  {
    printIndent();
    print("glGetActiveVaryingNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+","+dumpArray(arg5)+","+arg6+","+dumpArray(arg7)+","+arg8+","+dumpArray(arg9)+","+arg10+")");
    downstreamGL.glGetActiveVaryingNV(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  public  void glGetArrayObjectfvATI(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetArrayObjectfvATI("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetArrayObjectfvATI(arg0,arg1,arg2);
    println("");
  }
  public  void glGetArrayObjectfvATI(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetArrayObjectfvATI("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetArrayObjectfvATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetArrayObjectivATI(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetArrayObjectivATI("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetArrayObjectivATI(arg0,arg1,arg2);
    println("");
  }
  public  void glGetArrayObjectivATI(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetArrayObjectivATI("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetArrayObjectivATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetAttachedObjectsARB(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print("glGetAttachedObjectsARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetAttachedObjectsARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetAttachedObjectsARB(int arg0,int arg1,int[] arg2,int arg3,int[] arg4,int arg5)
  {
    printIndent();
    print("glGetAttachedObjectsARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glGetAttachedObjectsARB(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glGetAttachedShaders(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print("glGetAttachedShaders("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetAttachedShaders(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetAttachedShaders(int arg0,int arg1,int[] arg2,int arg3,int[] arg4,int arg5)
  {
    printIndent();
    print("glGetAttachedShaders("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glGetAttachedShaders(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  int glGetAttribLocation(int arg0,java.lang.String arg1)
  {
    printIndent();
    print("glGetAttribLocation("+arg0+","+arg1+")");
    int _res = downstreamGL.glGetAttribLocation(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  int glGetAttribLocationARB(int arg0,java.lang.String arg1)
  {
    printIndent();
    print("glGetAttribLocationARB("+arg0+","+arg1+")");
    int _res = downstreamGL.glGetAttribLocationARB(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  void glGetBooleanIndexedvEXT(int arg0,int arg1,java.nio.ByteBuffer arg2)
  {
    printIndent();
    print("glGetBooleanIndexedvEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetBooleanIndexedvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetBooleanIndexedvEXT(int arg0,int arg1,byte[] arg2,int arg3)
  {
    printIndent();
    print("glGetBooleanIndexedvEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetBooleanIndexedvEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetBooleanv(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glGetBooleanv("+arg0+","+arg1+")");
    downstreamGL.glGetBooleanv(arg0,arg1);
    println("");
  }
  public  void glGetBooleanv(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glGetBooleanv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGetBooleanv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetBufferParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetBufferParameteriv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetBufferParameteriv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetBufferParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetBufferParameteriv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetBufferParameteriv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetBufferParameterivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetBufferParameterivARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetBufferParameterivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGetBufferParameterivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetBufferParameterivARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetBufferParameterivARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetBufferSubData(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print("glGetBufferSubData("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetBufferSubData(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetBufferSubDataARB(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print("glGetBufferSubDataARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetBufferSubDataARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetClipPlane(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glGetClipPlane("+arg0+","+arg1+")");
    downstreamGL.glGetClipPlane(arg0,arg1);
    println("");
  }
  public  void glGetClipPlane(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glGetClipPlane("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGetClipPlane(arg0,arg1,arg2);
    println("");
  }
  public  void glGetColorTable(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print("glGetColorTable("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetColorTable(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetColorTable(int arg0,int arg1,int arg2,long arg3)
  {
    printIndent();
    print("glGetColorTable("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetColorTable(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetColorTableEXT(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print("glGetColorTableEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetColorTableEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetColorTableParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetColorTableParameterfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetColorTableParameterfv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetColorTableParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetColorTableParameterfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetColorTableParameterfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetColorTableParameterfvEXT(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetColorTableParameterfvEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetColorTableParameterfvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetColorTableParameterfvEXT(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetColorTableParameterfvEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetColorTableParameterfvEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetColorTableParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetColorTableParameteriv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetColorTableParameteriv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetColorTableParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetColorTableParameteriv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetColorTableParameteriv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetColorTableParameterivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetColorTableParameterivEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetColorTableParameterivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetColorTableParameterivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetColorTableParameterivEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetColorTableParameterivEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetCombinerInputParameterfvNV(int arg0,int arg1,int arg2,int arg3,java.nio.FloatBuffer arg4)
  {
    printIndent();
    print("glGetCombinerInputParameterfvNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glGetCombinerInputParameterfvNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetCombinerInputParameterfvNV(int arg0,int arg1,int arg2,int arg3,float[] arg4,int arg5)
  {
    printIndent();
    print("glGetCombinerInputParameterfvNV("+arg0+","+arg1+","+arg2+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glGetCombinerInputParameterfvNV(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glGetCombinerInputParameterivNV(int arg0,int arg1,int arg2,int arg3,java.nio.IntBuffer arg4)
  {
    printIndent();
    print("glGetCombinerInputParameterivNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glGetCombinerInputParameterivNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetCombinerInputParameterivNV(int arg0,int arg1,int arg2,int arg3,int[] arg4,int arg5)
  {
    printIndent();
    print("glGetCombinerInputParameterivNV("+arg0+","+arg1+","+arg2+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glGetCombinerInputParameterivNV(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glGetCombinerOutputParameterfvNV(int arg0,int arg1,int arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glGetCombinerOutputParameterfvNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetCombinerOutputParameterfvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetCombinerOutputParameterfvNV(int arg0,int arg1,int arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glGetCombinerOutputParameterfvNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glGetCombinerOutputParameterfvNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetCombinerOutputParameterivNV(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print("glGetCombinerOutputParameterivNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetCombinerOutputParameterivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetCombinerOutputParameterivNV(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print("glGetCombinerOutputParameterivNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glGetCombinerOutputParameterivNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetCombinerStageParameterfvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetCombinerStageParameterfvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetCombinerStageParameterfvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetCombinerStageParameterfvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetCombinerStageParameterfvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetCombinerStageParameterfvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetCompressedTexImage(int arg0,int arg1,java.nio.Buffer arg2)
  {
    printIndent();
    print("glGetCompressedTexImage("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetCompressedTexImage(arg0,arg1,arg2);
    println("");
  }
  public  void glGetCompressedTexImage(int arg0,int arg1,long arg2)
  {
    printIndent();
    print("glGetCompressedTexImage("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetCompressedTexImage(arg0,arg1,arg2);
    println("");
  }
  public  void glGetConvolutionFilter(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print("glGetConvolutionFilter("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetConvolutionFilter(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetConvolutionFilter(int arg0,int arg1,int arg2,long arg3)
  {
    printIndent();
    print("glGetConvolutionFilter("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetConvolutionFilter(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetConvolutionParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetConvolutionParameterfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetConvolutionParameterfv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetConvolutionParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetConvolutionParameterfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetConvolutionParameterfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetConvolutionParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetConvolutionParameteriv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetConvolutionParameteriv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetConvolutionParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetConvolutionParameteriv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetConvolutionParameteriv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetDetailTexFuncSGIS(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glGetDetailTexFuncSGIS("+arg0+","+arg1+")");
    downstreamGL.glGetDetailTexFuncSGIS(arg0,arg1);
    println("");
  }
  public  void glGetDetailTexFuncSGIS(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glGetDetailTexFuncSGIS("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGetDetailTexFuncSGIS(arg0,arg1,arg2);
    println("");
  }
  public  void glGetDoublev(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glGetDoublev("+arg0+","+arg1+")");
    downstreamGL.glGetDoublev(arg0,arg1);
    println("");
  }
  public  void glGetDoublev(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glGetDoublev("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGetDoublev(arg0,arg1,arg2);
    println("");
  }
  public  int glGetError()
  {
    printIndent();
    print("glGetError("+")");
    int _res = downstreamGL.glGetError();
    println(" = "+_res);
    return _res;
  }
  public  void glGetFenceivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetFenceivNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetFenceivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetFenceivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetFenceivNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetFenceivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetFinalCombinerInputParameterfvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetFinalCombinerInputParameterfvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetFinalCombinerInputParameterfvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetFinalCombinerInputParameterfvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetFinalCombinerInputParameterfvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetFinalCombinerInputParameterfvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetFinalCombinerInputParameterivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetFinalCombinerInputParameterivNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetFinalCombinerInputParameterivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetFinalCombinerInputParameterivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetFinalCombinerInputParameterivNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetFinalCombinerInputParameterivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetFloatv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glGetFloatv("+arg0+","+arg1+")");
    downstreamGL.glGetFloatv(arg0,arg1);
    println("");
  }
  public  void glGetFloatv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glGetFloatv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGetFloatv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetFogFuncSGIS(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glGetFogFuncSGIS("+arg0+")");
    downstreamGL.glGetFogFuncSGIS(arg0);
    println("");
  }
  public  void glGetFogFuncSGIS(float[] arg0,int arg1)
  {
    printIndent();
    print("glGetFogFuncSGIS("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glGetFogFuncSGIS(arg0,arg1);
    println("");
  }
  public  int glGetFragDataLocationEXT(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glGetFragDataLocationEXT("+arg0+","+arg1+")");
    int _res = downstreamGL.glGetFragDataLocationEXT(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  int glGetFragDataLocationEXT(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glGetFragDataLocationEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    int _res = downstreamGL.glGetFragDataLocationEXT(arg0,arg1,arg2);
    println(" = "+_res);
    return _res;
  }
  public  void glGetFragmentLightfvSGIX(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetFragmentLightfvSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetFragmentLightfvSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glGetFragmentLightfvSGIX(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetFragmentLightfvSGIX("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetFragmentLightfvSGIX(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetFragmentLightivSGIX(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetFragmentLightivSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetFragmentLightivSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glGetFragmentLightivSGIX(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetFragmentLightivSGIX("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetFragmentLightivSGIX(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetFragmentMaterialfvSGIX(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetFragmentMaterialfvSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetFragmentMaterialfvSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glGetFragmentMaterialfvSGIX(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetFragmentMaterialfvSGIX("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetFragmentMaterialfvSGIX(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetFragmentMaterialivSGIX(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetFragmentMaterialivSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetFragmentMaterialivSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glGetFragmentMaterialivSGIX(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetFragmentMaterialivSGIX("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetFragmentMaterialivSGIX(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetFramebufferAttachmentParameterivEXT(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print("glGetFramebufferAttachmentParameterivEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetFramebufferAttachmentParameterivEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetFramebufferAttachmentParameterivEXT(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print("glGetFramebufferAttachmentParameterivEXT("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glGetFramebufferAttachmentParameterivEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  int glGetHandleARB(int arg0)
  {
    printIndent();
    print("glGetHandleARB("+arg0+")");
    int _res = downstreamGL.glGetHandleARB(arg0);
    println(" = "+_res);
    return _res;
  }
  public  void glGetHistogram(int arg0,boolean arg1,int arg2,int arg3,java.nio.Buffer arg4)
  {
    printIndent();
    print("glGetHistogram("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glGetHistogram(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetHistogram(int arg0,boolean arg1,int arg2,int arg3,long arg4)
  {
    printIndent();
    print("glGetHistogram("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glGetHistogram(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetHistogramParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetHistogramParameterfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetHistogramParameterfv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetHistogramParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetHistogramParameterfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetHistogramParameterfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetHistogramParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetHistogramParameteriv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetHistogramParameteriv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetHistogramParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetHistogramParameteriv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetHistogramParameteriv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetImageTransformParameterfvHP(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetImageTransformParameterfvHP("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetImageTransformParameterfvHP(arg0,arg1,arg2);
    println("");
  }
  public  void glGetImageTransformParameterfvHP(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetImageTransformParameterfvHP("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetImageTransformParameterfvHP(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetImageTransformParameterivHP(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetImageTransformParameterivHP("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetImageTransformParameterivHP(arg0,arg1,arg2);
    println("");
  }
  public  void glGetImageTransformParameterivHP(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetImageTransformParameterivHP("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetImageTransformParameterivHP(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetInfoLogARB(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.ByteBuffer arg3)
  {
    printIndent();
    print("glGetInfoLogARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetInfoLogARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetInfoLogARB(int arg0,int arg1,int[] arg2,int arg3,byte[] arg4,int arg5)
  {
    printIndent();
    print("glGetInfoLogARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glGetInfoLogARB(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  int glGetInstrumentsSGIX()
  {
    printIndent();
    print("glGetInstrumentsSGIX("+")");
    int _res = downstreamGL.glGetInstrumentsSGIX();
    println(" = "+_res);
    return _res;
  }
  public  void glGetIntegerIndexedvEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetIntegerIndexedvEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetIntegerIndexedvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetIntegerIndexedvEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetIntegerIndexedvEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetIntegerIndexedvEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetIntegerv(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glGetIntegerv("+arg0+","+arg1+")");
    downstreamGL.glGetIntegerv(arg0,arg1);
    println("");
  }
  public  void glGetIntegerv(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glGetIntegerv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGetIntegerv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetInvariantBooleanvEXT(int arg0,int arg1,java.nio.ByteBuffer arg2)
  {
    printIndent();
    print("glGetInvariantBooleanvEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetInvariantBooleanvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetInvariantBooleanvEXT(int arg0,int arg1,byte[] arg2,int arg3)
  {
    printIndent();
    print("glGetInvariantBooleanvEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetInvariantBooleanvEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetInvariantFloatvEXT(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetInvariantFloatvEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetInvariantFloatvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetInvariantFloatvEXT(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetInvariantFloatvEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetInvariantFloatvEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetInvariantIntegervEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetInvariantIntegervEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetInvariantIntegervEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetInvariantIntegervEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetInvariantIntegervEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetInvariantIntegervEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetLightfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetLightfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetLightfv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetLightfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetLightfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetLightfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetLightiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetLightiv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetLightiv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetLightiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetLightiv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetLightiv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetListParameterfvSGIX(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetListParameterfvSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetListParameterfvSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glGetListParameterfvSGIX(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetListParameterfvSGIX("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetListParameterfvSGIX(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetListParameterivSGIX(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetListParameterivSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetListParameterivSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glGetListParameterivSGIX(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetListParameterivSGIX("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetListParameterivSGIX(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetLocalConstantBooleanvEXT(int arg0,int arg1,java.nio.ByteBuffer arg2)
  {
    printIndent();
    print("glGetLocalConstantBooleanvEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetLocalConstantBooleanvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetLocalConstantBooleanvEXT(int arg0,int arg1,byte[] arg2,int arg3)
  {
    printIndent();
    print("glGetLocalConstantBooleanvEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetLocalConstantBooleanvEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetLocalConstantFloatvEXT(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetLocalConstantFloatvEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetLocalConstantFloatvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetLocalConstantFloatvEXT(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetLocalConstantFloatvEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetLocalConstantFloatvEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetLocalConstantIntegervEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetLocalConstantIntegervEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetLocalConstantIntegervEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetLocalConstantIntegervEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetLocalConstantIntegervEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetLocalConstantIntegervEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetMapAttribParameterfvNV(int arg0,int arg1,int arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glGetMapAttribParameterfvNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetMapAttribParameterfvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetMapAttribParameterfvNV(int arg0,int arg1,int arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glGetMapAttribParameterfvNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glGetMapAttribParameterfvNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetMapAttribParameterivNV(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print("glGetMapAttribParameterivNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetMapAttribParameterivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetMapAttribParameterivNV(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print("glGetMapAttribParameterivNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glGetMapAttribParameterivNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetMapControlPointsNV(int arg0,int arg1,int arg2,int arg3,int arg4,boolean arg5,java.nio.Buffer arg6)
  {
    printIndent();
    print("glGetMapControlPointsNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glGetMapControlPointsNV(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glGetMapParameterfvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetMapParameterfvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetMapParameterfvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetMapParameterfvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetMapParameterfvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetMapParameterfvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetMapParameterivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetMapParameterivNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetMapParameterivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetMapParameterivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetMapParameterivNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetMapParameterivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetMapdv(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
    printIndent();
    print("glGetMapdv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetMapdv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetMapdv(int arg0,int arg1,double[] arg2,int arg3)
  {
    printIndent();
    print("glGetMapdv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetMapdv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetMapfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetMapfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetMapfv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetMapfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetMapfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetMapfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetMapiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetMapiv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetMapiv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetMapiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetMapiv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetMapiv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetMaterialfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetMaterialfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetMaterialfv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetMaterialfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetMaterialfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetMaterialfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetMaterialiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetMaterialiv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetMaterialiv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetMaterialiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetMaterialiv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetMaterialiv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetMinmax(int arg0,boolean arg1,int arg2,int arg3,java.nio.Buffer arg4)
  {
    printIndent();
    print("glGetMinmax("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glGetMinmax(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetMinmax(int arg0,boolean arg1,int arg2,int arg3,long arg4)
  {
    printIndent();
    print("glGetMinmax("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glGetMinmax(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetMinmaxParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetMinmaxParameterfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetMinmaxParameterfv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetMinmaxParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetMinmaxParameterfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetMinmaxParameterfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetMinmaxParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetMinmaxParameteriv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetMinmaxParameteriv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetMinmaxParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetMinmaxParameteriv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetMinmaxParameteriv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetObjectBufferfvATI(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetObjectBufferfvATI("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetObjectBufferfvATI(arg0,arg1,arg2);
    println("");
  }
  public  void glGetObjectBufferfvATI(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetObjectBufferfvATI("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetObjectBufferfvATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetObjectBufferivATI(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetObjectBufferivATI("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetObjectBufferivATI(arg0,arg1,arg2);
    println("");
  }
  public  void glGetObjectBufferivATI(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetObjectBufferivATI("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetObjectBufferivATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetObjectParameterfvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetObjectParameterfvARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetObjectParameterfvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGetObjectParameterfvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetObjectParameterfvARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetObjectParameterfvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetObjectParameterivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetObjectParameterivARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetObjectParameterivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGetObjectParameterivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetObjectParameterivARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetObjectParameterivARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetOcclusionQueryivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetOcclusionQueryivNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetOcclusionQueryivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetOcclusionQueryivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetOcclusionQueryivNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetOcclusionQueryivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetOcclusionQueryuivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetOcclusionQueryuivNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetOcclusionQueryuivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetOcclusionQueryuivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetOcclusionQueryuivNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetOcclusionQueryuivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetPixelMapfv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glGetPixelMapfv("+arg0+","+arg1+")");
    downstreamGL.glGetPixelMapfv(arg0,arg1);
    println("");
  }
  public  void glGetPixelMapfv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glGetPixelMapfv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGetPixelMapfv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetPixelMapfv(int arg0,long arg1)
  {
    printIndent();
    print("glGetPixelMapfv("+arg0+","+arg1+")");
    downstreamGL.glGetPixelMapfv(arg0,arg1);
    println("");
  }
  public  void glGetPixelMapuiv(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glGetPixelMapuiv("+arg0+","+arg1+")");
    downstreamGL.glGetPixelMapuiv(arg0,arg1);
    println("");
  }
  public  void glGetPixelMapuiv(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glGetPixelMapuiv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGetPixelMapuiv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetPixelMapuiv(int arg0,long arg1)
  {
    printIndent();
    print("glGetPixelMapuiv("+arg0+","+arg1+")");
    downstreamGL.glGetPixelMapuiv(arg0,arg1);
    println("");
  }
  public  void glGetPixelMapusv(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glGetPixelMapusv("+arg0+","+arg1+")");
    downstreamGL.glGetPixelMapusv(arg0,arg1);
    println("");
  }
  public  void glGetPixelMapusv(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glGetPixelMapusv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGetPixelMapusv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetPixelMapusv(int arg0,long arg1)
  {
    printIndent();
    print("glGetPixelMapusv("+arg0+","+arg1+")");
    downstreamGL.glGetPixelMapusv(arg0,arg1);
    println("");
  }
  public  void glGetPixelTexGenParameterfvSGIS(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glGetPixelTexGenParameterfvSGIS("+arg0+","+arg1+")");
    downstreamGL.glGetPixelTexGenParameterfvSGIS(arg0,arg1);
    println("");
  }
  public  void glGetPixelTexGenParameterfvSGIS(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glGetPixelTexGenParameterfvSGIS("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGetPixelTexGenParameterfvSGIS(arg0,arg1,arg2);
    println("");
  }
  public  void glGetPixelTexGenParameterivSGIS(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glGetPixelTexGenParameterivSGIS("+arg0+","+arg1+")");
    downstreamGL.glGetPixelTexGenParameterivSGIS(arg0,arg1);
    println("");
  }
  public  void glGetPixelTexGenParameterivSGIS(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glGetPixelTexGenParameterivSGIS("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGetPixelTexGenParameterivSGIS(arg0,arg1,arg2);
    println("");
  }
  public  void glGetPolygonStipple(java.nio.ByteBuffer arg0)
  {
    printIndent();
    print("glGetPolygonStipple("+arg0+")");
    downstreamGL.glGetPolygonStipple(arg0);
    println("");
  }
  public  void glGetPolygonStipple(byte[] arg0,int arg1)
  {
    printIndent();
    print("glGetPolygonStipple("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glGetPolygonStipple(arg0,arg1);
    println("");
  }
  public  void glGetPolygonStipple(long arg0)
  {
    printIndent();
    print("glGetPolygonStipple("+arg0+")");
    downstreamGL.glGetPolygonStipple(arg0);
    println("");
  }
  public  void glGetProgramEnvParameterIivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetProgramEnvParameterIivNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetProgramEnvParameterIivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetProgramEnvParameterIivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetProgramEnvParameterIivNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetProgramEnvParameterIivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetProgramEnvParameterIuivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetProgramEnvParameterIuivNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetProgramEnvParameterIuivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetProgramEnvParameterIuivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetProgramEnvParameterIuivNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetProgramEnvParameterIuivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetProgramEnvParameterdvARB(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
    printIndent();
    print("glGetProgramEnvParameterdvARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetProgramEnvParameterdvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGetProgramEnvParameterdvARB(int arg0,int arg1,double[] arg2,int arg3)
  {
    printIndent();
    print("glGetProgramEnvParameterdvARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetProgramEnvParameterdvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetProgramEnvParameterfvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetProgramEnvParameterfvARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetProgramEnvParameterfvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGetProgramEnvParameterfvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetProgramEnvParameterfvARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetProgramEnvParameterfvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetProgramInfoLog(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.ByteBuffer arg3)
  {
    printIndent();
    print("glGetProgramInfoLog("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetProgramInfoLog(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetProgramInfoLog(int arg0,int arg1,int[] arg2,int arg3,byte[] arg4,int arg5)
  {
    printIndent();
    print("glGetProgramInfoLog("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glGetProgramInfoLog(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glGetProgramLocalParameterIivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetProgramLocalParameterIivNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetProgramLocalParameterIivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetProgramLocalParameterIivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetProgramLocalParameterIivNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetProgramLocalParameterIivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetProgramLocalParameterIuivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetProgramLocalParameterIuivNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetProgramLocalParameterIuivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetProgramLocalParameterIuivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetProgramLocalParameterIuivNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetProgramLocalParameterIuivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetProgramLocalParameterdvARB(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
    printIndent();
    print("glGetProgramLocalParameterdvARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetProgramLocalParameterdvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGetProgramLocalParameterdvARB(int arg0,int arg1,double[] arg2,int arg3)
  {
    printIndent();
    print("glGetProgramLocalParameterdvARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetProgramLocalParameterdvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetProgramLocalParameterfvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetProgramLocalParameterfvARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetProgramLocalParameterfvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGetProgramLocalParameterfvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetProgramLocalParameterfvARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetProgramLocalParameterfvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetProgramNamedParameterdvNV(int arg0,int arg1,java.lang.String arg2,java.nio.DoubleBuffer arg3)
  {
    printIndent();
    print("glGetProgramNamedParameterdvNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetProgramNamedParameterdvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetProgramNamedParameterdvNV(int arg0,int arg1,java.lang.String arg2,double[] arg3,int arg4)
  {
    printIndent();
    print("glGetProgramNamedParameterdvNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glGetProgramNamedParameterdvNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetProgramNamedParameterfvNV(int arg0,int arg1,java.lang.String arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glGetProgramNamedParameterfvNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetProgramNamedParameterfvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetProgramNamedParameterfvNV(int arg0,int arg1,java.lang.String arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glGetProgramNamedParameterfvNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glGetProgramNamedParameterfvNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetProgramParameterdvNV(int arg0,int arg1,int arg2,java.nio.DoubleBuffer arg3)
  {
    printIndent();
    print("glGetProgramParameterdvNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetProgramParameterdvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetProgramParameterdvNV(int arg0,int arg1,int arg2,double[] arg3,int arg4)
  {
    printIndent();
    print("glGetProgramParameterdvNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glGetProgramParameterdvNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetProgramParameterfvNV(int arg0,int arg1,int arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glGetProgramParameterfvNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetProgramParameterfvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetProgramParameterfvNV(int arg0,int arg1,int arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glGetProgramParameterfvNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glGetProgramParameterfvNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetProgramStringARB(int arg0,int arg1,java.nio.Buffer arg2)
  {
    printIndent();
    print("glGetProgramStringARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetProgramStringARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGetProgramStringNV(int arg0,int arg1,java.nio.ByteBuffer arg2)
  {
    printIndent();
    print("glGetProgramStringNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetProgramStringNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetProgramStringNV(int arg0,int arg1,byte[] arg2,int arg3)
  {
    printIndent();
    print("glGetProgramStringNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetProgramStringNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetProgramiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetProgramiv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetProgramiv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetProgramiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetProgramiv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetProgramiv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetProgramivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetProgramivARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetProgramivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGetProgramivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetProgramivARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetProgramivARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetProgramivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetProgramivNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetProgramivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetProgramivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetProgramivNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetProgramivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetQueryObjecti64vEXT(int arg0,int arg1,java.nio.LongBuffer arg2)
  {
    printIndent();
    print("glGetQueryObjecti64vEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetQueryObjecti64vEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetQueryObjecti64vEXT(int arg0,int arg1,long[] arg2,int arg3)
  {
    printIndent();
    print("glGetQueryObjecti64vEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetQueryObjecti64vEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetQueryObjectiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetQueryObjectiv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetQueryObjectiv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetQueryObjectiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetQueryObjectiv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetQueryObjectiv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetQueryObjectivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetQueryObjectivARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetQueryObjectivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGetQueryObjectivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetQueryObjectivARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetQueryObjectivARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetQueryObjectui64vEXT(int arg0,int arg1,java.nio.LongBuffer arg2)
  {
    printIndent();
    print("glGetQueryObjectui64vEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetQueryObjectui64vEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetQueryObjectui64vEXT(int arg0,int arg1,long[] arg2,int arg3)
  {
    printIndent();
    print("glGetQueryObjectui64vEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetQueryObjectui64vEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetQueryObjectuiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetQueryObjectuiv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetQueryObjectuiv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetQueryObjectuiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetQueryObjectuiv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetQueryObjectuiv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetQueryObjectuivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetQueryObjectuivARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetQueryObjectuivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGetQueryObjectuivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetQueryObjectuivARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetQueryObjectuivARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetQueryiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetQueryiv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetQueryiv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetQueryiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetQueryiv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetQueryiv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetQueryivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetQueryivARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetQueryivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGetQueryivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetQueryivARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetQueryivARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetRenderbufferParameterivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetRenderbufferParameterivEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetRenderbufferParameterivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetRenderbufferParameterivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetRenderbufferParameterivEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetRenderbufferParameterivEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetSeparableFilter(int arg0,int arg1,int arg2,java.nio.Buffer arg3,java.nio.Buffer arg4,java.nio.Buffer arg5)
  {
    printIndent();
    print("glGetSeparableFilter("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glGetSeparableFilter(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glGetSeparableFilter(int arg0,int arg1,int arg2,long arg3,long arg4,long arg5)
  {
    printIndent();
    print("glGetSeparableFilter("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glGetSeparableFilter(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glGetShaderInfoLog(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.ByteBuffer arg3)
  {
    printIndent();
    print("glGetShaderInfoLog("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetShaderInfoLog(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetShaderInfoLog(int arg0,int arg1,int[] arg2,int arg3,byte[] arg4,int arg5)
  {
    printIndent();
    print("glGetShaderInfoLog("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glGetShaderInfoLog(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glGetShaderSource(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.ByteBuffer arg3)
  {
    printIndent();
    print("glGetShaderSource("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetShaderSource(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetShaderSource(int arg0,int arg1,int[] arg2,int arg3,byte[] arg4,int arg5)
  {
    printIndent();
    print("glGetShaderSource("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glGetShaderSource(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glGetShaderSourceARB(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.ByteBuffer arg3)
  {
    printIndent();
    print("glGetShaderSourceARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetShaderSourceARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetShaderSourceARB(int arg0,int arg1,int[] arg2,int arg3,byte[] arg4,int arg5)
  {
    printIndent();
    print("glGetShaderSourceARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glGetShaderSourceARB(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glGetShaderiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetShaderiv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetShaderiv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetShaderiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetShaderiv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetShaderiv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetSharpenTexFuncSGIS(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glGetSharpenTexFuncSGIS("+arg0+","+arg1+")");
    downstreamGL.glGetSharpenTexFuncSGIS(arg0,arg1);
    println("");
  }
  public  void glGetSharpenTexFuncSGIS(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glGetSharpenTexFuncSGIS("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGetSharpenTexFuncSGIS(arg0,arg1,arg2);
    println("");
  }
  public  java.lang.String glGetString(int arg0)
  {
    printIndent();
    print("glGetString("+arg0+")");
    java.lang.String _res = downstreamGL.glGetString(arg0);
    println(" = "+_res);
    return _res;
  }
  public  void glGetTexBumpParameterfvATI(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glGetTexBumpParameterfvATI("+arg0+","+arg1+")");
    downstreamGL.glGetTexBumpParameterfvATI(arg0,arg1);
    println("");
  }
  public  void glGetTexBumpParameterfvATI(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glGetTexBumpParameterfvATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGetTexBumpParameterfvATI(arg0,arg1,arg2);
    println("");
  }
  public  void glGetTexBumpParameterivATI(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glGetTexBumpParameterivATI("+arg0+","+arg1+")");
    downstreamGL.glGetTexBumpParameterivATI(arg0,arg1);
    println("");
  }
  public  void glGetTexBumpParameterivATI(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glGetTexBumpParameterivATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glGetTexBumpParameterivATI(arg0,arg1,arg2);
    println("");
  }
  public  void glGetTexEnvfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetTexEnvfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetTexEnvfv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetTexEnvfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetTexEnvfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetTexEnvfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetTexEnviv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetTexEnviv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetTexEnviv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetTexEnviv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetTexEnviv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetTexEnviv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetTexFilterFuncSGIS(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetTexFilterFuncSGIS("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetTexFilterFuncSGIS(arg0,arg1,arg2);
    println("");
  }
  public  void glGetTexFilterFuncSGIS(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetTexFilterFuncSGIS("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetTexFilterFuncSGIS(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetTexGendv(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
    printIndent();
    print("glGetTexGendv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetTexGendv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetTexGendv(int arg0,int arg1,double[] arg2,int arg3)
  {
    printIndent();
    print("glGetTexGendv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetTexGendv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetTexGenfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetTexGenfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetTexGenfv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetTexGenfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetTexGenfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetTexGenfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetTexGeniv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetTexGeniv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetTexGeniv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetTexGeniv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetTexGeniv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetTexGeniv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetTexImage(int arg0,int arg1,int arg2,int arg3,java.nio.Buffer arg4)
  {
    printIndent();
    print("glGetTexImage("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glGetTexImage(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetTexImage(int arg0,int arg1,int arg2,int arg3,long arg4)
  {
    printIndent();
    print("glGetTexImage("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glGetTexImage(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetTexLevelParameterfv(int arg0,int arg1,int arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glGetTexLevelParameterfv("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetTexLevelParameterfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetTexLevelParameterfv(int arg0,int arg1,int arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glGetTexLevelParameterfv("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glGetTexLevelParameterfv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetTexLevelParameteriv(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print("glGetTexLevelParameteriv("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetTexLevelParameteriv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetTexLevelParameteriv(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print("glGetTexLevelParameteriv("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glGetTexLevelParameteriv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetTexParameterIivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetTexParameterIivEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetTexParameterIivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetTexParameterIivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetTexParameterIivEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetTexParameterIivEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetTexParameterIuivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetTexParameterIuivEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetTexParameterIuivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetTexParameterIuivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetTexParameterIuivEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetTexParameterIuivEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetTexParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetTexParameterfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetTexParameterfv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetTexParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetTexParameterfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetTexParameterfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetTexParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetTexParameteriv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetTexParameteriv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetTexParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetTexParameteriv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetTexParameteriv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetTrackMatrixivNV(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print("glGetTrackMatrixivNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glGetTrackMatrixivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetTrackMatrixivNV(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print("glGetTrackMatrixivNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glGetTrackMatrixivNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glGetTransformFeedbackVaryingNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetTransformFeedbackVaryingNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetTransformFeedbackVaryingNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetTransformFeedbackVaryingNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetTransformFeedbackVaryingNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetTransformFeedbackVaryingNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  int glGetUniformBufferSizeEXT(int arg0,int arg1)
  {
    printIndent();
    print("glGetUniformBufferSizeEXT("+arg0+","+arg1+")");
    int _res = downstreamGL.glGetUniformBufferSizeEXT(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  int glGetUniformLocation(int arg0,java.lang.String arg1)
  {
    printIndent();
    print("glGetUniformLocation("+arg0+","+arg1+")");
    int _res = downstreamGL.glGetUniformLocation(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  int glGetUniformLocationARB(int arg0,java.lang.String arg1)
  {
    printIndent();
    print("glGetUniformLocationARB("+arg0+","+arg1+")");
    int _res = downstreamGL.glGetUniformLocationARB(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  int glGetUniformOffsetEXT(int arg0,int arg1)
  {
    printIndent();
    print("glGetUniformOffsetEXT("+arg0+","+arg1+")");
    int _res = downstreamGL.glGetUniformOffsetEXT(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  void glGetUniformfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetUniformfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetUniformfv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetUniformfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetUniformfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetUniformfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetUniformfvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetUniformfvARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetUniformfvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGetUniformfvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetUniformfvARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetUniformfvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetUniformiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetUniformiv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetUniformiv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetUniformiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetUniformiv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetUniformiv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetUniformivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetUniformivARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetUniformivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGetUniformivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetUniformivARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetUniformivARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetUniformuivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetUniformuivEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetUniformuivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetUniformuivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetUniformuivEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetUniformuivEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetVariantArrayObjectfvATI(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetVariantArrayObjectfvATI("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVariantArrayObjectfvATI(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVariantArrayObjectfvATI(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetVariantArrayObjectfvATI("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVariantArrayObjectfvATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetVariantArrayObjectivATI(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetVariantArrayObjectivATI("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVariantArrayObjectivATI(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVariantArrayObjectivATI(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetVariantArrayObjectivATI("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVariantArrayObjectivATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetVariantBooleanvEXT(int arg0,int arg1,java.nio.ByteBuffer arg2)
  {
    printIndent();
    print("glGetVariantBooleanvEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVariantBooleanvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVariantBooleanvEXT(int arg0,int arg1,byte[] arg2,int arg3)
  {
    printIndent();
    print("glGetVariantBooleanvEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVariantBooleanvEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetVariantFloatvEXT(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetVariantFloatvEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVariantFloatvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVariantFloatvEXT(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetVariantFloatvEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVariantFloatvEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetVariantIntegervEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetVariantIntegervEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVariantIntegervEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVariantIntegervEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetVariantIntegervEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVariantIntegervEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  int glGetVaryingLocationNV(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glGetVaryingLocationNV("+arg0+","+arg1+")");
    int _res = downstreamGL.glGetVaryingLocationNV(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  int glGetVaryingLocationNV(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glGetVaryingLocationNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    int _res = downstreamGL.glGetVaryingLocationNV(arg0,arg1,arg2);
    println(" = "+_res);
    return _res;
  }
  public  void glGetVertexAttribArrayObjectfvATI(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetVertexAttribArrayObjectfvATI("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVertexAttribArrayObjectfvATI(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVertexAttribArrayObjectfvATI(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetVertexAttribArrayObjectfvATI("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVertexAttribArrayObjectfvATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetVertexAttribArrayObjectivATI(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetVertexAttribArrayObjectivATI("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVertexAttribArrayObjectivATI(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVertexAttribArrayObjectivATI(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetVertexAttribArrayObjectivATI("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVertexAttribArrayObjectivATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetVertexAttribIivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetVertexAttribIivEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVertexAttribIivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVertexAttribIivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetVertexAttribIivEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVertexAttribIivEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetVertexAttribIuivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetVertexAttribIuivEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVertexAttribIuivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVertexAttribIuivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetVertexAttribIuivEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVertexAttribIuivEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetVertexAttribdv(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
    printIndent();
    print("glGetVertexAttribdv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVertexAttribdv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVertexAttribdv(int arg0,int arg1,double[] arg2,int arg3)
  {
    printIndent();
    print("glGetVertexAttribdv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVertexAttribdv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetVertexAttribdvARB(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
    printIndent();
    print("glGetVertexAttribdvARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVertexAttribdvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVertexAttribdvARB(int arg0,int arg1,double[] arg2,int arg3)
  {
    printIndent();
    print("glGetVertexAttribdvARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVertexAttribdvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetVertexAttribdvNV(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
    printIndent();
    print("glGetVertexAttribdvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVertexAttribdvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVertexAttribdvNV(int arg0,int arg1,double[] arg2,int arg3)
  {
    printIndent();
    print("glGetVertexAttribdvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVertexAttribdvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetVertexAttribfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetVertexAttribfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVertexAttribfv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVertexAttribfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetVertexAttribfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVertexAttribfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetVertexAttribfvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetVertexAttribfvARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVertexAttribfvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVertexAttribfvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetVertexAttribfvARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVertexAttribfvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetVertexAttribfvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glGetVertexAttribfvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVertexAttribfvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVertexAttribfvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glGetVertexAttribfvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVertexAttribfvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetVertexAttribiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetVertexAttribiv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVertexAttribiv(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVertexAttribiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetVertexAttribiv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVertexAttribiv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetVertexAttribivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetVertexAttribivARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVertexAttribivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVertexAttribivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetVertexAttribivARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVertexAttribivARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGetVertexAttribivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glGetVertexAttribivNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glGetVertexAttribivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glGetVertexAttribivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glGetVertexAttribivNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glGetVertexAttribivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glGlobalAlphaFactorbSUN(byte arg0)
  {
    printIndent();
    print("glGlobalAlphaFactorbSUN("+arg0+")");
    downstreamGL.glGlobalAlphaFactorbSUN(arg0);
    println("");
  }
  public  void glGlobalAlphaFactordSUN(double arg0)
  {
    printIndent();
    print("glGlobalAlphaFactordSUN("+arg0+")");
    downstreamGL.glGlobalAlphaFactordSUN(arg0);
    println("");
  }
  public  void glGlobalAlphaFactorfSUN(float arg0)
  {
    printIndent();
    print("glGlobalAlphaFactorfSUN("+arg0+")");
    downstreamGL.glGlobalAlphaFactorfSUN(arg0);
    println("");
  }
  public  void glGlobalAlphaFactoriSUN(int arg0)
  {
    printIndent();
    print("glGlobalAlphaFactoriSUN("+arg0+")");
    downstreamGL.glGlobalAlphaFactoriSUN(arg0);
    println("");
  }
  public  void glGlobalAlphaFactorsSUN(short arg0)
  {
    printIndent();
    print("glGlobalAlphaFactorsSUN("+arg0+")");
    downstreamGL.glGlobalAlphaFactorsSUN(arg0);
    println("");
  }
  public  void glGlobalAlphaFactorubSUN(byte arg0)
  {
    printIndent();
    print("glGlobalAlphaFactorubSUN("+arg0+")");
    downstreamGL.glGlobalAlphaFactorubSUN(arg0);
    println("");
  }
  public  void glGlobalAlphaFactoruiSUN(int arg0)
  {
    printIndent();
    print("glGlobalAlphaFactoruiSUN("+arg0+")");
    downstreamGL.glGlobalAlphaFactoruiSUN(arg0);
    println("");
  }
  public  void glGlobalAlphaFactorusSUN(short arg0)
  {
    printIndent();
    print("glGlobalAlphaFactorusSUN("+arg0+")");
    downstreamGL.glGlobalAlphaFactorusSUN(arg0);
    println("");
  }
  public  void glHint(int arg0,int arg1)
  {
    printIndent();
    print("glHint("+arg0+","+arg1+")");
    downstreamGL.glHint(arg0,arg1);
    println("");
  }
  public  void glHintPGI(int arg0,int arg1)
  {
    printIndent();
    print("glHintPGI("+arg0+","+arg1+")");
    downstreamGL.glHintPGI(arg0,arg1);
    println("");
  }
  public  void glHistogram(int arg0,int arg1,int arg2,boolean arg3)
  {
    printIndent();
    print("glHistogram("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glHistogram(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glIglooInterfaceSGIX(int arg0,java.nio.Buffer arg1)
  {
    printIndent();
    print("glIglooInterfaceSGIX("+arg0+","+arg1+")");
    downstreamGL.glIglooInterfaceSGIX(arg0,arg1);
    println("");
  }
  public  void glImageTransformParameterfHP(int arg0,int arg1,float arg2)
  {
    printIndent();
    print("glImageTransformParameterfHP("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glImageTransformParameterfHP(arg0,arg1,arg2);
    println("");
  }
  public  void glImageTransformParameterfvHP(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glImageTransformParameterfvHP("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glImageTransformParameterfvHP(arg0,arg1,arg2);
    println("");
  }
  public  void glImageTransformParameterfvHP(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glImageTransformParameterfvHP("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glImageTransformParameterfvHP(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glImageTransformParameteriHP(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glImageTransformParameteriHP("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glImageTransformParameteriHP(arg0,arg1,arg2);
    println("");
  }
  public  void glImageTransformParameterivHP(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glImageTransformParameterivHP("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glImageTransformParameterivHP(arg0,arg1,arg2);
    println("");
  }
  public  void glImageTransformParameterivHP(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glImageTransformParameterivHP("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glImageTransformParameterivHP(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glIndexFuncEXT(int arg0,float arg1)
  {
    printIndent();
    print("glIndexFuncEXT("+arg0+","+arg1+")");
    downstreamGL.glIndexFuncEXT(arg0,arg1);
    println("");
  }
  public  void glIndexMask(int arg0)
  {
    printIndent();
    print("glIndexMask("+arg0+")");
    downstreamGL.glIndexMask(arg0);
    println("");
  }
  public  void glIndexMaterialEXT(int arg0,int arg1)
  {
    printIndent();
    print("glIndexMaterialEXT("+arg0+","+arg1+")");
    downstreamGL.glIndexMaterialEXT(arg0,arg1);
    println("");
  }
  public  void glIndexPointer(int arg0,int arg1,java.nio.Buffer arg2)
  {
    printIndent();
    print("glIndexPointer("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glIndexPointer(arg0,arg1,arg2);
    println("");
  }
  public  void glIndexd(double arg0)
  {
    printIndent();
    print("glIndexd("+arg0+")");
    downstreamGL.glIndexd(arg0);
    println("");
  }
  public  void glIndexdv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glIndexdv("+arg0+")");
    downstreamGL.glIndexdv(arg0);
    println("");
  }
  public  void glIndexdv(double[] arg0,int arg1)
  {
    printIndent();
    print("glIndexdv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glIndexdv(arg0,arg1);
    println("");
  }
  public  void glIndexf(float arg0)
  {
    printIndent();
    print("glIndexf("+arg0+")");
    downstreamGL.glIndexf(arg0);
    println("");
  }
  public  void glIndexfv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glIndexfv("+arg0+")");
    downstreamGL.glIndexfv(arg0);
    println("");
  }
  public  void glIndexfv(float[] arg0,int arg1)
  {
    printIndent();
    print("glIndexfv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glIndexfv(arg0,arg1);
    println("");
  }
  public  void glIndexi(int arg0)
  {
    printIndent();
    print("glIndexi("+arg0+")");
    downstreamGL.glIndexi(arg0);
    println("");
  }
  public  void glIndexiv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glIndexiv("+arg0+")");
    downstreamGL.glIndexiv(arg0);
    println("");
  }
  public  void glIndexiv(int[] arg0,int arg1)
  {
    printIndent();
    print("glIndexiv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glIndexiv(arg0,arg1);
    println("");
  }
  public  void glIndexs(short arg0)
  {
    printIndent();
    print("glIndexs("+arg0+")");
    downstreamGL.glIndexs(arg0);
    println("");
  }
  public  void glIndexsv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glIndexsv("+arg0+")");
    downstreamGL.glIndexsv(arg0);
    println("");
  }
  public  void glIndexsv(short[] arg0,int arg1)
  {
    printIndent();
    print("glIndexsv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glIndexsv(arg0,arg1);
    println("");
  }
  public  void glIndexub(byte arg0)
  {
    printIndent();
    print("glIndexub("+arg0+")");
    downstreamGL.glIndexub(arg0);
    println("");
  }
  public  void glIndexubv(java.nio.ByteBuffer arg0)
  {
    printIndent();
    print("glIndexubv("+arg0+")");
    downstreamGL.glIndexubv(arg0);
    println("");
  }
  public  void glIndexubv(byte[] arg0,int arg1)
  {
    printIndent();
    print("glIndexubv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glIndexubv(arg0,arg1);
    println("");
  }
  public  void glInitNames()
  {
    printIndent();
    print("glInitNames("+")");
    downstreamGL.glInitNames();
    println("");
  }
  public  void glInsertComponentEXT(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glInsertComponentEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glInsertComponentEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glInstrumentsBufferSGIX(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glInstrumentsBufferSGIX("+arg0+","+arg1+")");
    downstreamGL.glInstrumentsBufferSGIX(arg0,arg1);
    println("");
  }
  public  void glInstrumentsBufferSGIX(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glInstrumentsBufferSGIX("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glInstrumentsBufferSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glInterleavedArrays(int arg0,int arg1,java.nio.Buffer arg2)
  {
    printIndent();
    print("glInterleavedArrays("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glInterleavedArrays(arg0,arg1,arg2);
    println("");
  }
  public  void glInterleavedArrays(int arg0,int arg1,long arg2)
  {
    printIndent();
    print("glInterleavedArrays("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glInterleavedArrays(arg0,arg1,arg2);
    println("");
  }
  public  boolean glIsAsyncMarkerSGIX(int arg0)
  {
    printIndent();
    print("glIsAsyncMarkerSGIX("+arg0+")");
    boolean _res = downstreamGL.glIsAsyncMarkerSGIX(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsBuffer(int arg0)
  {
    printIndent();
    print("glIsBuffer("+arg0+")");
    boolean _res = downstreamGL.glIsBuffer(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsBufferARB(int arg0)
  {
    printIndent();
    print("glIsBufferARB("+arg0+")");
    boolean _res = downstreamGL.glIsBufferARB(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsEnabled(int arg0)
  {
    printIndent();
    print("glIsEnabled("+arg0+")");
    boolean _res = downstreamGL.glIsEnabled(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsEnabledIndexedEXT(int arg0,int arg1)
  {
    printIndent();
    print("glIsEnabledIndexedEXT("+arg0+","+arg1+")");
    boolean _res = downstreamGL.glIsEnabledIndexedEXT(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsFenceAPPLE(int arg0)
  {
    printIndent();
    print("glIsFenceAPPLE("+arg0+")");
    boolean _res = downstreamGL.glIsFenceAPPLE(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsFenceNV(int arg0)
  {
    printIndent();
    print("glIsFenceNV("+arg0+")");
    boolean _res = downstreamGL.glIsFenceNV(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsFramebufferEXT(int arg0)
  {
    printIndent();
    print("glIsFramebufferEXT("+arg0+")");
    boolean _res = downstreamGL.glIsFramebufferEXT(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsList(int arg0)
  {
    printIndent();
    print("glIsList("+arg0+")");
    boolean _res = downstreamGL.glIsList(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsObjectBufferATI(int arg0)
  {
    printIndent();
    print("glIsObjectBufferATI("+arg0+")");
    boolean _res = downstreamGL.glIsObjectBufferATI(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsOcclusionQueryNV(int arg0)
  {
    printIndent();
    print("glIsOcclusionQueryNV("+arg0+")");
    boolean _res = downstreamGL.glIsOcclusionQueryNV(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsProgram(int arg0)
  {
    printIndent();
    print("glIsProgram("+arg0+")");
    boolean _res = downstreamGL.glIsProgram(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsProgramARB(int arg0)
  {
    printIndent();
    print("glIsProgramARB("+arg0+")");
    boolean _res = downstreamGL.glIsProgramARB(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsProgramNV(int arg0)
  {
    printIndent();
    print("glIsProgramNV("+arg0+")");
    boolean _res = downstreamGL.glIsProgramNV(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsQuery(int arg0)
  {
    printIndent();
    print("glIsQuery("+arg0+")");
    boolean _res = downstreamGL.glIsQuery(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsQueryARB(int arg0)
  {
    printIndent();
    print("glIsQueryARB("+arg0+")");
    boolean _res = downstreamGL.glIsQueryARB(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsRenderbufferEXT(int arg0)
  {
    printIndent();
    print("glIsRenderbufferEXT("+arg0+")");
    boolean _res = downstreamGL.glIsRenderbufferEXT(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsShader(int arg0)
  {
    printIndent();
    print("glIsShader("+arg0+")");
    boolean _res = downstreamGL.glIsShader(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsTexture(int arg0)
  {
    printIndent();
    print("glIsTexture("+arg0+")");
    boolean _res = downstreamGL.glIsTexture(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsVariantEnabledEXT(int arg0,int arg1)
  {
    printIndent();
    print("glIsVariantEnabledEXT("+arg0+","+arg1+")");
    boolean _res = downstreamGL.glIsVariantEnabledEXT(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsVertexArrayAPPLE(int arg0)
  {
    printIndent();
    print("glIsVertexArrayAPPLE("+arg0+")");
    boolean _res = downstreamGL.glIsVertexArrayAPPLE(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glIsVertexAttribEnabledAPPLE(int arg0,int arg1)
  {
    printIndent();
    print("glIsVertexAttribEnabledAPPLE("+arg0+","+arg1+")");
    boolean _res = downstreamGL.glIsVertexAttribEnabledAPPLE(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  void glLightEnviSGIX(int arg0,int arg1)
  {
    printIndent();
    print("glLightEnviSGIX("+arg0+","+arg1+")");
    downstreamGL.glLightEnviSGIX(arg0,arg1);
    println("");
  }
  public  void glLightModelf(int arg0,float arg1)
  {
    printIndent();
    print("glLightModelf("+arg0+","+arg1+")");
    downstreamGL.glLightModelf(arg0,arg1);
    println("");
  }
  public  void glLightModelfv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glLightModelfv("+arg0+","+arg1+")");
    downstreamGL.glLightModelfv(arg0,arg1);
    println("");
  }
  public  void glLightModelfv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glLightModelfv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glLightModelfv(arg0,arg1,arg2);
    println("");
  }
  public  void glLightModeli(int arg0,int arg1)
  {
    printIndent();
    print("glLightModeli("+arg0+","+arg1+")");
    downstreamGL.glLightModeli(arg0,arg1);
    println("");
  }
  public  void glLightModeliv(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glLightModeliv("+arg0+","+arg1+")");
    downstreamGL.glLightModeliv(arg0,arg1);
    println("");
  }
  public  void glLightModeliv(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glLightModeliv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glLightModeliv(arg0,arg1,arg2);
    println("");
  }
  public  void glLightf(int arg0,int arg1,float arg2)
  {
    printIndent();
    print("glLightf("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glLightf(arg0,arg1,arg2);
    println("");
  }
  public  void glLightfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glLightfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glLightfv(arg0,arg1,arg2);
    println("");
  }
  public  void glLightfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glLightfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glLightfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glLighti(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glLighti("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glLighti(arg0,arg1,arg2);
    println("");
  }
  public  void glLightiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glLightiv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glLightiv(arg0,arg1,arg2);
    println("");
  }
  public  void glLightiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glLightiv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glLightiv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glLineStipple(int arg0,short arg1)
  {
    printIndent();
    print("glLineStipple("+arg0+","+arg1+")");
    downstreamGL.glLineStipple(arg0,arg1);
    println("");
  }
  public  void glLineWidth(float arg0)
  {
    printIndent();
    print("glLineWidth("+arg0+")");
    downstreamGL.glLineWidth(arg0);
    println("");
  }
  public  void glLinkProgram(int arg0)
  {
    printIndent();
    print("glLinkProgram("+arg0+")");
    downstreamGL.glLinkProgram(arg0);
    println("");
  }
  public  void glLinkProgramARB(int arg0)
  {
    printIndent();
    print("glLinkProgramARB("+arg0+")");
    downstreamGL.glLinkProgramARB(arg0);
    println("");
  }
  public  void glListBase(int arg0)
  {
    printIndent();
    print("glListBase("+arg0+")");
    downstreamGL.glListBase(arg0);
    println("");
  }
  public  void glListParameterfSGIX(int arg0,int arg1,float arg2)
  {
    printIndent();
    print("glListParameterfSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glListParameterfSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glListParameterfvSGIX(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glListParameterfvSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glListParameterfvSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glListParameterfvSGIX(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glListParameterfvSGIX("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glListParameterfvSGIX(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glListParameteriSGIX(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glListParameteriSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glListParameteriSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glListParameterivSGIX(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glListParameterivSGIX("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glListParameterivSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glListParameterivSGIX(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glListParameterivSGIX("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glListParameterivSGIX(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glLoadIdentity()
  {
    printIndent();
    print("glLoadIdentity("+")");
    downstreamGL.glLoadIdentity();
    println("");
  }
  public  void glLoadIdentityDeformationMapSGIX(int arg0)
  {
    printIndent();
    print("glLoadIdentityDeformationMapSGIX("+arg0+")");
    downstreamGL.glLoadIdentityDeformationMapSGIX(arg0);
    println("");
  }
  public  void glLoadMatrixd(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glLoadMatrixd("+arg0+")");
    downstreamGL.glLoadMatrixd(arg0);
    println("");
  }
  public  void glLoadMatrixd(double[] arg0,int arg1)
  {
    printIndent();
    print("glLoadMatrixd("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glLoadMatrixd(arg0,arg1);
    println("");
  }
  public  void glLoadMatrixf(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glLoadMatrixf("+arg0+")");
    downstreamGL.glLoadMatrixf(arg0);
    println("");
  }
  public  void glLoadMatrixf(float[] arg0,int arg1)
  {
    printIndent();
    print("glLoadMatrixf("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glLoadMatrixf(arg0,arg1);
    println("");
  }
  public  void glLoadName(int arg0)
  {
    printIndent();
    print("glLoadName("+arg0+")");
    downstreamGL.glLoadName(arg0);
    println("");
  }
  public  void glLoadProgramNV(int arg0,int arg1,int arg2,java.lang.String arg3)
  {
    printIndent();
    print("glLoadProgramNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glLoadProgramNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glLoadTransposeMatrixd(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glLoadTransposeMatrixd("+arg0+")");
    downstreamGL.glLoadTransposeMatrixd(arg0);
    println("");
  }
  public  void glLoadTransposeMatrixd(double[] arg0,int arg1)
  {
    printIndent();
    print("glLoadTransposeMatrixd("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glLoadTransposeMatrixd(arg0,arg1);
    println("");
  }
  public  void glLoadTransposeMatrixf(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glLoadTransposeMatrixf("+arg0+")");
    downstreamGL.glLoadTransposeMatrixf(arg0);
    println("");
  }
  public  void glLoadTransposeMatrixf(float[] arg0,int arg1)
  {
    printIndent();
    print("glLoadTransposeMatrixf("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glLoadTransposeMatrixf(arg0,arg1);
    println("");
  }
  public  void glLockArraysEXT(int arg0,int arg1)
  {
    printIndent();
    print("glLockArraysEXT("+arg0+","+arg1+")");
    downstreamGL.glLockArraysEXT(arg0,arg1);
    println("");
  }
  public  void glLogicOp(int arg0)
  {
    printIndent();
    print("glLogicOp("+arg0+")");
    downstreamGL.glLogicOp(arg0);
    println("");
  }
  public  void glMap1d(int arg0,double arg1,double arg2,int arg3,int arg4,java.nio.DoubleBuffer arg5)
  {
    printIndent();
    print("glMap1d("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glMap1d(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glMap1d(int arg0,double arg1,double arg2,int arg3,int arg4,double[] arg5,int arg6)
  {
    printIndent();
    print("glMap1d("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+dumpArray(arg5)+","+arg6+")");
    downstreamGL.glMap1d(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glMap1f(int arg0,float arg1,float arg2,int arg3,int arg4,java.nio.FloatBuffer arg5)
  {
    printIndent();
    print("glMap1f("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glMap1f(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glMap1f(int arg0,float arg1,float arg2,int arg3,int arg4,float[] arg5,int arg6)
  {
    printIndent();
    print("glMap1f("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+dumpArray(arg5)+","+arg6+")");
    downstreamGL.glMap1f(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glMap2d(int arg0,double arg1,double arg2,int arg3,int arg4,double arg5,double arg6,int arg7,int arg8,java.nio.DoubleBuffer arg9)
  {
    printIndent();
    print("glMap2d("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+")");
    downstreamGL.glMap2d(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    println("");
  }
  public  void glMap2d(int arg0,double arg1,double arg2,int arg3,int arg4,double arg5,double arg6,int arg7,int arg8,double[] arg9,int arg10)
  {
    printIndent();
    print("glMap2d("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+dumpArray(arg9)+","+arg10+")");
    downstreamGL.glMap2d(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  public  void glMap2f(int arg0,float arg1,float arg2,int arg3,int arg4,float arg5,float arg6,int arg7,int arg8,java.nio.FloatBuffer arg9)
  {
    printIndent();
    print("glMap2f("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+")");
    downstreamGL.glMap2f(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    println("");
  }
  public  void glMap2f(int arg0,float arg1,float arg2,int arg3,int arg4,float arg5,float arg6,int arg7,int arg8,float[] arg9,int arg10)
  {
    printIndent();
    print("glMap2f("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+dumpArray(arg9)+","+arg10+")");
    downstreamGL.glMap2f(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  public  java.nio.ByteBuffer glMapBuffer(int arg0,int arg1)
  {
    printIndent();
    print("glMapBuffer("+arg0+","+arg1+")");
    java.nio.ByteBuffer _res = downstreamGL.glMapBuffer(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  java.nio.ByteBuffer glMapBufferARB(int arg0,int arg1)
  {
    printIndent();
    print("glMapBufferARB("+arg0+","+arg1+")");
    java.nio.ByteBuffer _res = downstreamGL.glMapBufferARB(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  void glMapControlPointsNV(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,boolean arg7,java.nio.Buffer arg8)
  {
    printIndent();
    print("glMapControlPointsNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+")");
    downstreamGL.glMapControlPointsNV(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  public  void glMapGrid1d(int arg0,double arg1,double arg2)
  {
    printIndent();
    print("glMapGrid1d("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glMapGrid1d(arg0,arg1,arg2);
    println("");
  }
  public  void glMapGrid1f(int arg0,float arg1,float arg2)
  {
    printIndent();
    print("glMapGrid1f("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glMapGrid1f(arg0,arg1,arg2);
    println("");
  }
  public  void glMapGrid2d(int arg0,double arg1,double arg2,int arg3,double arg4,double arg5)
  {
    printIndent();
    print("glMapGrid2d("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glMapGrid2d(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glMapGrid2f(int arg0,float arg1,float arg2,int arg3,float arg4,float arg5)
  {
    printIndent();
    print("glMapGrid2f("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glMapGrid2f(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glMapParameterfvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glMapParameterfvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glMapParameterfvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glMapParameterfvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glMapParameterfvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glMapParameterfvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glMapParameterivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glMapParameterivNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glMapParameterivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glMapParameterivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glMapParameterivNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glMapParameterivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glMapVertexAttrib1dAPPLE(int arg0,int arg1,double arg2,double arg3,int arg4,int arg5,java.nio.DoubleBuffer arg6)
  {
    printIndent();
    print("glMapVertexAttrib1dAPPLE("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glMapVertexAttrib1dAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glMapVertexAttrib1dAPPLE(int arg0,int arg1,double arg2,double arg3,int arg4,int arg5,double[] arg6,int arg7)
  {
    printIndent();
    print("glMapVertexAttrib1dAPPLE("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+dumpArray(arg6)+","+arg7+")");
    downstreamGL.glMapVertexAttrib1dAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glMapVertexAttrib1fAPPLE(int arg0,int arg1,float arg2,float arg3,int arg4,int arg5,java.nio.FloatBuffer arg6)
  {
    printIndent();
    print("glMapVertexAttrib1fAPPLE("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glMapVertexAttrib1fAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glMapVertexAttrib1fAPPLE(int arg0,int arg1,float arg2,float arg3,int arg4,int arg5,float[] arg6,int arg7)
  {
    printIndent();
    print("glMapVertexAttrib1fAPPLE("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+dumpArray(arg6)+","+arg7+")");
    downstreamGL.glMapVertexAttrib1fAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glMapVertexAttrib2dAPPLE(int arg0,int arg1,double arg2,double arg3,int arg4,int arg5,double arg6,double arg7,int arg8,int arg9,java.nio.DoubleBuffer arg10)
  {
    printIndent();
    print("glMapVertexAttrib2dAPPLE("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+")");
    downstreamGL.glMapVertexAttrib2dAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  public  void glMapVertexAttrib2dAPPLE(int arg0,int arg1,double arg2,double arg3,int arg4,int arg5,double arg6,double arg7,int arg8,int arg9,double[] arg10,int arg11)
  {
    printIndent();
    print("glMapVertexAttrib2dAPPLE("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+dumpArray(arg10)+","+arg11+")");
    downstreamGL.glMapVertexAttrib2dAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11);
    println("");
  }
  public  void glMapVertexAttrib2fAPPLE(int arg0,int arg1,float arg2,float arg3,int arg4,int arg5,float arg6,float arg7,int arg8,int arg9,java.nio.FloatBuffer arg10)
  {
    printIndent();
    print("glMapVertexAttrib2fAPPLE("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+")");
    downstreamGL.glMapVertexAttrib2fAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  public  void glMapVertexAttrib2fAPPLE(int arg0,int arg1,float arg2,float arg3,int arg4,int arg5,float arg6,float arg7,int arg8,int arg9,float[] arg10,int arg11)
  {
    printIndent();
    print("glMapVertexAttrib2fAPPLE("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+dumpArray(arg10)+","+arg11+")");
    downstreamGL.glMapVertexAttrib2fAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11);
    println("");
  }
  public  void glMaterialf(int arg0,int arg1,float arg2)
  {
    printIndent();
    print("glMaterialf("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glMaterialf(arg0,arg1,arg2);
    println("");
  }
  public  void glMaterialfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glMaterialfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glMaterialfv(arg0,arg1,arg2);
    println("");
  }
  public  void glMaterialfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glMaterialfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glMaterialfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glMateriali(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glMateriali("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glMateriali(arg0,arg1,arg2);
    println("");
  }
  public  void glMaterialiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glMaterialiv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glMaterialiv(arg0,arg1,arg2);
    println("");
  }
  public  void glMaterialiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glMaterialiv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glMaterialiv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glMatrixIndexPointerARB(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print("glMatrixIndexPointerARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glMatrixIndexPointerARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glMatrixIndexPointerARB(int arg0,int arg1,int arg2,long arg3)
  {
    printIndent();
    print("glMatrixIndexPointerARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glMatrixIndexPointerARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glMatrixIndexubvARB(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glMatrixIndexubvARB("+arg0+","+arg1+")");
    downstreamGL.glMatrixIndexubvARB(arg0,arg1);
    println("");
  }
  public  void glMatrixIndexubvARB(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glMatrixIndexubvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMatrixIndexubvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glMatrixIndexuivARB(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glMatrixIndexuivARB("+arg0+","+arg1+")");
    downstreamGL.glMatrixIndexuivARB(arg0,arg1);
    println("");
  }
  public  void glMatrixIndexuivARB(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glMatrixIndexuivARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMatrixIndexuivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glMatrixIndexusvARB(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glMatrixIndexusvARB("+arg0+","+arg1+")");
    downstreamGL.glMatrixIndexusvARB(arg0,arg1);
    println("");
  }
  public  void glMatrixIndexusvARB(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glMatrixIndexusvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMatrixIndexusvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glMatrixMode(int arg0)
  {
    printIndent();
    print("glMatrixMode("+arg0+")");
    downstreamGL.glMatrixMode(arg0);
    println("");
  }
  public  void glMinmax(int arg0,int arg1,boolean arg2)
  {
    printIndent();
    print("glMinmax("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glMinmax(arg0,arg1,arg2);
    println("");
  }
  public  void glMultMatrixd(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glMultMatrixd("+arg0+")");
    downstreamGL.glMultMatrixd(arg0);
    println("");
  }
  public  void glMultMatrixd(double[] arg0,int arg1)
  {
    printIndent();
    print("glMultMatrixd("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glMultMatrixd(arg0,arg1);
    println("");
  }
  public  void glMultMatrixf(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glMultMatrixf("+arg0+")");
    downstreamGL.glMultMatrixf(arg0);
    println("");
  }
  public  void glMultMatrixf(float[] arg0,int arg1)
  {
    printIndent();
    print("glMultMatrixf("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glMultMatrixf(arg0,arg1);
    println("");
  }
  public  void glMultTransposeMatrixd(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glMultTransposeMatrixd("+arg0+")");
    downstreamGL.glMultTransposeMatrixd(arg0);
    println("");
  }
  public  void glMultTransposeMatrixd(double[] arg0,int arg1)
  {
    printIndent();
    print("glMultTransposeMatrixd("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glMultTransposeMatrixd(arg0,arg1);
    println("");
  }
  public  void glMultTransposeMatrixf(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glMultTransposeMatrixf("+arg0+")");
    downstreamGL.glMultTransposeMatrixf(arg0);
    println("");
  }
  public  void glMultTransposeMatrixf(float[] arg0,int arg1)
  {
    printIndent();
    print("glMultTransposeMatrixf("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glMultTransposeMatrixf(arg0,arg1);
    println("");
  }
  public  void glMultiDrawArrays(int arg0,java.nio.IntBuffer arg1,java.nio.IntBuffer arg2,int arg3)
  {
    printIndent();
    print("glMultiDrawArrays("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glMultiDrawArrays(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glMultiDrawArrays(int arg0,int[] arg1,int arg2,int[] arg3,int arg4,int arg5)
  {
    printIndent();
    print("glMultiDrawArrays("+arg0+","+dumpArray(arg1)+","+arg2+","+dumpArray(arg3)+","+arg4+","+arg5+")");
    downstreamGL.glMultiDrawArrays(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glMultiDrawArraysEXT(int arg0,java.nio.IntBuffer arg1,java.nio.IntBuffer arg2,int arg3)
  {
    printIndent();
    print("glMultiDrawArraysEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glMultiDrawArraysEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glMultiDrawArraysEXT(int arg0,int[] arg1,int arg2,int[] arg3,int arg4,int arg5)
  {
    printIndent();
    print("glMultiDrawArraysEXT("+arg0+","+dumpArray(arg1)+","+arg2+","+dumpArray(arg3)+","+arg4+","+arg5+")");
    downstreamGL.glMultiDrawArraysEXT(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glMultiDrawElementArrayAPPLE(int arg0,java.nio.IntBuffer arg1,java.nio.IntBuffer arg2,int arg3)
  {
    printIndent();
    print("glMultiDrawElementArrayAPPLE("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glMultiDrawElementArrayAPPLE(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glMultiDrawElementArrayAPPLE(int arg0,int[] arg1,int arg2,int[] arg3,int arg4,int arg5)
  {
    printIndent();
    print("glMultiDrawElementArrayAPPLE("+arg0+","+dumpArray(arg1)+","+arg2+","+dumpArray(arg3)+","+arg4+","+arg5+")");
    downstreamGL.glMultiDrawElementArrayAPPLE(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glMultiDrawElements(int arg0,java.nio.IntBuffer arg1,int arg2,java.nio.Buffer[] arg3,int arg4)
  {
    printIndent();
    print("glMultiDrawElements("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glMultiDrawElements(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glMultiDrawElements(int arg0,int[] arg1,int arg2,int arg3,java.nio.Buffer[] arg4,int arg5)
  {
    printIndent();
    print("glMultiDrawElements("+arg0+","+dumpArray(arg1)+","+arg2+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glMultiDrawElements(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glMultiDrawElementsEXT(int arg0,java.nio.IntBuffer arg1,int arg2,java.nio.Buffer[] arg3,int arg4)
  {
    printIndent();
    print("glMultiDrawElementsEXT("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glMultiDrawElementsEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glMultiDrawElementsEXT(int arg0,int[] arg1,int arg2,int arg3,java.nio.Buffer[] arg4,int arg5)
  {
    printIndent();
    print("glMultiDrawElementsEXT("+arg0+","+dumpArray(arg1)+","+arg2+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glMultiDrawElementsEXT(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glMultiDrawRangeElementArrayAPPLE(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4,int arg5)
  {
    printIndent();
    print("glMultiDrawRangeElementArrayAPPLE("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glMultiDrawRangeElementArrayAPPLE(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glMultiDrawRangeElementArrayAPPLE(int arg0,int arg1,int arg2,int[] arg3,int arg4,int[] arg5,int arg6,int arg7)
  {
    printIndent();
    print("glMultiDrawRangeElementArrayAPPLE("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+","+dumpArray(arg5)+","+arg6+","+arg7+")");
    downstreamGL.glMultiDrawRangeElementArrayAPPLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glMultiModeDrawArraysIBM(java.nio.IntBuffer arg0,java.nio.IntBuffer arg1,java.nio.IntBuffer arg2,int arg3,int arg4)
  {
    printIndent();
    print("glMultiModeDrawArraysIBM("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glMultiModeDrawArraysIBM(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glMultiModeDrawArraysIBM(int[] arg0,int arg1,int[] arg2,int arg3,int[] arg4,int arg5,int arg6,int arg7)
  {
    printIndent();
    print("glMultiModeDrawArraysIBM("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+","+arg6+","+arg7+")");
    downstreamGL.glMultiModeDrawArraysIBM(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glMultiModeDrawElementsIBM(java.nio.IntBuffer arg0,java.nio.IntBuffer arg1,int arg2,java.nio.Buffer[] arg3,int arg4,int arg5)
  {
    printIndent();
    print("glMultiModeDrawElementsIBM("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+","+arg5+")");
    downstreamGL.glMultiModeDrawElementsIBM(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glMultiModeDrawElementsIBM(int[] arg0,int arg1,int[] arg2,int arg3,int arg4,java.nio.Buffer[] arg5,int arg6,int arg7)
  {
    printIndent();
    print("glMultiModeDrawElementsIBM("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+","+arg4+","+dumpArray(arg5)+","+arg6+","+arg7+")");
    downstreamGL.glMultiModeDrawElementsIBM(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glMultiTexCoord1d(int arg0,double arg1)
  {
    printIndent();
    print("glMultiTexCoord1d("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord1d(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord1dv(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord1dv("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord1dv(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord1dv(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord1dv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord1dv(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord1f(int arg0,float arg1)
  {
    printIndent();
    print("glMultiTexCoord1f("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord1f(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord1fv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord1fv("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord1fv(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord1fv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord1fv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord1fv(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord1hNV(int arg0,short arg1)
  {
    printIndent();
    print("glMultiTexCoord1hNV("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord1hNV(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord1hvNV(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord1hvNV("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord1hvNV(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord1hvNV(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord1hvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord1hvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord1i(int arg0,int arg1)
  {
    printIndent();
    print("glMultiTexCoord1i("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord1i(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord1iv(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord1iv("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord1iv(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord1iv(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord1iv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord1iv(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord1s(int arg0,short arg1)
  {
    printIndent();
    print("glMultiTexCoord1s("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord1s(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord1sv(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord1sv("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord1sv(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord1sv(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord1sv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord1sv(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord2d(int arg0,double arg1,double arg2)
  {
    printIndent();
    print("glMultiTexCoord2d("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glMultiTexCoord2d(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord2dv(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord2dv("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord2dv(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord2dv(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord2dv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord2dv(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord2f(int arg0,float arg1,float arg2)
  {
    printIndent();
    print("glMultiTexCoord2f("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glMultiTexCoord2f(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord2fv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord2fv("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord2fv(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord2fv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord2fv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord2fv(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord2hNV(int arg0,short arg1,short arg2)
  {
    printIndent();
    print("glMultiTexCoord2hNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glMultiTexCoord2hNV(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord2hvNV(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord2hvNV("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord2hvNV(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord2hvNV(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord2hvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord2hvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord2i(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord2i("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glMultiTexCoord2i(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord2iv(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord2iv("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord2iv(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord2iv(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord2iv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord2iv(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord2s(int arg0,short arg1,short arg2)
  {
    printIndent();
    print("glMultiTexCoord2s("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glMultiTexCoord2s(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord2sv(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord2sv("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord2sv(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord2sv(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord2sv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord2sv(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord3d(int arg0,double arg1,double arg2,double arg3)
  {
    printIndent();
    print("glMultiTexCoord3d("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glMultiTexCoord3d(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glMultiTexCoord3dv(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord3dv("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord3dv(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord3dv(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord3dv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord3dv(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord3f(int arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glMultiTexCoord3f("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glMultiTexCoord3f(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glMultiTexCoord3fv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord3fv("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord3fv(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord3fv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord3fv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord3fv(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord3hNV(int arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glMultiTexCoord3hNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glMultiTexCoord3hNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glMultiTexCoord3hvNV(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord3hvNV("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord3hvNV(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord3hvNV(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord3hvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord3hvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord3i(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glMultiTexCoord3i("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glMultiTexCoord3i(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glMultiTexCoord3iv(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord3iv("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord3iv(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord3iv(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord3iv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord3iv(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord3s(int arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glMultiTexCoord3s("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glMultiTexCoord3s(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glMultiTexCoord3sv(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord3sv("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord3sv(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord3sv(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord3sv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord3sv(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord4d(int arg0,double arg1,double arg2,double arg3,double arg4)
  {
    printIndent();
    print("glMultiTexCoord4d("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glMultiTexCoord4d(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glMultiTexCoord4dv(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord4dv("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord4dv(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord4dv(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord4dv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord4dv(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord4f(int arg0,float arg1,float arg2,float arg3,float arg4)
  {
    printIndent();
    print("glMultiTexCoord4f("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glMultiTexCoord4f(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glMultiTexCoord4fv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord4fv("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord4fv(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord4fv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord4fv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord4fv(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord4hNV(int arg0,short arg1,short arg2,short arg3,short arg4)
  {
    printIndent();
    print("glMultiTexCoord4hNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glMultiTexCoord4hNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glMultiTexCoord4hvNV(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord4hvNV("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord4hvNV(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord4hvNV(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord4hvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord4hvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord4i(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glMultiTexCoord4i("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glMultiTexCoord4i(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glMultiTexCoord4iv(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord4iv("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord4iv(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord4iv(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord4iv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord4iv(arg0,arg1,arg2);
    println("");
  }
  public  void glMultiTexCoord4s(int arg0,short arg1,short arg2,short arg3,short arg4)
  {
    printIndent();
    print("glMultiTexCoord4s("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glMultiTexCoord4s(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glMultiTexCoord4sv(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glMultiTexCoord4sv("+arg0+","+arg1+")");
    downstreamGL.glMultiTexCoord4sv(arg0,arg1);
    println("");
  }
  public  void glMultiTexCoord4sv(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glMultiTexCoord4sv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glMultiTexCoord4sv(arg0,arg1,arg2);
    println("");
  }
  public  int glNewBufferRegion(int arg0)
  {
    printIndent();
    print("glNewBufferRegion("+arg0+")");
    int _res = downstreamGL.glNewBufferRegion(arg0);
    println(" = "+_res);
    return _res;
  }
  public  void glNewList(int arg0,int arg1)
  {
    printIndent();
    print("glNewList("+arg0+","+arg1+")");
    downstreamGL.glNewList(arg0,arg1);
    println("");
  }
  public  int glNewObjectBufferATI(int arg0,java.nio.Buffer arg1,int arg2)
  {
    printIndent();
    print("glNewObjectBufferATI("+arg0+","+arg1+","+arg2+")");
    int _res = downstreamGL.glNewObjectBufferATI(arg0,arg1,arg2);
    println(" = "+_res);
    return _res;
  }
  public  void glNormal3b(byte arg0,byte arg1,byte arg2)
  {
    printIndent();
    print("glNormal3b("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glNormal3b(arg0,arg1,arg2);
    println("");
  }
  public  void glNormal3bv(java.nio.ByteBuffer arg0)
  {
    printIndent();
    print("glNormal3bv("+arg0+")");
    downstreamGL.glNormal3bv(arg0);
    println("");
  }
  public  void glNormal3bv(byte[] arg0,int arg1)
  {
    printIndent();
    print("glNormal3bv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glNormal3bv(arg0,arg1);
    println("");
  }
  public  void glNormal3d(double arg0,double arg1,double arg2)
  {
    printIndent();
    print("glNormal3d("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glNormal3d(arg0,arg1,arg2);
    println("");
  }
  public  void glNormal3dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glNormal3dv("+arg0+")");
    downstreamGL.glNormal3dv(arg0);
    println("");
  }
  public  void glNormal3dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glNormal3dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glNormal3dv(arg0,arg1);
    println("");
  }
  public  void glNormal3f(float arg0,float arg1,float arg2)
  {
    printIndent();
    print("glNormal3f("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glNormal3f(arg0,arg1,arg2);
    println("");
  }
  public  void glNormal3fVertex3fSUN(float arg0,float arg1,float arg2,float arg3,float arg4,float arg5)
  {
    printIndent();
    print("glNormal3fVertex3fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glNormal3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glNormal3fVertex3fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glNormal3fVertex3fvSUN("+arg0+","+arg1+")");
    downstreamGL.glNormal3fVertex3fvSUN(arg0,arg1);
    println("");
  }
  public  void glNormal3fVertex3fvSUN(float[] arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glNormal3fVertex3fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glNormal3fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glNormal3fv("+arg0+")");
    downstreamGL.glNormal3fv(arg0);
    println("");
  }
  public  void glNormal3fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glNormal3fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glNormal3fv(arg0,arg1);
    println("");
  }
  public  void glNormal3hNV(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glNormal3hNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glNormal3hNV(arg0,arg1,arg2);
    println("");
  }
  public  void glNormal3hvNV(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glNormal3hvNV("+arg0+")");
    downstreamGL.glNormal3hvNV(arg0);
    println("");
  }
  public  void glNormal3hvNV(short[] arg0,int arg1)
  {
    printIndent();
    print("glNormal3hvNV("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glNormal3hvNV(arg0,arg1);
    println("");
  }
  public  void glNormal3i(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glNormal3i("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glNormal3i(arg0,arg1,arg2);
    println("");
  }
  public  void glNormal3iv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glNormal3iv("+arg0+")");
    downstreamGL.glNormal3iv(arg0);
    println("");
  }
  public  void glNormal3iv(int[] arg0,int arg1)
  {
    printIndent();
    print("glNormal3iv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glNormal3iv(arg0,arg1);
    println("");
  }
  public  void glNormal3s(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glNormal3s("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glNormal3s(arg0,arg1,arg2);
    println("");
  }
  public  void glNormal3sv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glNormal3sv("+arg0+")");
    downstreamGL.glNormal3sv(arg0);
    println("");
  }
  public  void glNormal3sv(short[] arg0,int arg1)
  {
    printIndent();
    print("glNormal3sv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glNormal3sv(arg0,arg1);
    println("");
  }
  public  void glNormalPointer(int arg0,int arg1,java.nio.Buffer arg2)
  {
    printIndent();
    print("glNormalPointer("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glNormalPointer(arg0,arg1,arg2);
    println("");
  }
  public  void glNormalPointer(int arg0,int arg1,long arg2)
  {
    printIndent();
    print("glNormalPointer("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glNormalPointer(arg0,arg1,arg2);
    println("");
  }
  public  void glNormalStream3bATI(int arg0,byte arg1,byte arg2,byte arg3)
  {
    printIndent();
    print("glNormalStream3bATI("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glNormalStream3bATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glNormalStream3bvATI(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glNormalStream3bvATI("+arg0+","+arg1+")");
    downstreamGL.glNormalStream3bvATI(arg0,arg1);
    println("");
  }
  public  void glNormalStream3bvATI(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glNormalStream3bvATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glNormalStream3bvATI(arg0,arg1,arg2);
    println("");
  }
  public  void glNormalStream3dATI(int arg0,double arg1,double arg2,double arg3)
  {
    printIndent();
    print("glNormalStream3dATI("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glNormalStream3dATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glNormalStream3dvATI(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glNormalStream3dvATI("+arg0+","+arg1+")");
    downstreamGL.glNormalStream3dvATI(arg0,arg1);
    println("");
  }
  public  void glNormalStream3dvATI(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glNormalStream3dvATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glNormalStream3dvATI(arg0,arg1,arg2);
    println("");
  }
  public  void glNormalStream3fATI(int arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glNormalStream3fATI("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glNormalStream3fATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glNormalStream3fvATI(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glNormalStream3fvATI("+arg0+","+arg1+")");
    downstreamGL.glNormalStream3fvATI(arg0,arg1);
    println("");
  }
  public  void glNormalStream3fvATI(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glNormalStream3fvATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glNormalStream3fvATI(arg0,arg1,arg2);
    println("");
  }
  public  void glNormalStream3iATI(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glNormalStream3iATI("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glNormalStream3iATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glNormalStream3ivATI(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glNormalStream3ivATI("+arg0+","+arg1+")");
    downstreamGL.glNormalStream3ivATI(arg0,arg1);
    println("");
  }
  public  void glNormalStream3ivATI(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glNormalStream3ivATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glNormalStream3ivATI(arg0,arg1,arg2);
    println("");
  }
  public  void glNormalStream3sATI(int arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glNormalStream3sATI("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glNormalStream3sATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glNormalStream3svATI(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glNormalStream3svATI("+arg0+","+arg1+")");
    downstreamGL.glNormalStream3svATI(arg0,arg1);
    println("");
  }
  public  void glNormalStream3svATI(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glNormalStream3svATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glNormalStream3svATI(arg0,arg1,arg2);
    println("");
  }
  public  void glOrtho(double arg0,double arg1,double arg2,double arg3,double arg4,double arg5)
  {
    printIndent();
    print("glOrtho("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glOrtho(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glPNTrianglesfATI(int arg0,float arg1)
  {
    printIndent();
    print("glPNTrianglesfATI("+arg0+","+arg1+")");
    downstreamGL.glPNTrianglesfATI(arg0,arg1);
    println("");
  }
  public  void glPNTrianglesiATI(int arg0,int arg1)
  {
    printIndent();
    print("glPNTrianglesiATI("+arg0+","+arg1+")");
    downstreamGL.glPNTrianglesiATI(arg0,arg1);
    println("");
  }
  public  void glPassTexCoordATI(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glPassTexCoordATI("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glPassTexCoordATI(arg0,arg1,arg2);
    println("");
  }
  public  void glPassThrough(float arg0)
  {
    printIndent();
    print("glPassThrough("+arg0+")");
    downstreamGL.glPassThrough(arg0);
    println("");
  }
  public  void glPixelDataRangeNV(int arg0,int arg1,java.nio.Buffer arg2)
  {
    printIndent();
    print("glPixelDataRangeNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glPixelDataRangeNV(arg0,arg1,arg2);
    println("");
  }
  public  void glPixelMapfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glPixelMapfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glPixelMapfv(arg0,arg1,arg2);
    println("");
  }
  public  void glPixelMapfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glPixelMapfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glPixelMapfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glPixelMapfv(int arg0,int arg1,long arg2)
  {
    printIndent();
    print("glPixelMapfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glPixelMapfv(arg0,arg1,arg2);
    println("");
  }
  public  void glPixelMapuiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glPixelMapuiv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glPixelMapuiv(arg0,arg1,arg2);
    println("");
  }
  public  void glPixelMapuiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glPixelMapuiv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glPixelMapuiv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glPixelMapuiv(int arg0,int arg1,long arg2)
  {
    printIndent();
    print("glPixelMapuiv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glPixelMapuiv(arg0,arg1,arg2);
    println("");
  }
  public  void glPixelMapusv(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
    printIndent();
    print("glPixelMapusv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glPixelMapusv(arg0,arg1,arg2);
    println("");
  }
  public  void glPixelMapusv(int arg0,int arg1,short[] arg2,int arg3)
  {
    printIndent();
    print("glPixelMapusv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glPixelMapusv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glPixelMapusv(int arg0,int arg1,long arg2)
  {
    printIndent();
    print("glPixelMapusv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glPixelMapusv(arg0,arg1,arg2);
    println("");
  }
  public  void glPixelStoref(int arg0,float arg1)
  {
    printIndent();
    print("glPixelStoref("+arg0+","+arg1+")");
    downstreamGL.glPixelStoref(arg0,arg1);
    println("");
  }
  public  void glPixelStorei(int arg0,int arg1)
  {
    printIndent();
    print("glPixelStorei("+arg0+","+arg1+")");
    downstreamGL.glPixelStorei(arg0,arg1);
    println("");
  }
  public  void glPixelTexGenParameterfSGIS(int arg0,float arg1)
  {
    printIndent();
    print("glPixelTexGenParameterfSGIS("+arg0+","+arg1+")");
    downstreamGL.glPixelTexGenParameterfSGIS(arg0,arg1);
    println("");
  }
  public  void glPixelTexGenParameterfvSGIS(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glPixelTexGenParameterfvSGIS("+arg0+","+arg1+")");
    downstreamGL.glPixelTexGenParameterfvSGIS(arg0,arg1);
    println("");
  }
  public  void glPixelTexGenParameterfvSGIS(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glPixelTexGenParameterfvSGIS("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glPixelTexGenParameterfvSGIS(arg0,arg1,arg2);
    println("");
  }
  public  void glPixelTexGenParameteriSGIS(int arg0,int arg1)
  {
    printIndent();
    print("glPixelTexGenParameteriSGIS("+arg0+","+arg1+")");
    downstreamGL.glPixelTexGenParameteriSGIS(arg0,arg1);
    println("");
  }
  public  void glPixelTexGenParameterivSGIS(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glPixelTexGenParameterivSGIS("+arg0+","+arg1+")");
    downstreamGL.glPixelTexGenParameterivSGIS(arg0,arg1);
    println("");
  }
  public  void glPixelTexGenParameterivSGIS(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glPixelTexGenParameterivSGIS("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glPixelTexGenParameterivSGIS(arg0,arg1,arg2);
    println("");
  }
  public  void glPixelTexGenSGIX(int arg0)
  {
    printIndent();
    print("glPixelTexGenSGIX("+arg0+")");
    downstreamGL.glPixelTexGenSGIX(arg0);
    println("");
  }
  public  void glPixelTransferf(int arg0,float arg1)
  {
    printIndent();
    print("glPixelTransferf("+arg0+","+arg1+")");
    downstreamGL.glPixelTransferf(arg0,arg1);
    println("");
  }
  public  void glPixelTransferi(int arg0,int arg1)
  {
    printIndent();
    print("glPixelTransferi("+arg0+","+arg1+")");
    downstreamGL.glPixelTransferi(arg0,arg1);
    println("");
  }
  public  void glPixelTransformParameterfEXT(int arg0,int arg1,float arg2)
  {
    printIndent();
    print("glPixelTransformParameterfEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glPixelTransformParameterfEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glPixelTransformParameterfvEXT(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glPixelTransformParameterfvEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glPixelTransformParameterfvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glPixelTransformParameterfvEXT(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glPixelTransformParameterfvEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glPixelTransformParameterfvEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glPixelTransformParameteriEXT(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glPixelTransformParameteriEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glPixelTransformParameteriEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glPixelTransformParameterivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glPixelTransformParameterivEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glPixelTransformParameterivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glPixelTransformParameterivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glPixelTransformParameterivEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glPixelTransformParameterivEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glPixelZoom(float arg0,float arg1)
  {
    printIndent();
    print("glPixelZoom("+arg0+","+arg1+")");
    downstreamGL.glPixelZoom(arg0,arg1);
    println("");
  }
  public  void glPointParameterf(int arg0,float arg1)
  {
    printIndent();
    print("glPointParameterf("+arg0+","+arg1+")");
    downstreamGL.glPointParameterf(arg0,arg1);
    println("");
  }
  public  void glPointParameterfARB(int arg0,float arg1)
  {
    printIndent();
    print("glPointParameterfARB("+arg0+","+arg1+")");
    downstreamGL.glPointParameterfARB(arg0,arg1);
    println("");
  }
  public  void glPointParameterfEXT(int arg0,float arg1)
  {
    printIndent();
    print("glPointParameterfEXT("+arg0+","+arg1+")");
    downstreamGL.glPointParameterfEXT(arg0,arg1);
    println("");
  }
  public  void glPointParameterfSGIS(int arg0,float arg1)
  {
    printIndent();
    print("glPointParameterfSGIS("+arg0+","+arg1+")");
    downstreamGL.glPointParameterfSGIS(arg0,arg1);
    println("");
  }
  public  void glPointParameterfv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glPointParameterfv("+arg0+","+arg1+")");
    downstreamGL.glPointParameterfv(arg0,arg1);
    println("");
  }
  public  void glPointParameterfv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glPointParameterfv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glPointParameterfv(arg0,arg1,arg2);
    println("");
  }
  public  void glPointParameterfvARB(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glPointParameterfvARB("+arg0+","+arg1+")");
    downstreamGL.glPointParameterfvARB(arg0,arg1);
    println("");
  }
  public  void glPointParameterfvARB(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glPointParameterfvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glPointParameterfvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glPointParameterfvEXT(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glPointParameterfvEXT("+arg0+","+arg1+")");
    downstreamGL.glPointParameterfvEXT(arg0,arg1);
    println("");
  }
  public  void glPointParameterfvEXT(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glPointParameterfvEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glPointParameterfvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glPointParameterfvSGIS(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glPointParameterfvSGIS("+arg0+","+arg1+")");
    downstreamGL.glPointParameterfvSGIS(arg0,arg1);
    println("");
  }
  public  void glPointParameterfvSGIS(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glPointParameterfvSGIS("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glPointParameterfvSGIS(arg0,arg1,arg2);
    println("");
  }
  public  void glPointParameteri(int arg0,int arg1)
  {
    printIndent();
    print("glPointParameteri("+arg0+","+arg1+")");
    downstreamGL.glPointParameteri(arg0,arg1);
    println("");
  }
  public  void glPointParameteriNV(int arg0,int arg1)
  {
    printIndent();
    print("glPointParameteriNV("+arg0+","+arg1+")");
    downstreamGL.glPointParameteriNV(arg0,arg1);
    println("");
  }
  public  void glPointParameteriv(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glPointParameteriv("+arg0+","+arg1+")");
    downstreamGL.glPointParameteriv(arg0,arg1);
    println("");
  }
  public  void glPointParameteriv(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glPointParameteriv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glPointParameteriv(arg0,arg1,arg2);
    println("");
  }
  public  void glPointParameterivNV(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glPointParameterivNV("+arg0+","+arg1+")");
    downstreamGL.glPointParameterivNV(arg0,arg1);
    println("");
  }
  public  void glPointParameterivNV(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glPointParameterivNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glPointParameterivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glPointSize(float arg0)
  {
    printIndent();
    print("glPointSize("+arg0+")");
    downstreamGL.glPointSize(arg0);
    println("");
  }
  public  int glPollAsyncSGIX(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glPollAsyncSGIX("+arg0+")");
    int _res = downstreamGL.glPollAsyncSGIX(arg0);
    println(" = "+_res);
    return _res;
  }
  public  int glPollAsyncSGIX(int[] arg0,int arg1)
  {
    printIndent();
    print("glPollAsyncSGIX("+dumpArray(arg0)+","+arg1+")");
    int _res = downstreamGL.glPollAsyncSGIX(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  int glPollInstrumentsSGIX(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glPollInstrumentsSGIX("+arg0+")");
    int _res = downstreamGL.glPollInstrumentsSGIX(arg0);
    println(" = "+_res);
    return _res;
  }
  public  int glPollInstrumentsSGIX(int[] arg0,int arg1)
  {
    printIndent();
    print("glPollInstrumentsSGIX("+dumpArray(arg0)+","+arg1+")");
    int _res = downstreamGL.glPollInstrumentsSGIX(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  void glPolygonMode(int arg0,int arg1)
  {
    printIndent();
    print("glPolygonMode("+arg0+","+arg1+")");
    downstreamGL.glPolygonMode(arg0,arg1);
    println("");
  }
  public  void glPolygonOffset(float arg0,float arg1)
  {
    printIndent();
    print("glPolygonOffset("+arg0+","+arg1+")");
    downstreamGL.glPolygonOffset(arg0,arg1);
    println("");
  }
  public  void glPolygonStipple(java.nio.ByteBuffer arg0)
  {
    printIndent();
    print("glPolygonStipple("+arg0+")");
    downstreamGL.glPolygonStipple(arg0);
    println("");
  }
  public  void glPolygonStipple(byte[] arg0,int arg1)
  {
    printIndent();
    print("glPolygonStipple("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glPolygonStipple(arg0,arg1);
    println("");
  }
  public  void glPolygonStipple(long arg0)
  {
    printIndent();
    print("glPolygonStipple("+arg0+")");
    downstreamGL.glPolygonStipple(arg0);
    println("");
  }
  public  void glPopAttrib()
  {
    printIndent();
    print("glPopAttrib("+")");
    downstreamGL.glPopAttrib();
    println("");
  }
  public  void glPopClientAttrib()
  {
    printIndent();
    print("glPopClientAttrib("+")");
    downstreamGL.glPopClientAttrib();
    println("");
  }
  public  void glPopMatrix()
  {
    printIndent();
    print("glPopMatrix("+")");
    downstreamGL.glPopMatrix();
    println("");
  }
  public  void glPopName()
  {
    printIndent();
    print("glPopName("+")");
    downstreamGL.glPopName();
    println("");
  }
  public  void glPrimitiveRestartIndexNV(int arg0)
  {
    printIndent();
    print("glPrimitiveRestartIndexNV("+arg0+")");
    downstreamGL.glPrimitiveRestartIndexNV(arg0);
    println("");
  }
  public  void glPrimitiveRestartNV()
  {
    printIndent();
    print("glPrimitiveRestartNV("+")");
    downstreamGL.glPrimitiveRestartNV();
    println("");
  }
  public  void glPrioritizeTextures(int arg0,java.nio.IntBuffer arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glPrioritizeTextures("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glPrioritizeTextures(arg0,arg1,arg2);
    println("");
  }
  public  void glPrioritizeTextures(int arg0,int[] arg1,int arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glPrioritizeTextures("+arg0+","+dumpArray(arg1)+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glPrioritizeTextures(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glProgramBufferParametersIivNV(int arg0,int arg1,int arg2,int arg3,java.nio.IntBuffer arg4)
  {
    printIndent();
    print("glProgramBufferParametersIivNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glProgramBufferParametersIivNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glProgramBufferParametersIivNV(int arg0,int arg1,int arg2,int arg3,int[] arg4,int arg5)
  {
    printIndent();
    print("glProgramBufferParametersIivNV("+arg0+","+arg1+","+arg2+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glProgramBufferParametersIivNV(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glProgramBufferParametersIuivNV(int arg0,int arg1,int arg2,int arg3,java.nio.IntBuffer arg4)
  {
    printIndent();
    print("glProgramBufferParametersIuivNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glProgramBufferParametersIuivNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glProgramBufferParametersIuivNV(int arg0,int arg1,int arg2,int arg3,int[] arg4,int arg5)
  {
    printIndent();
    print("glProgramBufferParametersIuivNV("+arg0+","+arg1+","+arg2+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glProgramBufferParametersIuivNV(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glProgramBufferParametersfvNV(int arg0,int arg1,int arg2,int arg3,java.nio.FloatBuffer arg4)
  {
    printIndent();
    print("glProgramBufferParametersfvNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glProgramBufferParametersfvNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glProgramBufferParametersfvNV(int arg0,int arg1,int arg2,int arg3,float[] arg4,int arg5)
  {
    printIndent();
    print("glProgramBufferParametersfvNV("+arg0+","+arg1+","+arg2+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glProgramBufferParametersfvNV(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glProgramEnvParameter4dARB(int arg0,int arg1,double arg2,double arg3,double arg4,double arg5)
  {
    printIndent();
    print("glProgramEnvParameter4dARB("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glProgramEnvParameter4dARB(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glProgramEnvParameter4dvARB(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
    printIndent();
    print("glProgramEnvParameter4dvARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glProgramEnvParameter4dvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glProgramEnvParameter4dvARB(int arg0,int arg1,double[] arg2,int arg3)
  {
    printIndent();
    print("glProgramEnvParameter4dvARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glProgramEnvParameter4dvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramEnvParameter4fARB(int arg0,int arg1,float arg2,float arg3,float arg4,float arg5)
  {
    printIndent();
    print("glProgramEnvParameter4fARB("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glProgramEnvParameter4fARB(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glProgramEnvParameter4fvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glProgramEnvParameter4fvARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glProgramEnvParameter4fvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glProgramEnvParameter4fvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glProgramEnvParameter4fvARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glProgramEnvParameter4fvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramEnvParameterI4iNV(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print("glProgramEnvParameterI4iNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glProgramEnvParameterI4iNV(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glProgramEnvParameterI4ivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glProgramEnvParameterI4ivNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glProgramEnvParameterI4ivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glProgramEnvParameterI4ivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glProgramEnvParameterI4ivNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glProgramEnvParameterI4ivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramEnvParameterI4uiNV(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print("glProgramEnvParameterI4uiNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glProgramEnvParameterI4uiNV(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glProgramEnvParameterI4uivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glProgramEnvParameterI4uivNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glProgramEnvParameterI4uivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glProgramEnvParameterI4uivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glProgramEnvParameterI4uivNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glProgramEnvParameterI4uivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramEnvParameters4fvEXT(int arg0,int arg1,int arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glProgramEnvParameters4fvEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glProgramEnvParameters4fvEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramEnvParameters4fvEXT(int arg0,int arg1,int arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glProgramEnvParameters4fvEXT("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glProgramEnvParameters4fvEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glProgramEnvParametersI4ivNV(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print("glProgramEnvParametersI4ivNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glProgramEnvParametersI4ivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramEnvParametersI4ivNV(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print("glProgramEnvParametersI4ivNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glProgramEnvParametersI4ivNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glProgramEnvParametersI4uivNV(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print("glProgramEnvParametersI4uivNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glProgramEnvParametersI4uivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramEnvParametersI4uivNV(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print("glProgramEnvParametersI4uivNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glProgramEnvParametersI4uivNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glProgramLocalParameter4dARB(int arg0,int arg1,double arg2,double arg3,double arg4,double arg5)
  {
    printIndent();
    print("glProgramLocalParameter4dARB("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glProgramLocalParameter4dARB(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glProgramLocalParameter4dvARB(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
    printIndent();
    print("glProgramLocalParameter4dvARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glProgramLocalParameter4dvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glProgramLocalParameter4dvARB(int arg0,int arg1,double[] arg2,int arg3)
  {
    printIndent();
    print("glProgramLocalParameter4dvARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glProgramLocalParameter4dvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramLocalParameter4fARB(int arg0,int arg1,float arg2,float arg3,float arg4,float arg5)
  {
    printIndent();
    print("glProgramLocalParameter4fARB("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glProgramLocalParameter4fARB(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glProgramLocalParameter4fvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glProgramLocalParameter4fvARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glProgramLocalParameter4fvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glProgramLocalParameter4fvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glProgramLocalParameter4fvARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glProgramLocalParameter4fvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramLocalParameterI4iNV(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print("glProgramLocalParameterI4iNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glProgramLocalParameterI4iNV(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glProgramLocalParameterI4ivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glProgramLocalParameterI4ivNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glProgramLocalParameterI4ivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glProgramLocalParameterI4ivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glProgramLocalParameterI4ivNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glProgramLocalParameterI4ivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramLocalParameterI4uiNV(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print("glProgramLocalParameterI4uiNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glProgramLocalParameterI4uiNV(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glProgramLocalParameterI4uivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glProgramLocalParameterI4uivNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glProgramLocalParameterI4uivNV(arg0,arg1,arg2);
    println("");
  }
  public  void glProgramLocalParameterI4uivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glProgramLocalParameterI4uivNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glProgramLocalParameterI4uivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramLocalParameters4fvEXT(int arg0,int arg1,int arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glProgramLocalParameters4fvEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glProgramLocalParameters4fvEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramLocalParameters4fvEXT(int arg0,int arg1,int arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glProgramLocalParameters4fvEXT("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glProgramLocalParameters4fvEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glProgramLocalParametersI4ivNV(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print("glProgramLocalParametersI4ivNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glProgramLocalParametersI4ivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramLocalParametersI4ivNV(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print("glProgramLocalParametersI4ivNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glProgramLocalParametersI4ivNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glProgramLocalParametersI4uivNV(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print("glProgramLocalParametersI4uivNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glProgramLocalParametersI4uivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramLocalParametersI4uivNV(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print("glProgramLocalParametersI4uivNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glProgramLocalParametersI4uivNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glProgramNamedParameter4dNV(int arg0,int arg1,java.lang.String arg2,double arg3,double arg4,double arg5,double arg6)
  {
    printIndent();
    print("glProgramNamedParameter4dNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glProgramNamedParameter4dNV(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glProgramNamedParameter4dvNV(int arg0,int arg1,java.lang.String arg2,java.nio.DoubleBuffer arg3)
  {
    printIndent();
    print("glProgramNamedParameter4dvNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glProgramNamedParameter4dvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramNamedParameter4dvNV(int arg0,int arg1,java.lang.String arg2,double[] arg3,int arg4)
  {
    printIndent();
    print("glProgramNamedParameter4dvNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glProgramNamedParameter4dvNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glProgramNamedParameter4fNV(int arg0,int arg1,java.lang.String arg2,float arg3,float arg4,float arg5,float arg6)
  {
    printIndent();
    print("glProgramNamedParameter4fNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glProgramNamedParameter4fNV(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glProgramNamedParameter4fvNV(int arg0,int arg1,java.lang.String arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glProgramNamedParameter4fvNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glProgramNamedParameter4fvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramNamedParameter4fvNV(int arg0,int arg1,java.lang.String arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glProgramNamedParameter4fvNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glProgramNamedParameter4fvNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glProgramParameter4dNV(int arg0,int arg1,double arg2,double arg3,double arg4,double arg5)
  {
    printIndent();
    print("glProgramParameter4dNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glProgramParameter4dNV(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glProgramParameter4dvNV(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
    printIndent();
    print("glProgramParameter4dvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glProgramParameter4dvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glProgramParameter4dvNV(int arg0,int arg1,double[] arg2,int arg3)
  {
    printIndent();
    print("glProgramParameter4dvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glProgramParameter4dvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramParameter4fNV(int arg0,int arg1,float arg2,float arg3,float arg4,float arg5)
  {
    printIndent();
    print("glProgramParameter4fNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glProgramParameter4fNV(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glProgramParameter4fvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glProgramParameter4fvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glProgramParameter4fvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glProgramParameter4fvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glProgramParameter4fvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glProgramParameter4fvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramParameteriEXT(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glProgramParameteriEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glProgramParameteriEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glProgramParameters4dvNV(int arg0,int arg1,int arg2,java.nio.DoubleBuffer arg3)
  {
    printIndent();
    print("glProgramParameters4dvNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glProgramParameters4dvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramParameters4dvNV(int arg0,int arg1,int arg2,double[] arg3,int arg4)
  {
    printIndent();
    print("glProgramParameters4dvNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glProgramParameters4dvNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glProgramParameters4fvNV(int arg0,int arg1,int arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glProgramParameters4fvNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glProgramParameters4fvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramParameters4fvNV(int arg0,int arg1,int arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glProgramParameters4fvNV("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glProgramParameters4fvNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glProgramStringARB(int arg0,int arg1,int arg2,java.lang.String arg3)
  {
    printIndent();
    print("glProgramStringARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glProgramStringARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glProgramVertexLimitNV(int arg0,int arg1)
  {
    printIndent();
    print("glProgramVertexLimitNV("+arg0+","+arg1+")");
    downstreamGL.glProgramVertexLimitNV(arg0,arg1);
    println("");
  }
  public  void glPushAttrib(int arg0)
  {
    printIndent();
    print("glPushAttrib("+arg0+")");
    downstreamGL.glPushAttrib(arg0);
    println("");
  }
  public  void glPushClientAttrib(int arg0)
  {
    printIndent();
    print("glPushClientAttrib("+arg0+")");
    downstreamGL.glPushClientAttrib(arg0);
    println("");
  }
  public  void glPushMatrix()
  {
    printIndent();
    print("glPushMatrix("+")");
    downstreamGL.glPushMatrix();
    println("");
  }
  public  void glPushName(int arg0)
  {
    printIndent();
    print("glPushName("+arg0+")");
    downstreamGL.glPushName(arg0);
    println("");
  }
  public  void glRasterPos2d(double arg0,double arg1)
  {
    printIndent();
    print("glRasterPos2d("+arg0+","+arg1+")");
    downstreamGL.glRasterPos2d(arg0,arg1);
    println("");
  }
  public  void glRasterPos2dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glRasterPos2dv("+arg0+")");
    downstreamGL.glRasterPos2dv(arg0);
    println("");
  }
  public  void glRasterPos2dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glRasterPos2dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glRasterPos2dv(arg0,arg1);
    println("");
  }
  public  void glRasterPos2f(float arg0,float arg1)
  {
    printIndent();
    print("glRasterPos2f("+arg0+","+arg1+")");
    downstreamGL.glRasterPos2f(arg0,arg1);
    println("");
  }
  public  void glRasterPos2fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glRasterPos2fv("+arg0+")");
    downstreamGL.glRasterPos2fv(arg0);
    println("");
  }
  public  void glRasterPos2fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glRasterPos2fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glRasterPos2fv(arg0,arg1);
    println("");
  }
  public  void glRasterPos2i(int arg0,int arg1)
  {
    printIndent();
    print("glRasterPos2i("+arg0+","+arg1+")");
    downstreamGL.glRasterPos2i(arg0,arg1);
    println("");
  }
  public  void glRasterPos2iv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glRasterPos2iv("+arg0+")");
    downstreamGL.glRasterPos2iv(arg0);
    println("");
  }
  public  void glRasterPos2iv(int[] arg0,int arg1)
  {
    printIndent();
    print("glRasterPos2iv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glRasterPos2iv(arg0,arg1);
    println("");
  }
  public  void glRasterPos2s(short arg0,short arg1)
  {
    printIndent();
    print("glRasterPos2s("+arg0+","+arg1+")");
    downstreamGL.glRasterPos2s(arg0,arg1);
    println("");
  }
  public  void glRasterPos2sv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glRasterPos2sv("+arg0+")");
    downstreamGL.glRasterPos2sv(arg0);
    println("");
  }
  public  void glRasterPos2sv(short[] arg0,int arg1)
  {
    printIndent();
    print("glRasterPos2sv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glRasterPos2sv(arg0,arg1);
    println("");
  }
  public  void glRasterPos3d(double arg0,double arg1,double arg2)
  {
    printIndent();
    print("glRasterPos3d("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glRasterPos3d(arg0,arg1,arg2);
    println("");
  }
  public  void glRasterPos3dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glRasterPos3dv("+arg0+")");
    downstreamGL.glRasterPos3dv(arg0);
    println("");
  }
  public  void glRasterPos3dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glRasterPos3dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glRasterPos3dv(arg0,arg1);
    println("");
  }
  public  void glRasterPos3f(float arg0,float arg1,float arg2)
  {
    printIndent();
    print("glRasterPos3f("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glRasterPos3f(arg0,arg1,arg2);
    println("");
  }
  public  void glRasterPos3fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glRasterPos3fv("+arg0+")");
    downstreamGL.glRasterPos3fv(arg0);
    println("");
  }
  public  void glRasterPos3fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glRasterPos3fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glRasterPos3fv(arg0,arg1);
    println("");
  }
  public  void glRasterPos3i(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glRasterPos3i("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glRasterPos3i(arg0,arg1,arg2);
    println("");
  }
  public  void glRasterPos3iv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glRasterPos3iv("+arg0+")");
    downstreamGL.glRasterPos3iv(arg0);
    println("");
  }
  public  void glRasterPos3iv(int[] arg0,int arg1)
  {
    printIndent();
    print("glRasterPos3iv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glRasterPos3iv(arg0,arg1);
    println("");
  }
  public  void glRasterPos3s(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glRasterPos3s("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glRasterPos3s(arg0,arg1,arg2);
    println("");
  }
  public  void glRasterPos3sv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glRasterPos3sv("+arg0+")");
    downstreamGL.glRasterPos3sv(arg0);
    println("");
  }
  public  void glRasterPos3sv(short[] arg0,int arg1)
  {
    printIndent();
    print("glRasterPos3sv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glRasterPos3sv(arg0,arg1);
    println("");
  }
  public  void glRasterPos4d(double arg0,double arg1,double arg2,double arg3)
  {
    printIndent();
    print("glRasterPos4d("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glRasterPos4d(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glRasterPos4dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glRasterPos4dv("+arg0+")");
    downstreamGL.glRasterPos4dv(arg0);
    println("");
  }
  public  void glRasterPos4dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glRasterPos4dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glRasterPos4dv(arg0,arg1);
    println("");
  }
  public  void glRasterPos4f(float arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glRasterPos4f("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glRasterPos4f(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glRasterPos4fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glRasterPos4fv("+arg0+")");
    downstreamGL.glRasterPos4fv(arg0);
    println("");
  }
  public  void glRasterPos4fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glRasterPos4fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glRasterPos4fv(arg0,arg1);
    println("");
  }
  public  void glRasterPos4i(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glRasterPos4i("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glRasterPos4i(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glRasterPos4iv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glRasterPos4iv("+arg0+")");
    downstreamGL.glRasterPos4iv(arg0);
    println("");
  }
  public  void glRasterPos4iv(int[] arg0,int arg1)
  {
    printIndent();
    print("glRasterPos4iv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glRasterPos4iv(arg0,arg1);
    println("");
  }
  public  void glRasterPos4s(short arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glRasterPos4s("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glRasterPos4s(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glRasterPos4sv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glRasterPos4sv("+arg0+")");
    downstreamGL.glRasterPos4sv(arg0);
    println("");
  }
  public  void glRasterPos4sv(short[] arg0,int arg1)
  {
    printIndent();
    print("glRasterPos4sv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glRasterPos4sv(arg0,arg1);
    println("");
  }
  public  void glReadBuffer(int arg0)
  {
    printIndent();
    print("glReadBuffer("+arg0+")");
    downstreamGL.glReadBuffer(arg0);
    println("");
  }
  public  void glReadBufferRegion(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glReadBufferRegion("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glReadBufferRegion(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glReadInstrumentsSGIX(int arg0)
  {
    printIndent();
    print("glReadInstrumentsSGIX("+arg0+")");
    downstreamGL.glReadInstrumentsSGIX(arg0);
    println("");
  }
  public  void glReadPixels(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,java.nio.Buffer arg6)
  {
    printIndent();
    print("glReadPixels("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glReadPixels(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glReadPixels(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,long arg6)
  {
    printIndent();
    print("glReadPixels("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glReadPixels(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glRectd(double arg0,double arg1,double arg2,double arg3)
  {
    printIndent();
    print("glRectd("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glRectd(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glRectdv(java.nio.DoubleBuffer arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glRectdv("+arg0+","+arg1+")");
    downstreamGL.glRectdv(arg0,arg1);
    println("");
  }
  public  void glRectdv(double[] arg0,int arg1,double[] arg2,int arg3)
  {
    printIndent();
    print("glRectdv("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glRectdv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glRectf(float arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glRectf("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glRectf(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glRectfv(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glRectfv("+arg0+","+arg1+")");
    downstreamGL.glRectfv(arg0,arg1);
    println("");
  }
  public  void glRectfv(float[] arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glRectfv("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glRectfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glRecti(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glRecti("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glRecti(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glRectiv(java.nio.IntBuffer arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glRectiv("+arg0+","+arg1+")");
    downstreamGL.glRectiv(arg0,arg1);
    println("");
  }
  public  void glRectiv(int[] arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glRectiv("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glRectiv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glRects(short arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glRects("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glRects(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glRectsv(java.nio.ShortBuffer arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glRectsv("+arg0+","+arg1+")");
    downstreamGL.glRectsv(arg0,arg1);
    println("");
  }
  public  void glRectsv(short[] arg0,int arg1,short[] arg2,int arg3)
  {
    printIndent();
    print("glRectsv("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glRectsv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glReferencePlaneSGIX(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glReferencePlaneSGIX("+arg0+")");
    downstreamGL.glReferencePlaneSGIX(arg0);
    println("");
  }
  public  void glReferencePlaneSGIX(double[] arg0,int arg1)
  {
    printIndent();
    print("glReferencePlaneSGIX("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glReferencePlaneSGIX(arg0,arg1);
    println("");
  }
  public  int glRenderMode(int arg0)
  {
    printIndent();
    print("glRenderMode("+arg0+")");
    int _res = downstreamGL.glRenderMode(arg0);
    println(" = "+_res);
    return _res;
  }
  public  void glRenderbufferStorageEXT(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glRenderbufferStorageEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glRenderbufferStorageEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glRenderbufferStorageMultisampleCoverageNV(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print("glRenderbufferStorageMultisampleCoverageNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glRenderbufferStorageMultisampleCoverageNV(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glRenderbufferStorageMultisampleEXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glRenderbufferStorageMultisampleEXT("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glRenderbufferStorageMultisampleEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glReplacementCodeuiColor3fVertex3fSUN(int arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6)
  {
    printIndent();
    print("glReplacementCodeuiColor3fVertex3fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glReplacementCodeuiColor3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glReplacementCodeuiColor3fVertex3fvSUN(java.nio.IntBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glReplacementCodeuiColor3fVertex3fvSUN("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glReplacementCodeuiColor3fVertex3fvSUN(arg0,arg1,arg2);
    println("");
  }
  public  void glReplacementCodeuiColor3fVertex3fvSUN(int[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5)
  {
    printIndent();
    print("glReplacementCodeuiColor3fVertex3fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glReplacementCodeuiColor3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glReplacementCodeuiColor4fNormal3fVertex3fSUN(int arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7,float arg8,float arg9,float arg10)
  {
    printIndent();
    print("glReplacementCodeuiColor4fNormal3fVertex3fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+")");
    downstreamGL.glReplacementCodeuiColor4fNormal3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  public  void glReplacementCodeuiColor4fNormal3fVertex3fvSUN(java.nio.IntBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glReplacementCodeuiColor4fNormal3fVertex3fvSUN("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glReplacementCodeuiColor4fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glReplacementCodeuiColor4fNormal3fVertex3fvSUN(int[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5,float[] arg6,int arg7)
  {
    printIndent();
    print("glReplacementCodeuiColor4fNormal3fVertex3fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+","+dumpArray(arg6)+","+arg7+")");
    downstreamGL.glReplacementCodeuiColor4fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glReplacementCodeuiColor4ubVertex3fSUN(int arg0,byte arg1,byte arg2,byte arg3,byte arg4,float arg5,float arg6,float arg7)
  {
    printIndent();
    print("glReplacementCodeuiColor4ubVertex3fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+")");
    downstreamGL.glReplacementCodeuiColor4ubVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glReplacementCodeuiColor4ubVertex3fvSUN(java.nio.IntBuffer arg0,java.nio.ByteBuffer arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glReplacementCodeuiColor4ubVertex3fvSUN("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glReplacementCodeuiColor4ubVertex3fvSUN(arg0,arg1,arg2);
    println("");
  }
  public  void glReplacementCodeuiColor4ubVertex3fvSUN(int[] arg0,int arg1,byte[] arg2,int arg3,float[] arg4,int arg5)
  {
    printIndent();
    print("glReplacementCodeuiColor4ubVertex3fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glReplacementCodeuiColor4ubVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glReplacementCodeuiNormal3fVertex3fSUN(int arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6)
  {
    printIndent();
    print("glReplacementCodeuiNormal3fVertex3fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glReplacementCodeuiNormal3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glReplacementCodeuiNormal3fVertex3fvSUN(java.nio.IntBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glReplacementCodeuiNormal3fVertex3fvSUN("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glReplacementCodeuiNormal3fVertex3fvSUN(arg0,arg1,arg2);
    println("");
  }
  public  void glReplacementCodeuiNormal3fVertex3fvSUN(int[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5)
  {
    printIndent();
    print("glReplacementCodeuiNormal3fVertex3fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glReplacementCodeuiNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fSUN(int arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7,float arg8,float arg9,float arg10,float arg11,float arg12)
  {
    printIndent();
    print("glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+","+arg11+","+arg12+")");
    downstreamGL.glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12);
    println("");
  }
  public  void glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fvSUN(java.nio.IntBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2,java.nio.FloatBuffer arg3,java.nio.FloatBuffer arg4)
  {
    printIndent();
    print("glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fvSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fvSUN(int[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5,float[] arg6,int arg7,float[] arg8,int arg9)
  {
    printIndent();
    print("glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+","+dumpArray(arg6)+","+arg7+","+dumpArray(arg8)+","+arg9+")");
    downstreamGL.glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    println("");
  }
  public  void glReplacementCodeuiTexCoord2fNormal3fVertex3fSUN(int arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7,float arg8)
  {
    printIndent();
    print("glReplacementCodeuiTexCoord2fNormal3fVertex3fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+")");
    downstreamGL.glReplacementCodeuiTexCoord2fNormal3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  public  void glReplacementCodeuiTexCoord2fNormal3fVertex3fvSUN(java.nio.IntBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glReplacementCodeuiTexCoord2fNormal3fVertex3fvSUN("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glReplacementCodeuiTexCoord2fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glReplacementCodeuiTexCoord2fNormal3fVertex3fvSUN(int[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5,float[] arg6,int arg7)
  {
    printIndent();
    print("glReplacementCodeuiTexCoord2fNormal3fVertex3fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+","+dumpArray(arg6)+","+arg7+")");
    downstreamGL.glReplacementCodeuiTexCoord2fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glReplacementCodeuiTexCoord2fVertex3fSUN(int arg0,float arg1,float arg2,float arg3,float arg4,float arg5)
  {
    printIndent();
    print("glReplacementCodeuiTexCoord2fVertex3fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glReplacementCodeuiTexCoord2fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glReplacementCodeuiTexCoord2fVertex3fvSUN(java.nio.IntBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glReplacementCodeuiTexCoord2fVertex3fvSUN("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glReplacementCodeuiTexCoord2fVertex3fvSUN(arg0,arg1,arg2);
    println("");
  }
  public  void glReplacementCodeuiTexCoord2fVertex3fvSUN(int[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5)
  {
    printIndent();
    print("glReplacementCodeuiTexCoord2fVertex3fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glReplacementCodeuiTexCoord2fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glReplacementCodeuiVertex3fSUN(int arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glReplacementCodeuiVertex3fSUN("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glReplacementCodeuiVertex3fSUN(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glReplacementCodeuiVertex3fvSUN(java.nio.IntBuffer arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glReplacementCodeuiVertex3fvSUN("+arg0+","+arg1+")");
    downstreamGL.glReplacementCodeuiVertex3fvSUN(arg0,arg1);
    println("");
  }
  public  void glReplacementCodeuiVertex3fvSUN(int[] arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glReplacementCodeuiVertex3fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glReplacementCodeuiVertex3fvSUN(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glRequestResidentProgramsNV(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glRequestResidentProgramsNV("+arg0+","+arg1+")");
    downstreamGL.glRequestResidentProgramsNV(arg0,arg1);
    println("");
  }
  public  void glRequestResidentProgramsNV(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glRequestResidentProgramsNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glRequestResidentProgramsNV(arg0,arg1,arg2);
    println("");
  }
  public  void glResetHistogram(int arg0)
  {
    printIndent();
    print("glResetHistogram("+arg0+")");
    downstreamGL.glResetHistogram(arg0);
    println("");
  }
  public  void glResetMinmax(int arg0)
  {
    printIndent();
    print("glResetMinmax("+arg0+")");
    downstreamGL.glResetMinmax(arg0);
    println("");
  }
  public  void glResizeBuffersMESA()
  {
    printIndent();
    print("glResizeBuffersMESA("+")");
    downstreamGL.glResizeBuffersMESA();
    println("");
  }
  public  void glRotated(double arg0,double arg1,double arg2,double arg3)
  {
    printIndent();
    print("glRotated("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glRotated(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glRotatef(float arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glRotatef("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glRotatef(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glSampleCoverage(float arg0,boolean arg1)
  {
    printIndent();
    print("glSampleCoverage("+arg0+","+arg1+")");
    downstreamGL.glSampleCoverage(arg0,arg1);
    println("");
  }
  public  void glSampleMapATI(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glSampleMapATI("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSampleMapATI(arg0,arg1,arg2);
    println("");
  }
  public  void glSampleMaskEXT(float arg0,boolean arg1)
  {
    printIndent();
    print("glSampleMaskEXT("+arg0+","+arg1+")");
    downstreamGL.glSampleMaskEXT(arg0,arg1);
    println("");
  }
  public  void glSampleMaskSGIS(float arg0,boolean arg1)
  {
    printIndent();
    print("glSampleMaskSGIS("+arg0+","+arg1+")");
    downstreamGL.glSampleMaskSGIS(arg0,arg1);
    println("");
  }
  public  void glSamplePatternEXT(int arg0)
  {
    printIndent();
    print("glSamplePatternEXT("+arg0+")");
    downstreamGL.glSamplePatternEXT(arg0);
    println("");
  }
  public  void glSamplePatternSGIS(int arg0)
  {
    printIndent();
    print("glSamplePatternSGIS("+arg0+")");
    downstreamGL.glSamplePatternSGIS(arg0);
    println("");
  }
  public  void glScaled(double arg0,double arg1,double arg2)
  {
    printIndent();
    print("glScaled("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glScaled(arg0,arg1,arg2);
    println("");
  }
  public  void glScalef(float arg0,float arg1,float arg2)
  {
    printIndent();
    print("glScalef("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glScalef(arg0,arg1,arg2);
    println("");
  }
  public  void glScissor(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glScissor("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glScissor(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glSecondaryColor3b(byte arg0,byte arg1,byte arg2)
  {
    printIndent();
    print("glSecondaryColor3b("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSecondaryColor3b(arg0,arg1,arg2);
    println("");
  }
  public  void glSecondaryColor3bEXT(byte arg0,byte arg1,byte arg2)
  {
    printIndent();
    print("glSecondaryColor3bEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSecondaryColor3bEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glSecondaryColor3bv(java.nio.ByteBuffer arg0)
  {
    printIndent();
    print("glSecondaryColor3bv("+arg0+")");
    downstreamGL.glSecondaryColor3bv(arg0);
    println("");
  }
  public  void glSecondaryColor3bv(byte[] arg0,int arg1)
  {
    printIndent();
    print("glSecondaryColor3bv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glSecondaryColor3bv(arg0,arg1);
    println("");
  }
  public  void glSecondaryColor3bvEXT(java.nio.ByteBuffer arg0)
  {
    printIndent();
    print("glSecondaryColor3bvEXT("+arg0+")");
    downstreamGL.glSecondaryColor3bvEXT(arg0);
    println("");
  }
  public  void glSecondaryColor3bvEXT(byte[] arg0,int arg1)
  {
    printIndent();
    print("glSecondaryColor3bvEXT("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glSecondaryColor3bvEXT(arg0,arg1);
    println("");
  }
  public  void glSecondaryColor3d(double arg0,double arg1,double arg2)
  {
    printIndent();
    print("glSecondaryColor3d("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSecondaryColor3d(arg0,arg1,arg2);
    println("");
  }
  public  void glSecondaryColor3dEXT(double arg0,double arg1,double arg2)
  {
    printIndent();
    print("glSecondaryColor3dEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSecondaryColor3dEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glSecondaryColor3dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glSecondaryColor3dv("+arg0+")");
    downstreamGL.glSecondaryColor3dv(arg0);
    println("");
  }
  public  void glSecondaryColor3dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glSecondaryColor3dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glSecondaryColor3dv(arg0,arg1);
    println("");
  }
  public  void glSecondaryColor3dvEXT(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glSecondaryColor3dvEXT("+arg0+")");
    downstreamGL.glSecondaryColor3dvEXT(arg0);
    println("");
  }
  public  void glSecondaryColor3dvEXT(double[] arg0,int arg1)
  {
    printIndent();
    print("glSecondaryColor3dvEXT("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glSecondaryColor3dvEXT(arg0,arg1);
    println("");
  }
  public  void glSecondaryColor3f(float arg0,float arg1,float arg2)
  {
    printIndent();
    print("glSecondaryColor3f("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSecondaryColor3f(arg0,arg1,arg2);
    println("");
  }
  public  void glSecondaryColor3fEXT(float arg0,float arg1,float arg2)
  {
    printIndent();
    print("glSecondaryColor3fEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSecondaryColor3fEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glSecondaryColor3fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glSecondaryColor3fv("+arg0+")");
    downstreamGL.glSecondaryColor3fv(arg0);
    println("");
  }
  public  void glSecondaryColor3fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glSecondaryColor3fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glSecondaryColor3fv(arg0,arg1);
    println("");
  }
  public  void glSecondaryColor3fvEXT(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glSecondaryColor3fvEXT("+arg0+")");
    downstreamGL.glSecondaryColor3fvEXT(arg0);
    println("");
  }
  public  void glSecondaryColor3fvEXT(float[] arg0,int arg1)
  {
    printIndent();
    print("glSecondaryColor3fvEXT("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glSecondaryColor3fvEXT(arg0,arg1);
    println("");
  }
  public  void glSecondaryColor3hNV(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glSecondaryColor3hNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSecondaryColor3hNV(arg0,arg1,arg2);
    println("");
  }
  public  void glSecondaryColor3hvNV(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glSecondaryColor3hvNV("+arg0+")");
    downstreamGL.glSecondaryColor3hvNV(arg0);
    println("");
  }
  public  void glSecondaryColor3hvNV(short[] arg0,int arg1)
  {
    printIndent();
    print("glSecondaryColor3hvNV("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glSecondaryColor3hvNV(arg0,arg1);
    println("");
  }
  public  void glSecondaryColor3i(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glSecondaryColor3i("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSecondaryColor3i(arg0,arg1,arg2);
    println("");
  }
  public  void glSecondaryColor3iEXT(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glSecondaryColor3iEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSecondaryColor3iEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glSecondaryColor3iv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glSecondaryColor3iv("+arg0+")");
    downstreamGL.glSecondaryColor3iv(arg0);
    println("");
  }
  public  void glSecondaryColor3iv(int[] arg0,int arg1)
  {
    printIndent();
    print("glSecondaryColor3iv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glSecondaryColor3iv(arg0,arg1);
    println("");
  }
  public  void glSecondaryColor3ivEXT(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glSecondaryColor3ivEXT("+arg0+")");
    downstreamGL.glSecondaryColor3ivEXT(arg0);
    println("");
  }
  public  void glSecondaryColor3ivEXT(int[] arg0,int arg1)
  {
    printIndent();
    print("glSecondaryColor3ivEXT("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glSecondaryColor3ivEXT(arg0,arg1);
    println("");
  }
  public  void glSecondaryColor3s(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glSecondaryColor3s("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSecondaryColor3s(arg0,arg1,arg2);
    println("");
  }
  public  void glSecondaryColor3sEXT(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glSecondaryColor3sEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSecondaryColor3sEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glSecondaryColor3sv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glSecondaryColor3sv("+arg0+")");
    downstreamGL.glSecondaryColor3sv(arg0);
    println("");
  }
  public  void glSecondaryColor3sv(short[] arg0,int arg1)
  {
    printIndent();
    print("glSecondaryColor3sv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glSecondaryColor3sv(arg0,arg1);
    println("");
  }
  public  void glSecondaryColor3svEXT(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glSecondaryColor3svEXT("+arg0+")");
    downstreamGL.glSecondaryColor3svEXT(arg0);
    println("");
  }
  public  void glSecondaryColor3svEXT(short[] arg0,int arg1)
  {
    printIndent();
    print("glSecondaryColor3svEXT("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glSecondaryColor3svEXT(arg0,arg1);
    println("");
  }
  public  void glSecondaryColor3ub(byte arg0,byte arg1,byte arg2)
  {
    printIndent();
    print("glSecondaryColor3ub("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSecondaryColor3ub(arg0,arg1,arg2);
    println("");
  }
  public  void glSecondaryColor3ubEXT(byte arg0,byte arg1,byte arg2)
  {
    printIndent();
    print("glSecondaryColor3ubEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSecondaryColor3ubEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glSecondaryColor3ubv(java.nio.ByteBuffer arg0)
  {
    printIndent();
    print("glSecondaryColor3ubv("+arg0+")");
    downstreamGL.glSecondaryColor3ubv(arg0);
    println("");
  }
  public  void glSecondaryColor3ubv(byte[] arg0,int arg1)
  {
    printIndent();
    print("glSecondaryColor3ubv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glSecondaryColor3ubv(arg0,arg1);
    println("");
  }
  public  void glSecondaryColor3ubvEXT(java.nio.ByteBuffer arg0)
  {
    printIndent();
    print("glSecondaryColor3ubvEXT("+arg0+")");
    downstreamGL.glSecondaryColor3ubvEXT(arg0);
    println("");
  }
  public  void glSecondaryColor3ubvEXT(byte[] arg0,int arg1)
  {
    printIndent();
    print("glSecondaryColor3ubvEXT("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glSecondaryColor3ubvEXT(arg0,arg1);
    println("");
  }
  public  void glSecondaryColor3ui(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glSecondaryColor3ui("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSecondaryColor3ui(arg0,arg1,arg2);
    println("");
  }
  public  void glSecondaryColor3uiEXT(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glSecondaryColor3uiEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSecondaryColor3uiEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glSecondaryColor3uiv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glSecondaryColor3uiv("+arg0+")");
    downstreamGL.glSecondaryColor3uiv(arg0);
    println("");
  }
  public  void glSecondaryColor3uiv(int[] arg0,int arg1)
  {
    printIndent();
    print("glSecondaryColor3uiv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glSecondaryColor3uiv(arg0,arg1);
    println("");
  }
  public  void glSecondaryColor3uivEXT(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glSecondaryColor3uivEXT("+arg0+")");
    downstreamGL.glSecondaryColor3uivEXT(arg0);
    println("");
  }
  public  void glSecondaryColor3uivEXT(int[] arg0,int arg1)
  {
    printIndent();
    print("glSecondaryColor3uivEXT("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glSecondaryColor3uivEXT(arg0,arg1);
    println("");
  }
  public  void glSecondaryColor3us(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glSecondaryColor3us("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSecondaryColor3us(arg0,arg1,arg2);
    println("");
  }
  public  void glSecondaryColor3usEXT(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glSecondaryColor3usEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSecondaryColor3usEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glSecondaryColor3usv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glSecondaryColor3usv("+arg0+")");
    downstreamGL.glSecondaryColor3usv(arg0);
    println("");
  }
  public  void glSecondaryColor3usv(short[] arg0,int arg1)
  {
    printIndent();
    print("glSecondaryColor3usv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glSecondaryColor3usv(arg0,arg1);
    println("");
  }
  public  void glSecondaryColor3usvEXT(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glSecondaryColor3usvEXT("+arg0+")");
    downstreamGL.glSecondaryColor3usvEXT(arg0);
    println("");
  }
  public  void glSecondaryColor3usvEXT(short[] arg0,int arg1)
  {
    printIndent();
    print("glSecondaryColor3usvEXT("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glSecondaryColor3usvEXT(arg0,arg1);
    println("");
  }
  public  void glSecondaryColorPointer(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print("glSecondaryColorPointer("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glSecondaryColorPointer(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glSecondaryColorPointer(int arg0,int arg1,int arg2,long arg3)
  {
    printIndent();
    print("glSecondaryColorPointer("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glSecondaryColorPointer(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glSecondaryColorPointerEXT(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print("glSecondaryColorPointerEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glSecondaryColorPointerEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glSecondaryColorPointerEXT(int arg0,int arg1,int arg2,long arg3)
  {
    printIndent();
    print("glSecondaryColorPointerEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glSecondaryColorPointerEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glSelectBuffer(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glSelectBuffer("+arg0+","+arg1+")");
    downstreamGL.glSelectBuffer(arg0,arg1);
    println("");
  }
  public  void glSeparableFilter2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,java.nio.Buffer arg6,java.nio.Buffer arg7)
  {
    printIndent();
    print("glSeparableFilter2D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+")");
    downstreamGL.glSeparableFilter2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glSeparableFilter2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,long arg6,long arg7)
  {
    printIndent();
    print("glSeparableFilter2D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+")");
    downstreamGL.glSeparableFilter2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glSetFenceAPPLE(int arg0)
  {
    printIndent();
    print("glSetFenceAPPLE("+arg0+")");
    downstreamGL.glSetFenceAPPLE(arg0);
    println("");
  }
  public  void glSetFenceNV(int arg0,int arg1)
  {
    printIndent();
    print("glSetFenceNV("+arg0+","+arg1+")");
    downstreamGL.glSetFenceNV(arg0,arg1);
    println("");
  }
  public  void glSetFragmentShaderConstantATI(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glSetFragmentShaderConstantATI("+arg0+","+arg1+")");
    downstreamGL.glSetFragmentShaderConstantATI(arg0,arg1);
    println("");
  }
  public  void glSetFragmentShaderConstantATI(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glSetFragmentShaderConstantATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glSetFragmentShaderConstantATI(arg0,arg1,arg2);
    println("");
  }
  public  void glSetInvariantEXT(int arg0,int arg1,java.nio.Buffer arg2)
  {
    printIndent();
    print("glSetInvariantEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSetInvariantEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glSetLocalConstantEXT(int arg0,int arg1,java.nio.Buffer arg2)
  {
    printIndent();
    print("glSetLocalConstantEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSetLocalConstantEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glShadeModel(int arg0)
  {
    printIndent();
    print("glShadeModel("+arg0+")");
    downstreamGL.glShadeModel(arg0);
    println("");
  }
  public  void glShaderOp1EXT(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glShaderOp1EXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glShaderOp1EXT(arg0,arg1,arg2);
    println("");
  }
  public  void glShaderOp2EXT(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glShaderOp2EXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glShaderOp2EXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glShaderOp3EXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glShaderOp3EXT("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glShaderOp3EXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glShaderSource(int arg0,int arg1,java.lang.String[] arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print("glShaderSource("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glShaderSource(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glShaderSource(int arg0,int arg1,java.lang.String[] arg2,int[] arg3,int arg4)
  {
    printIndent();
    print("glShaderSource("+arg0+","+arg1+","+dumpArray(arg2)+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glShaderSource(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glShaderSourceARB(int arg0,int arg1,java.lang.String[] arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print("glShaderSourceARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glShaderSourceARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glShaderSourceARB(int arg0,int arg1,java.lang.String[] arg2,int[] arg3,int arg4)
  {
    printIndent();
    print("glShaderSourceARB("+arg0+","+arg1+","+dumpArray(arg2)+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glShaderSourceARB(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glSharpenTexFuncSGIS(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glSharpenTexFuncSGIS("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glSharpenTexFuncSGIS(arg0,arg1,arg2);
    println("");
  }
  public  void glSharpenTexFuncSGIS(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glSharpenTexFuncSGIS("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glSharpenTexFuncSGIS(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glSpriteParameterfSGIX(int arg0,float arg1)
  {
    printIndent();
    print("glSpriteParameterfSGIX("+arg0+","+arg1+")");
    downstreamGL.glSpriteParameterfSGIX(arg0,arg1);
    println("");
  }
  public  void glSpriteParameterfvSGIX(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glSpriteParameterfvSGIX("+arg0+","+arg1+")");
    downstreamGL.glSpriteParameterfvSGIX(arg0,arg1);
    println("");
  }
  public  void glSpriteParameterfvSGIX(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glSpriteParameterfvSGIX("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glSpriteParameterfvSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glSpriteParameteriSGIX(int arg0,int arg1)
  {
    printIndent();
    print("glSpriteParameteriSGIX("+arg0+","+arg1+")");
    downstreamGL.glSpriteParameteriSGIX(arg0,arg1);
    println("");
  }
  public  void glSpriteParameterivSGIX(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glSpriteParameterivSGIX("+arg0+","+arg1+")");
    downstreamGL.glSpriteParameterivSGIX(arg0,arg1);
    println("");
  }
  public  void glSpriteParameterivSGIX(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glSpriteParameterivSGIX("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glSpriteParameterivSGIX(arg0,arg1,arg2);
    println("");
  }
  public  void glStartInstrumentsSGIX()
  {
    printIndent();
    print("glStartInstrumentsSGIX("+")");
    downstreamGL.glStartInstrumentsSGIX();
    println("");
  }
  public  void glStencilClearTagEXT(int arg0,int arg1)
  {
    printIndent();
    print("glStencilClearTagEXT("+arg0+","+arg1+")");
    downstreamGL.glStencilClearTagEXT(arg0,arg1);
    println("");
  }
  public  void glStencilFunc(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glStencilFunc("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glStencilFunc(arg0,arg1,arg2);
    println("");
  }
  public  void glStencilFuncSeparate(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glStencilFuncSeparate("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glStencilFuncSeparate(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glStencilFuncSeparateATI(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glStencilFuncSeparateATI("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glStencilFuncSeparateATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glStencilMask(int arg0)
  {
    printIndent();
    print("glStencilMask("+arg0+")");
    downstreamGL.glStencilMask(arg0);
    println("");
  }
  public  void glStencilMaskSeparate(int arg0,int arg1)
  {
    printIndent();
    print("glStencilMaskSeparate("+arg0+","+arg1+")");
    downstreamGL.glStencilMaskSeparate(arg0,arg1);
    println("");
  }
  public  void glStencilOp(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glStencilOp("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glStencilOp(arg0,arg1,arg2);
    println("");
  }
  public  void glStencilOpSeparate(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glStencilOpSeparate("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glStencilOpSeparate(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glStencilOpSeparateATI(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glStencilOpSeparateATI("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glStencilOpSeparateATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glStopInstrumentsSGIX(int arg0)
  {
    printIndent();
    print("glStopInstrumentsSGIX("+arg0+")");
    downstreamGL.glStopInstrumentsSGIX(arg0);
    println("");
  }
  public  void glStringMarkerGREMEDY(int arg0,java.nio.Buffer arg1)
  {
    printIndent();
    print("glStringMarkerGREMEDY("+arg0+","+arg1+")");
    downstreamGL.glStringMarkerGREMEDY(arg0,arg1);
    println("");
  }
  public  void glSwapAPPLE()
  {
    printIndent();
    print("glSwapAPPLE("+")");
    downstreamGL.glSwapAPPLE();
    println("");
  }
  public  void glSwizzleEXT(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print("glSwizzleEXT("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glSwizzleEXT(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glTagSampleBufferSGIX()
  {
    printIndent();
    print("glTagSampleBufferSGIX("+")");
    downstreamGL.glTagSampleBufferSGIX();
    println("");
  }
  public  void glTbufferMask3DFX(int arg0)
  {
    printIndent();
    print("glTbufferMask3DFX("+arg0+")");
    downstreamGL.glTbufferMask3DFX(arg0);
    println("");
  }
  public  boolean glTestFenceAPPLE(int arg0)
  {
    printIndent();
    print("glTestFenceAPPLE("+arg0+")");
    boolean _res = downstreamGL.glTestFenceAPPLE(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glTestFenceNV(int arg0)
  {
    printIndent();
    print("glTestFenceNV("+arg0+")");
    boolean _res = downstreamGL.glTestFenceNV(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glTestObjectAPPLE(int arg0,int arg1)
  {
    printIndent();
    print("glTestObjectAPPLE("+arg0+","+arg1+")");
    boolean _res = downstreamGL.glTestObjectAPPLE(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  public  void glTexBufferEXT(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glTexBufferEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexBufferEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glTexBumpParameterfvATI(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glTexBumpParameterfvATI("+arg0+","+arg1+")");
    downstreamGL.glTexBumpParameterfvATI(arg0,arg1);
    println("");
  }
  public  void glTexBumpParameterfvATI(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glTexBumpParameterfvATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glTexBumpParameterfvATI(arg0,arg1,arg2);
    println("");
  }
  public  void glTexBumpParameterivATI(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glTexBumpParameterivATI("+arg0+","+arg1+")");
    downstreamGL.glTexBumpParameterivATI(arg0,arg1);
    println("");
  }
  public  void glTexBumpParameterivATI(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glTexBumpParameterivATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glTexBumpParameterivATI(arg0,arg1,arg2);
    println("");
  }
  public  void glTexCoord1d(double arg0)
  {
    printIndent();
    print("glTexCoord1d("+arg0+")");
    downstreamGL.glTexCoord1d(arg0);
    println("");
  }
  public  void glTexCoord1dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glTexCoord1dv("+arg0+")");
    downstreamGL.glTexCoord1dv(arg0);
    println("");
  }
  public  void glTexCoord1dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord1dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord1dv(arg0,arg1);
    println("");
  }
  public  void glTexCoord1f(float arg0)
  {
    printIndent();
    print("glTexCoord1f("+arg0+")");
    downstreamGL.glTexCoord1f(arg0);
    println("");
  }
  public  void glTexCoord1fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glTexCoord1fv("+arg0+")");
    downstreamGL.glTexCoord1fv(arg0);
    println("");
  }
  public  void glTexCoord1fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord1fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord1fv(arg0,arg1);
    println("");
  }
  public  void glTexCoord1hNV(short arg0)
  {
    printIndent();
    print("glTexCoord1hNV("+arg0+")");
    downstreamGL.glTexCoord1hNV(arg0);
    println("");
  }
  public  void glTexCoord1hvNV(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glTexCoord1hvNV("+arg0+")");
    downstreamGL.glTexCoord1hvNV(arg0);
    println("");
  }
  public  void glTexCoord1hvNV(short[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord1hvNV("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord1hvNV(arg0,arg1);
    println("");
  }
  public  void glTexCoord1i(int arg0)
  {
    printIndent();
    print("glTexCoord1i("+arg0+")");
    downstreamGL.glTexCoord1i(arg0);
    println("");
  }
  public  void glTexCoord1iv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glTexCoord1iv("+arg0+")");
    downstreamGL.glTexCoord1iv(arg0);
    println("");
  }
  public  void glTexCoord1iv(int[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord1iv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord1iv(arg0,arg1);
    println("");
  }
  public  void glTexCoord1s(short arg0)
  {
    printIndent();
    print("glTexCoord1s("+arg0+")");
    downstreamGL.glTexCoord1s(arg0);
    println("");
  }
  public  void glTexCoord1sv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glTexCoord1sv("+arg0+")");
    downstreamGL.glTexCoord1sv(arg0);
    println("");
  }
  public  void glTexCoord1sv(short[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord1sv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord1sv(arg0,arg1);
    println("");
  }
  public  void glTexCoord2d(double arg0,double arg1)
  {
    printIndent();
    print("glTexCoord2d("+arg0+","+arg1+")");
    downstreamGL.glTexCoord2d(arg0,arg1);
    println("");
  }
  public  void glTexCoord2dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glTexCoord2dv("+arg0+")");
    downstreamGL.glTexCoord2dv(arg0);
    println("");
  }
  public  void glTexCoord2dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord2dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord2dv(arg0,arg1);
    println("");
  }
  public  void glTexCoord2f(float arg0,float arg1)
  {
    printIndent();
    print("glTexCoord2f("+arg0+","+arg1+")");
    downstreamGL.glTexCoord2f(arg0,arg1);
    println("");
  }
  public  void glTexCoord2fColor3fVertex3fSUN(float arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7)
  {
    printIndent();
    print("glTexCoord2fColor3fVertex3fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+")");
    downstreamGL.glTexCoord2fColor3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glTexCoord2fColor3fVertex3fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glTexCoord2fColor3fVertex3fvSUN("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexCoord2fColor3fVertex3fvSUN(arg0,arg1,arg2);
    println("");
  }
  public  void glTexCoord2fColor3fVertex3fvSUN(float[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5)
  {
    printIndent();
    print("glTexCoord2fColor3fVertex3fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glTexCoord2fColor3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glTexCoord2fColor4fNormal3fVertex3fSUN(float arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7,float arg8,float arg9,float arg10,float arg11)
  {
    printIndent();
    print("glTexCoord2fColor4fNormal3fVertex3fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+","+arg11+")");
    downstreamGL.glTexCoord2fColor4fNormal3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11);
    println("");
  }
  public  void glTexCoord2fColor4fNormal3fVertex3fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glTexCoord2fColor4fNormal3fVertex3fvSUN("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glTexCoord2fColor4fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexCoord2fColor4fNormal3fVertex3fvSUN(float[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5,float[] arg6,int arg7)
  {
    printIndent();
    print("glTexCoord2fColor4fNormal3fVertex3fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+","+dumpArray(arg6)+","+arg7+")");
    downstreamGL.glTexCoord2fColor4fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glTexCoord2fColor4ubVertex3fSUN(float arg0,float arg1,byte arg2,byte arg3,byte arg4,byte arg5,float arg6,float arg7,float arg8)
  {
    printIndent();
    print("glTexCoord2fColor4ubVertex3fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+")");
    downstreamGL.glTexCoord2fColor4ubVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  public  void glTexCoord2fColor4ubVertex3fvSUN(java.nio.FloatBuffer arg0,java.nio.ByteBuffer arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glTexCoord2fColor4ubVertex3fvSUN("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexCoord2fColor4ubVertex3fvSUN(arg0,arg1,arg2);
    println("");
  }
  public  void glTexCoord2fColor4ubVertex3fvSUN(float[] arg0,int arg1,byte[] arg2,int arg3,float[] arg4,int arg5)
  {
    printIndent();
    print("glTexCoord2fColor4ubVertex3fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glTexCoord2fColor4ubVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glTexCoord2fNormal3fVertex3fSUN(float arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7)
  {
    printIndent();
    print("glTexCoord2fNormal3fVertex3fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+")");
    downstreamGL.glTexCoord2fNormal3fVertex3fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glTexCoord2fNormal3fVertex3fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glTexCoord2fNormal3fVertex3fvSUN("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexCoord2fNormal3fVertex3fvSUN(arg0,arg1,arg2);
    println("");
  }
  public  void glTexCoord2fNormal3fVertex3fvSUN(float[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5)
  {
    printIndent();
    print("glTexCoord2fNormal3fVertex3fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+")");
    downstreamGL.glTexCoord2fNormal3fVertex3fvSUN(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glTexCoord2fVertex3fSUN(float arg0,float arg1,float arg2,float arg3,float arg4)
  {
    printIndent();
    print("glTexCoord2fVertex3fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glTexCoord2fVertex3fSUN(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glTexCoord2fVertex3fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glTexCoord2fVertex3fvSUN("+arg0+","+arg1+")");
    downstreamGL.glTexCoord2fVertex3fvSUN(arg0,arg1);
    println("");
  }
  public  void glTexCoord2fVertex3fvSUN(float[] arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glTexCoord2fVertex3fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glTexCoord2fVertex3fvSUN(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexCoord2fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glTexCoord2fv("+arg0+")");
    downstreamGL.glTexCoord2fv(arg0);
    println("");
  }
  public  void glTexCoord2fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord2fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord2fv(arg0,arg1);
    println("");
  }
  public  void glTexCoord2hNV(short arg0,short arg1)
  {
    printIndent();
    print("glTexCoord2hNV("+arg0+","+arg1+")");
    downstreamGL.glTexCoord2hNV(arg0,arg1);
    println("");
  }
  public  void glTexCoord2hvNV(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glTexCoord2hvNV("+arg0+")");
    downstreamGL.glTexCoord2hvNV(arg0);
    println("");
  }
  public  void glTexCoord2hvNV(short[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord2hvNV("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord2hvNV(arg0,arg1);
    println("");
  }
  public  void glTexCoord2i(int arg0,int arg1)
  {
    printIndent();
    print("glTexCoord2i("+arg0+","+arg1+")");
    downstreamGL.glTexCoord2i(arg0,arg1);
    println("");
  }
  public  void glTexCoord2iv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glTexCoord2iv("+arg0+")");
    downstreamGL.glTexCoord2iv(arg0);
    println("");
  }
  public  void glTexCoord2iv(int[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord2iv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord2iv(arg0,arg1);
    println("");
  }
  public  void glTexCoord2s(short arg0,short arg1)
  {
    printIndent();
    print("glTexCoord2s("+arg0+","+arg1+")");
    downstreamGL.glTexCoord2s(arg0,arg1);
    println("");
  }
  public  void glTexCoord2sv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glTexCoord2sv("+arg0+")");
    downstreamGL.glTexCoord2sv(arg0);
    println("");
  }
  public  void glTexCoord2sv(short[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord2sv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord2sv(arg0,arg1);
    println("");
  }
  public  void glTexCoord3d(double arg0,double arg1,double arg2)
  {
    printIndent();
    print("glTexCoord3d("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexCoord3d(arg0,arg1,arg2);
    println("");
  }
  public  void glTexCoord3dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glTexCoord3dv("+arg0+")");
    downstreamGL.glTexCoord3dv(arg0);
    println("");
  }
  public  void glTexCoord3dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord3dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord3dv(arg0,arg1);
    println("");
  }
  public  void glTexCoord3f(float arg0,float arg1,float arg2)
  {
    printIndent();
    print("glTexCoord3f("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexCoord3f(arg0,arg1,arg2);
    println("");
  }
  public  void glTexCoord3fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glTexCoord3fv("+arg0+")");
    downstreamGL.glTexCoord3fv(arg0);
    println("");
  }
  public  void glTexCoord3fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord3fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord3fv(arg0,arg1);
    println("");
  }
  public  void glTexCoord3hNV(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glTexCoord3hNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexCoord3hNV(arg0,arg1,arg2);
    println("");
  }
  public  void glTexCoord3hvNV(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glTexCoord3hvNV("+arg0+")");
    downstreamGL.glTexCoord3hvNV(arg0);
    println("");
  }
  public  void glTexCoord3hvNV(short[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord3hvNV("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord3hvNV(arg0,arg1);
    println("");
  }
  public  void glTexCoord3i(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glTexCoord3i("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexCoord3i(arg0,arg1,arg2);
    println("");
  }
  public  void glTexCoord3iv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glTexCoord3iv("+arg0+")");
    downstreamGL.glTexCoord3iv(arg0);
    println("");
  }
  public  void glTexCoord3iv(int[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord3iv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord3iv(arg0,arg1);
    println("");
  }
  public  void glTexCoord3s(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glTexCoord3s("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexCoord3s(arg0,arg1,arg2);
    println("");
  }
  public  void glTexCoord3sv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glTexCoord3sv("+arg0+")");
    downstreamGL.glTexCoord3sv(arg0);
    println("");
  }
  public  void glTexCoord3sv(short[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord3sv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord3sv(arg0,arg1);
    println("");
  }
  public  void glTexCoord4d(double arg0,double arg1,double arg2,double arg3)
  {
    printIndent();
    print("glTexCoord4d("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glTexCoord4d(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexCoord4dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glTexCoord4dv("+arg0+")");
    downstreamGL.glTexCoord4dv(arg0);
    println("");
  }
  public  void glTexCoord4dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord4dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord4dv(arg0,arg1);
    println("");
  }
  public  void glTexCoord4f(float arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glTexCoord4f("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glTexCoord4f(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexCoord4fColor4fNormal3fVertex4fSUN(float arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7,float arg8,float arg9,float arg10,float arg11,float arg12,float arg13,float arg14)
  {
    printIndent();
    print("glTexCoord4fColor4fNormal3fVertex4fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+","+arg11+","+arg12+","+arg13+","+arg14+")");
    downstreamGL.glTexCoord4fColor4fNormal3fVertex4fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14);
    println("");
  }
  public  void glTexCoord4fColor4fNormal3fVertex4fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1,java.nio.FloatBuffer arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glTexCoord4fColor4fNormal3fVertex4fvSUN("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glTexCoord4fColor4fNormal3fVertex4fvSUN(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexCoord4fColor4fNormal3fVertex4fvSUN(float[] arg0,int arg1,float[] arg2,int arg3,float[] arg4,int arg5,float[] arg6,int arg7)
  {
    printIndent();
    print("glTexCoord4fColor4fNormal3fVertex4fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+","+dumpArray(arg4)+","+arg5+","+dumpArray(arg6)+","+arg7+")");
    downstreamGL.glTexCoord4fColor4fNormal3fVertex4fvSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glTexCoord4fVertex4fSUN(float arg0,float arg1,float arg2,float arg3,float arg4,float arg5,float arg6,float arg7)
  {
    printIndent();
    print("glTexCoord4fVertex4fSUN("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+")");
    downstreamGL.glTexCoord4fVertex4fSUN(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glTexCoord4fVertex4fvSUN(java.nio.FloatBuffer arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glTexCoord4fVertex4fvSUN("+arg0+","+arg1+")");
    downstreamGL.glTexCoord4fVertex4fvSUN(arg0,arg1);
    println("");
  }
  public  void glTexCoord4fVertex4fvSUN(float[] arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glTexCoord4fVertex4fvSUN("+dumpArray(arg0)+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glTexCoord4fVertex4fvSUN(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexCoord4fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glTexCoord4fv("+arg0+")");
    downstreamGL.glTexCoord4fv(arg0);
    println("");
  }
  public  void glTexCoord4fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord4fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord4fv(arg0,arg1);
    println("");
  }
  public  void glTexCoord4hNV(short arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glTexCoord4hNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glTexCoord4hNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexCoord4hvNV(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glTexCoord4hvNV("+arg0+")");
    downstreamGL.glTexCoord4hvNV(arg0);
    println("");
  }
  public  void glTexCoord4hvNV(short[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord4hvNV("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord4hvNV(arg0,arg1);
    println("");
  }
  public  void glTexCoord4i(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glTexCoord4i("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glTexCoord4i(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexCoord4iv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glTexCoord4iv("+arg0+")");
    downstreamGL.glTexCoord4iv(arg0);
    println("");
  }
  public  void glTexCoord4iv(int[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord4iv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord4iv(arg0,arg1);
    println("");
  }
  public  void glTexCoord4s(short arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glTexCoord4s("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glTexCoord4s(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexCoord4sv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glTexCoord4sv("+arg0+")");
    downstreamGL.glTexCoord4sv(arg0);
    println("");
  }
  public  void glTexCoord4sv(short[] arg0,int arg1)
  {
    printIndent();
    print("glTexCoord4sv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glTexCoord4sv(arg0,arg1);
    println("");
  }
  public  void glTexCoordPointer(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print("glTexCoordPointer("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glTexCoordPointer(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexCoordPointer(int arg0,int arg1,int arg2,long arg3)
  {
    printIndent();
    print("glTexCoordPointer("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glTexCoordPointer(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexEnvf(int arg0,int arg1,float arg2)
  {
    printIndent();
    print("glTexEnvf("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexEnvf(arg0,arg1,arg2);
    println("");
  }
  public  void glTexEnvfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glTexEnvfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexEnvfv(arg0,arg1,arg2);
    println("");
  }
  public  void glTexEnvfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glTexEnvfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glTexEnvfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexEnvi(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glTexEnvi("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexEnvi(arg0,arg1,arg2);
    println("");
  }
  public  void glTexEnviv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glTexEnviv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexEnviv(arg0,arg1,arg2);
    println("");
  }
  public  void glTexEnviv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glTexEnviv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glTexEnviv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexFilterFuncSGIS(int arg0,int arg1,int arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glTexFilterFuncSGIS("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glTexFilterFuncSGIS(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexFilterFuncSGIS(int arg0,int arg1,int arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glTexFilterFuncSGIS("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glTexFilterFuncSGIS(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glTexGend(int arg0,int arg1,double arg2)
  {
    printIndent();
    print("glTexGend("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexGend(arg0,arg1,arg2);
    println("");
  }
  public  void glTexGendv(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
    printIndent();
    print("glTexGendv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexGendv(arg0,arg1,arg2);
    println("");
  }
  public  void glTexGendv(int arg0,int arg1,double[] arg2,int arg3)
  {
    printIndent();
    print("glTexGendv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glTexGendv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexGenf(int arg0,int arg1,float arg2)
  {
    printIndent();
    print("glTexGenf("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexGenf(arg0,arg1,arg2);
    println("");
  }
  public  void glTexGenfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glTexGenfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexGenfv(arg0,arg1,arg2);
    println("");
  }
  public  void glTexGenfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glTexGenfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glTexGenfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexGeni(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glTexGeni("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexGeni(arg0,arg1,arg2);
    println("");
  }
  public  void glTexGeniv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glTexGeniv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexGeniv(arg0,arg1,arg2);
    println("");
  }
  public  void glTexGeniv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glTexGeniv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glTexGeniv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,java.nio.Buffer arg7)
  {
    printIndent();
    print("glTexImage1D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+")");
    downstreamGL.glTexImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glTexImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,long arg7)
  {
    printIndent();
    print("glTexImage1D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+")");
    downstreamGL.glTexImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  public  void glTexImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,java.nio.Buffer arg8)
  {
    printIndent();
    print("glTexImage2D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+")");
    downstreamGL.glTexImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  public  void glTexImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,long arg8)
  {
    printIndent();
    print("glTexImage2D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+")");
    downstreamGL.glTexImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  public  void glTexImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,java.nio.Buffer arg9)
  {
    printIndent();
    print("glTexImage3D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+")");
    downstreamGL.glTexImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    println("");
  }
  public  void glTexImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,long arg9)
  {
    printIndent();
    print("glTexImage3D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+")");
    downstreamGL.glTexImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    println("");
  }
  public  void glTexImage4DSGIS(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,java.nio.Buffer arg10)
  {
    printIndent();
    print("glTexImage4DSGIS("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+")");
    downstreamGL.glTexImage4DSGIS(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  public  void glTexParameterIivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glTexParameterIivEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexParameterIivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glTexParameterIivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glTexParameterIivEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glTexParameterIivEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexParameterIuivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glTexParameterIuivEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexParameterIuivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glTexParameterIuivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glTexParameterIuivEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glTexParameterIuivEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexParameterf(int arg0,int arg1,float arg2)
  {
    printIndent();
    print("glTexParameterf("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexParameterf(arg0,arg1,arg2);
    println("");
  }
  public  void glTexParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glTexParameterfv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexParameterfv(arg0,arg1,arg2);
    println("");
  }
  public  void glTexParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glTexParameterfv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glTexParameterfv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexParameteri(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glTexParameteri("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexParameteri(arg0,arg1,arg2);
    println("");
  }
  public  void glTexParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glTexParameteriv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTexParameteriv(arg0,arg1,arg2);
    println("");
  }
  public  void glTexParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glTexParameteriv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glTexParameteriv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTexSubImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,java.nio.Buffer arg6)
  {
    printIndent();
    print("glTexSubImage1D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glTexSubImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glTexSubImage1D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,long arg6)
  {
    printIndent();
    print("glTexSubImage1D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glTexSubImage1D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glTexSubImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,java.nio.Buffer arg8)
  {
    printIndent();
    print("glTexSubImage2D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+")");
    downstreamGL.glTexSubImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  public  void glTexSubImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,long arg8)
  {
    printIndent();
    print("glTexSubImage2D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+")");
    downstreamGL.glTexSubImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  public  void glTexSubImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,java.nio.Buffer arg10)
  {
    printIndent();
    print("glTexSubImage3D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+")");
    downstreamGL.glTexSubImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  public  void glTexSubImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,long arg10)
  {
    printIndent();
    print("glTexSubImage3D("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+")");
    downstreamGL.glTexSubImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  public  void glTexSubImage4DSGIS(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,int arg10,int arg11,java.nio.Buffer arg12)
  {
    printIndent();
    print("glTexSubImage4DSGIS("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+","+arg7+","+arg8+","+arg9+","+arg10+","+arg11+","+arg12+")");
    downstreamGL.glTexSubImage4DSGIS(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12);
    println("");
  }
  public  void glTextureColorMaskSGIS(boolean arg0,boolean arg1,boolean arg2,boolean arg3)
  {
    printIndent();
    print("glTextureColorMaskSGIS("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glTextureColorMaskSGIS(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTextureLightEXT(int arg0)
  {
    printIndent();
    print("glTextureLightEXT("+arg0+")");
    downstreamGL.glTextureLightEXT(arg0);
    println("");
  }
  public  void glTextureMaterialEXT(int arg0,int arg1)
  {
    printIndent();
    print("glTextureMaterialEXT("+arg0+","+arg1+")");
    downstreamGL.glTextureMaterialEXT(arg0,arg1);
    println("");
  }
  public  void glTextureNormalEXT(int arg0)
  {
    printIndent();
    print("glTextureNormalEXT("+arg0+")");
    downstreamGL.glTextureNormalEXT(arg0);
    println("");
  }
  public  void glTextureRangeAPPLE(int arg0,int arg1,java.nio.Buffer arg2)
  {
    printIndent();
    print("glTextureRangeAPPLE("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTextureRangeAPPLE(arg0,arg1,arg2);
    println("");
  }
  public  void glTrackMatrixNV(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glTrackMatrixNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glTrackMatrixNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTransformFeedbackAttribsNV(int arg0,java.nio.IntBuffer arg1,int arg2)
  {
    printIndent();
    print("glTransformFeedbackAttribsNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTransformFeedbackAttribsNV(arg0,arg1,arg2);
    println("");
  }
  public  void glTransformFeedbackAttribsNV(int arg0,int[] arg1,int arg2,int arg3)
  {
    printIndent();
    print("glTransformFeedbackAttribsNV("+arg0+","+dumpArray(arg1)+","+arg2+","+arg3+")");
    downstreamGL.glTransformFeedbackAttribsNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTransformFeedbackVaryingsNV(int arg0,int arg1,java.nio.IntBuffer arg2,int arg3)
  {
    printIndent();
    print("glTransformFeedbackVaryingsNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glTransformFeedbackVaryingsNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glTransformFeedbackVaryingsNV(int arg0,int arg1,int[] arg2,int arg3,int arg4)
  {
    printIndent();
    print("glTransformFeedbackVaryingsNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+","+arg4+")");
    downstreamGL.glTransformFeedbackVaryingsNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glTranslated(double arg0,double arg1,double arg2)
  {
    printIndent();
    print("glTranslated("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTranslated(arg0,arg1,arg2);
    println("");
  }
  public  void glTranslatef(float arg0,float arg1,float arg2)
  {
    printIndent();
    print("glTranslatef("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glTranslatef(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform1f(int arg0,float arg1)
  {
    printIndent();
    print("glUniform1f("+arg0+","+arg1+")");
    downstreamGL.glUniform1f(arg0,arg1);
    println("");
  }
  public  void glUniform1fARB(int arg0,float arg1)
  {
    printIndent();
    print("glUniform1fARB("+arg0+","+arg1+")");
    downstreamGL.glUniform1fARB(arg0,arg1);
    println("");
  }
  public  void glUniform1fv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glUniform1fv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform1fv(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform1fv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glUniform1fv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform1fv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform1fvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glUniform1fvARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform1fvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform1fvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glUniform1fvARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform1fvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform1i(int arg0,int arg1)
  {
    printIndent();
    print("glUniform1i("+arg0+","+arg1+")");
    downstreamGL.glUniform1i(arg0,arg1);
    println("");
  }
  public  void glUniform1iARB(int arg0,int arg1)
  {
    printIndent();
    print("glUniform1iARB("+arg0+","+arg1+")");
    downstreamGL.glUniform1iARB(arg0,arg1);
    println("");
  }
  public  void glUniform1iv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glUniform1iv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform1iv(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform1iv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glUniform1iv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform1iv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform1ivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glUniform1ivARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform1ivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform1ivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glUniform1ivARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform1ivARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform1uiEXT(int arg0,int arg1)
  {
    printIndent();
    print("glUniform1uiEXT("+arg0+","+arg1+")");
    downstreamGL.glUniform1uiEXT(arg0,arg1);
    println("");
  }
  public  void glUniform1uivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glUniform1uivEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform1uivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform1uivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glUniform1uivEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform1uivEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform2f(int arg0,float arg1,float arg2)
  {
    printIndent();
    print("glUniform2f("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform2f(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform2fARB(int arg0,float arg1,float arg2)
  {
    printIndent();
    print("glUniform2fARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform2fARB(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform2fv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glUniform2fv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform2fv(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform2fv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glUniform2fv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform2fv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform2fvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glUniform2fvARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform2fvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform2fvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glUniform2fvARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform2fvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform2i(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glUniform2i("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform2i(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform2iARB(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glUniform2iARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform2iARB(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform2iv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glUniform2iv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform2iv(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform2iv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glUniform2iv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform2iv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform2ivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glUniform2ivARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform2ivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform2ivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glUniform2ivARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform2ivARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform2uiEXT(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glUniform2uiEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform2uiEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform2uivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glUniform2uivEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform2uivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform2uivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glUniform2uivEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform2uivEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform3f(int arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glUniform3f("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glUniform3f(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform3fARB(int arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glUniform3fARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glUniform3fARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform3fv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glUniform3fv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform3fv(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform3fv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glUniform3fv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform3fv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform3fvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glUniform3fvARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform3fvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform3fvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glUniform3fvARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform3fvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform3i(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glUniform3i("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glUniform3i(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform3iARB(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glUniform3iARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glUniform3iARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform3iv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glUniform3iv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform3iv(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform3iv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glUniform3iv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform3iv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform3ivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glUniform3ivARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform3ivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform3ivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glUniform3ivARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform3ivARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform3uiEXT(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glUniform3uiEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glUniform3uiEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform3uivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glUniform3uivEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform3uivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform3uivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glUniform3uivEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform3uivEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform4f(int arg0,float arg1,float arg2,float arg3,float arg4)
  {
    printIndent();
    print("glUniform4f("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glUniform4f(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUniform4fARB(int arg0,float arg1,float arg2,float arg3,float arg4)
  {
    printIndent();
    print("glUniform4fARB("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glUniform4fARB(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUniform4fv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glUniform4fv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform4fv(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform4fv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glUniform4fv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform4fv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform4fvARB(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glUniform4fvARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform4fvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform4fvARB(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glUniform4fvARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform4fvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform4i(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glUniform4i("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glUniform4i(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUniform4iARB(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glUniform4iARB("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glUniform4iARB(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUniform4iv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glUniform4iv("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform4iv(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform4iv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glUniform4iv("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform4iv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform4ivARB(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glUniform4ivARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform4ivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform4ivARB(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glUniform4ivARB("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform4ivARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniform4uiEXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glUniform4uiEXT("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glUniform4uiEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUniform4uivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print("glUniform4uivEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniform4uivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glUniform4uivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print("glUniform4uivEXT("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glUniform4uivEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniformBufferEXT(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glUniformBufferEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glUniformBufferEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glUniformMatrix2fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glUniformMatrix2fv("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glUniformMatrix2fv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniformMatrix2fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glUniformMatrix2fv("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glUniformMatrix2fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUniformMatrix2fvARB(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glUniformMatrix2fvARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glUniformMatrix2fvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniformMatrix2fvARB(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glUniformMatrix2fvARB("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glUniformMatrix2fvARB(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUniformMatrix2x3fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glUniformMatrix2x3fv("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glUniformMatrix2x3fv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniformMatrix2x3fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glUniformMatrix2x3fv("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glUniformMatrix2x3fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUniformMatrix2x4fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glUniformMatrix2x4fv("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glUniformMatrix2x4fv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniformMatrix2x4fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glUniformMatrix2x4fv("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glUniformMatrix2x4fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUniformMatrix3fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glUniformMatrix3fv("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glUniformMatrix3fv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniformMatrix3fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glUniformMatrix3fv("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glUniformMatrix3fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUniformMatrix3fvARB(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glUniformMatrix3fvARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glUniformMatrix3fvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniformMatrix3fvARB(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glUniformMatrix3fvARB("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glUniformMatrix3fvARB(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUniformMatrix3x2fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glUniformMatrix3x2fv("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glUniformMatrix3x2fv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniformMatrix3x2fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glUniformMatrix3x2fv("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glUniformMatrix3x2fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUniformMatrix3x4fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glUniformMatrix3x4fv("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glUniformMatrix3x4fv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniformMatrix3x4fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glUniformMatrix3x4fv("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glUniformMatrix3x4fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUniformMatrix4fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glUniformMatrix4fv("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glUniformMatrix4fv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniformMatrix4fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glUniformMatrix4fv("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glUniformMatrix4fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUniformMatrix4fvARB(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glUniformMatrix4fvARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glUniformMatrix4fvARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniformMatrix4fvARB(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glUniformMatrix4fvARB("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glUniformMatrix4fvARB(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUniformMatrix4x2fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glUniformMatrix4x2fv("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glUniformMatrix4x2fv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniformMatrix4x2fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glUniformMatrix4x2fv("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glUniformMatrix4x2fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUniformMatrix4x3fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print("glUniformMatrix4x3fv("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glUniformMatrix4x3fv(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glUniformMatrix4x3fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print("glUniformMatrix4x3fv("+arg0+","+arg1+","+arg2+","+dumpArray(arg3)+","+arg4+")");
    downstreamGL.glUniformMatrix4x3fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUnlockArraysEXT()
  {
    printIndent();
    print("glUnlockArraysEXT("+")");
    downstreamGL.glUnlockArraysEXT();
    println("");
  }
  public  boolean glUnmapBuffer(int arg0)
  {
    printIndent();
    print("glUnmapBuffer("+arg0+")");
    boolean _res = downstreamGL.glUnmapBuffer(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean glUnmapBufferARB(int arg0)
  {
    printIndent();
    print("glUnmapBufferARB("+arg0+")");
    boolean _res = downstreamGL.glUnmapBufferARB(arg0);
    println(" = "+_res);
    return _res;
  }
  public  void glUpdateObjectBufferATI(int arg0,int arg1,int arg2,java.nio.Buffer arg3,int arg4)
  {
    printIndent();
    print("glUpdateObjectBufferATI("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glUpdateObjectBufferATI(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glUseProgram(int arg0)
  {
    printIndent();
    print("glUseProgram("+arg0+")");
    downstreamGL.glUseProgram(arg0);
    println("");
  }
  public  void glUseProgramObjectARB(int arg0)
  {
    printIndent();
    print("glUseProgramObjectARB("+arg0+")");
    downstreamGL.glUseProgramObjectARB(arg0);
    println("");
  }
  public  void glValidateProgram(int arg0)
  {
    printIndent();
    print("glValidateProgram("+arg0+")");
    downstreamGL.glValidateProgram(arg0);
    println("");
  }
  public  void glValidateProgramARB(int arg0)
  {
    printIndent();
    print("glValidateProgramARB("+arg0+")");
    downstreamGL.glValidateProgramARB(arg0);
    println("");
  }
  public  void glVariantArrayObjectATI(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glVariantArrayObjectATI("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVariantArrayObjectATI(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVariantPointerEXT(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print("glVariantPointerEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVariantPointerEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVariantPointerEXT(int arg0,int arg1,int arg2,long arg3)
  {
    printIndent();
    print("glVariantPointerEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVariantPointerEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVariantbvEXT(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glVariantbvEXT("+arg0+","+arg1+")");
    downstreamGL.glVariantbvEXT(arg0,arg1);
    println("");
  }
  public  void glVariantbvEXT(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glVariantbvEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVariantbvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVariantdvEXT(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glVariantdvEXT("+arg0+","+arg1+")");
    downstreamGL.glVariantdvEXT(arg0,arg1);
    println("");
  }
  public  void glVariantdvEXT(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glVariantdvEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVariantdvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVariantfvEXT(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glVariantfvEXT("+arg0+","+arg1+")");
    downstreamGL.glVariantfvEXT(arg0,arg1);
    println("");
  }
  public  void glVariantfvEXT(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glVariantfvEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVariantfvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVariantivEXT(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVariantivEXT("+arg0+","+arg1+")");
    downstreamGL.glVariantivEXT(arg0,arg1);
    println("");
  }
  public  void glVariantivEXT(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVariantivEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVariantivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVariantsvEXT(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVariantsvEXT("+arg0+","+arg1+")");
    downstreamGL.glVariantsvEXT(arg0,arg1);
    println("");
  }
  public  void glVariantsvEXT(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVariantsvEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVariantsvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVariantubvEXT(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glVariantubvEXT("+arg0+","+arg1+")");
    downstreamGL.glVariantubvEXT(arg0,arg1);
    println("");
  }
  public  void glVariantubvEXT(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glVariantubvEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVariantubvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVariantuivEXT(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVariantuivEXT("+arg0+","+arg1+")");
    downstreamGL.glVariantuivEXT(arg0,arg1);
    println("");
  }
  public  void glVariantuivEXT(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVariantuivEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVariantuivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVariantusvEXT(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVariantusvEXT("+arg0+","+arg1+")");
    downstreamGL.glVariantusvEXT(arg0,arg1);
    println("");
  }
  public  void glVariantusvEXT(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVariantusvEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVariantusvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVertex2d(double arg0,double arg1)
  {
    printIndent();
    print("glVertex2d("+arg0+","+arg1+")");
    downstreamGL.glVertex2d(arg0,arg1);
    println("");
  }
  public  void glVertex2dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glVertex2dv("+arg0+")");
    downstreamGL.glVertex2dv(arg0);
    println("");
  }
  public  void glVertex2dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glVertex2dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glVertex2dv(arg0,arg1);
    println("");
  }
  public  void glVertex2f(float arg0,float arg1)
  {
    printIndent();
    print("glVertex2f("+arg0+","+arg1+")");
    downstreamGL.glVertex2f(arg0,arg1);
    println("");
  }
  public  void glVertex2fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glVertex2fv("+arg0+")");
    downstreamGL.glVertex2fv(arg0);
    println("");
  }
  public  void glVertex2fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glVertex2fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glVertex2fv(arg0,arg1);
    println("");
  }
  public  void glVertex2hNV(short arg0,short arg1)
  {
    printIndent();
    print("glVertex2hNV("+arg0+","+arg1+")");
    downstreamGL.glVertex2hNV(arg0,arg1);
    println("");
  }
  public  void glVertex2hvNV(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glVertex2hvNV("+arg0+")");
    downstreamGL.glVertex2hvNV(arg0);
    println("");
  }
  public  void glVertex2hvNV(short[] arg0,int arg1)
  {
    printIndent();
    print("glVertex2hvNV("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glVertex2hvNV(arg0,arg1);
    println("");
  }
  public  void glVertex2i(int arg0,int arg1)
  {
    printIndent();
    print("glVertex2i("+arg0+","+arg1+")");
    downstreamGL.glVertex2i(arg0,arg1);
    println("");
  }
  public  void glVertex2iv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glVertex2iv("+arg0+")");
    downstreamGL.glVertex2iv(arg0);
    println("");
  }
  public  void glVertex2iv(int[] arg0,int arg1)
  {
    printIndent();
    print("glVertex2iv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glVertex2iv(arg0,arg1);
    println("");
  }
  public  void glVertex2s(short arg0,short arg1)
  {
    printIndent();
    print("glVertex2s("+arg0+","+arg1+")");
    downstreamGL.glVertex2s(arg0,arg1);
    println("");
  }
  public  void glVertex2sv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glVertex2sv("+arg0+")");
    downstreamGL.glVertex2sv(arg0);
    println("");
  }
  public  void glVertex2sv(short[] arg0,int arg1)
  {
    printIndent();
    print("glVertex2sv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glVertex2sv(arg0,arg1);
    println("");
  }
  public  void glVertex3d(double arg0,double arg1,double arg2)
  {
    printIndent();
    print("glVertex3d("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertex3d(arg0,arg1,arg2);
    println("");
  }
  public  void glVertex3dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glVertex3dv("+arg0+")");
    downstreamGL.glVertex3dv(arg0);
    println("");
  }
  public  void glVertex3dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glVertex3dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glVertex3dv(arg0,arg1);
    println("");
  }
  public  void glVertex3f(float arg0,float arg1,float arg2)
  {
    printIndent();
    print("glVertex3f("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertex3f(arg0,arg1,arg2);
    println("");
  }
  public  void glVertex3fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glVertex3fv("+arg0+")");
    downstreamGL.glVertex3fv(arg0);
    println("");
  }
  public  void glVertex3fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glVertex3fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glVertex3fv(arg0,arg1);
    println("");
  }
  public  void glVertex3hNV(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glVertex3hNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertex3hNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertex3hvNV(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glVertex3hvNV("+arg0+")");
    downstreamGL.glVertex3hvNV(arg0);
    println("");
  }
  public  void glVertex3hvNV(short[] arg0,int arg1)
  {
    printIndent();
    print("glVertex3hvNV("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glVertex3hvNV(arg0,arg1);
    println("");
  }
  public  void glVertex3i(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glVertex3i("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertex3i(arg0,arg1,arg2);
    println("");
  }
  public  void glVertex3iv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glVertex3iv("+arg0+")");
    downstreamGL.glVertex3iv(arg0);
    println("");
  }
  public  void glVertex3iv(int[] arg0,int arg1)
  {
    printIndent();
    print("glVertex3iv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glVertex3iv(arg0,arg1);
    println("");
  }
  public  void glVertex3s(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glVertex3s("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertex3s(arg0,arg1,arg2);
    println("");
  }
  public  void glVertex3sv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glVertex3sv("+arg0+")");
    downstreamGL.glVertex3sv(arg0);
    println("");
  }
  public  void glVertex3sv(short[] arg0,int arg1)
  {
    printIndent();
    print("glVertex3sv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glVertex3sv(arg0,arg1);
    println("");
  }
  public  void glVertex4d(double arg0,double arg1,double arg2,double arg3)
  {
    printIndent();
    print("glVertex4d("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertex4d(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertex4dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glVertex4dv("+arg0+")");
    downstreamGL.glVertex4dv(arg0);
    println("");
  }
  public  void glVertex4dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glVertex4dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glVertex4dv(arg0,arg1);
    println("");
  }
  public  void glVertex4f(float arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glVertex4f("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertex4f(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertex4fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glVertex4fv("+arg0+")");
    downstreamGL.glVertex4fv(arg0);
    println("");
  }
  public  void glVertex4fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glVertex4fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glVertex4fv(arg0,arg1);
    println("");
  }
  public  void glVertex4hNV(short arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glVertex4hNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertex4hNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertex4hvNV(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glVertex4hvNV("+arg0+")");
    downstreamGL.glVertex4hvNV(arg0);
    println("");
  }
  public  void glVertex4hvNV(short[] arg0,int arg1)
  {
    printIndent();
    print("glVertex4hvNV("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glVertex4hvNV(arg0,arg1);
    println("");
  }
  public  void glVertex4i(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glVertex4i("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertex4i(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertex4iv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glVertex4iv("+arg0+")");
    downstreamGL.glVertex4iv(arg0);
    println("");
  }
  public  void glVertex4iv(int[] arg0,int arg1)
  {
    printIndent();
    print("glVertex4iv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glVertex4iv(arg0,arg1);
    println("");
  }
  public  void glVertex4s(short arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glVertex4s("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertex4s(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertex4sv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glVertex4sv("+arg0+")");
    downstreamGL.glVertex4sv(arg0);
    println("");
  }
  public  void glVertex4sv(short[] arg0,int arg1)
  {
    printIndent();
    print("glVertex4sv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glVertex4sv(arg0,arg1);
    println("");
  }
  public  void glVertexArrayParameteriAPPLE(int arg0,int arg1)
  {
    printIndent();
    print("glVertexArrayParameteriAPPLE("+arg0+","+arg1+")");
    downstreamGL.glVertexArrayParameteriAPPLE(arg0,arg1);
    println("");
  }
  public  void glVertexArrayRangeAPPLE(int arg0,java.nio.Buffer arg1)
  {
    printIndent();
    print("glVertexArrayRangeAPPLE("+arg0+","+arg1+")");
    downstreamGL.glVertexArrayRangeAPPLE(arg0,arg1);
    println("");
  }
  public  void glVertexArrayRangeNV(int arg0,java.nio.Buffer arg1)
  {
    printIndent();
    print("glVertexArrayRangeNV("+arg0+","+arg1+")");
    downstreamGL.glVertexArrayRangeNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1d(int arg0,double arg1)
  {
    printIndent();
    print("glVertexAttrib1d("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1d(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1dARB(int arg0,double arg1)
  {
    printIndent();
    print("glVertexAttrib1dARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1dARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1dNV(int arg0,double arg1)
  {
    printIndent();
    print("glVertexAttrib1dNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1dNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1dv(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib1dv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1dv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1dv(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib1dv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib1dv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib1dvARB(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib1dvARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1dvARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1dvARB(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib1dvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib1dvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib1dvNV(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib1dvNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1dvNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1dvNV(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib1dvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib1dvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib1f(int arg0,float arg1)
  {
    printIndent();
    print("glVertexAttrib1f("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1f(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1fARB(int arg0,float arg1)
  {
    printIndent();
    print("glVertexAttrib1fARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1fARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1fNV(int arg0,float arg1)
  {
    printIndent();
    print("glVertexAttrib1fNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1fNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1fv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib1fv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1fv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1fv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib1fv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib1fv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib1fvARB(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib1fvARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1fvARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1fvARB(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib1fvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib1fvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib1fvNV(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib1fvNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1fvNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1fvNV(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib1fvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib1fvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib1hNV(int arg0,short arg1)
  {
    printIndent();
    print("glVertexAttrib1hNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1hNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1hvNV(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib1hvNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1hvNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1hvNV(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib1hvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib1hvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib1s(int arg0,short arg1)
  {
    printIndent();
    print("glVertexAttrib1s("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1s(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1sARB(int arg0,short arg1)
  {
    printIndent();
    print("glVertexAttrib1sARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1sARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1sNV(int arg0,short arg1)
  {
    printIndent();
    print("glVertexAttrib1sNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1sNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1sv(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib1sv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1sv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1sv(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib1sv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib1sv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib1svARB(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib1svARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1svARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1svARB(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib1svARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib1svARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib1svNV(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib1svNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib1svNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib1svNV(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib1svNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib1svNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2d(int arg0,double arg1,double arg2)
  {
    printIndent();
    print("glVertexAttrib2d("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttrib2d(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2dARB(int arg0,double arg1,double arg2)
  {
    printIndent();
    print("glVertexAttrib2dARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttrib2dARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2dNV(int arg0,double arg1,double arg2)
  {
    printIndent();
    print("glVertexAttrib2dNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttrib2dNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2dv(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib2dv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib2dv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib2dv(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib2dv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib2dv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2dvARB(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib2dvARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib2dvARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib2dvARB(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib2dvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib2dvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2dvNV(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib2dvNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib2dvNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib2dvNV(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib2dvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib2dvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2f(int arg0,float arg1,float arg2)
  {
    printIndent();
    print("glVertexAttrib2f("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttrib2f(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2fARB(int arg0,float arg1,float arg2)
  {
    printIndent();
    print("glVertexAttrib2fARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttrib2fARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2fNV(int arg0,float arg1,float arg2)
  {
    printIndent();
    print("glVertexAttrib2fNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttrib2fNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2fv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib2fv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib2fv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib2fv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib2fv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib2fv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2fvARB(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib2fvARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib2fvARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib2fvARB(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib2fvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib2fvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2fvNV(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib2fvNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib2fvNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib2fvNV(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib2fvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib2fvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2hNV(int arg0,short arg1,short arg2)
  {
    printIndent();
    print("glVertexAttrib2hNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttrib2hNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2hvNV(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib2hvNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib2hvNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib2hvNV(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib2hvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib2hvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2s(int arg0,short arg1,short arg2)
  {
    printIndent();
    print("glVertexAttrib2s("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttrib2s(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2sARB(int arg0,short arg1,short arg2)
  {
    printIndent();
    print("glVertexAttrib2sARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttrib2sARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2sNV(int arg0,short arg1,short arg2)
  {
    printIndent();
    print("glVertexAttrib2sNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttrib2sNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2sv(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib2sv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib2sv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib2sv(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib2sv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib2sv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2svARB(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib2svARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib2svARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib2svARB(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib2svARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib2svARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib2svNV(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib2svNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib2svNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib2svNV(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib2svNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib2svNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib3d(int arg0,double arg1,double arg2,double arg3)
  {
    printIndent();
    print("glVertexAttrib3d("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexAttrib3d(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttrib3dARB(int arg0,double arg1,double arg2,double arg3)
  {
    printIndent();
    print("glVertexAttrib3dARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexAttrib3dARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttrib3dNV(int arg0,double arg1,double arg2,double arg3)
  {
    printIndent();
    print("glVertexAttrib3dNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexAttrib3dNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttrib3dv(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib3dv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib3dv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib3dv(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib3dv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib3dv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib3dvARB(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib3dvARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib3dvARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib3dvARB(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib3dvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib3dvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib3dvNV(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib3dvNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib3dvNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib3dvNV(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib3dvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib3dvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib3f(int arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glVertexAttrib3f("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexAttrib3f(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttrib3fARB(int arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glVertexAttrib3fARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexAttrib3fARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttrib3fNV(int arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glVertexAttrib3fNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexAttrib3fNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttrib3fv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib3fv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib3fv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib3fv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib3fv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib3fv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib3fvARB(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib3fvARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib3fvARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib3fvARB(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib3fvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib3fvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib3fvNV(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib3fvNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib3fvNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib3fvNV(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib3fvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib3fvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib3hNV(int arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glVertexAttrib3hNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexAttrib3hNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttrib3hvNV(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib3hvNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib3hvNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib3hvNV(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib3hvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib3hvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib3s(int arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glVertexAttrib3s("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexAttrib3s(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttrib3sARB(int arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glVertexAttrib3sARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexAttrib3sARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttrib3sNV(int arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glVertexAttrib3sNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexAttrib3sNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttrib3sv(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib3sv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib3sv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib3sv(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib3sv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib3sv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib3svARB(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib3svARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib3svARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib3svARB(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib3svARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib3svARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib3svNV(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib3svNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib3svNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib3svNV(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib3svNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib3svNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4Nbv(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4Nbv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4Nbv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4Nbv(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4Nbv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4Nbv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4NbvARB(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4NbvARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4NbvARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4NbvARB(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4NbvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4NbvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4Niv(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4Niv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4Niv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4Niv(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4Niv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4Niv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4NivARB(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4NivARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4NivARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4NivARB(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4NivARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4NivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4Nsv(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4Nsv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4Nsv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4Nsv(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4Nsv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4Nsv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4NsvARB(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4NsvARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4NsvARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4NsvARB(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4NsvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4NsvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4Nub(int arg0,byte arg1,byte arg2,byte arg3,byte arg4)
  {
    printIndent();
    print("glVertexAttrib4Nub("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttrib4Nub(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttrib4NubARB(int arg0,byte arg1,byte arg2,byte arg3,byte arg4)
  {
    printIndent();
    print("glVertexAttrib4NubARB("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttrib4NubARB(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttrib4Nubv(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4Nubv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4Nubv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4Nubv(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4Nubv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4Nubv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4NubvARB(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4NubvARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4NubvARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4NubvARB(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4NubvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4NubvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4Nuiv(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4Nuiv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4Nuiv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4Nuiv(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4Nuiv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4Nuiv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4NuivARB(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4NuivARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4NuivARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4NuivARB(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4NuivARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4NuivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4Nusv(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4Nusv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4Nusv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4Nusv(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4Nusv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4Nusv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4NusvARB(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4NusvARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4NusvARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4NusvARB(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4NusvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4NusvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4bv(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4bv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4bv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4bv(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4bv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4bv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4bvARB(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4bvARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4bvARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4bvARB(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4bvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4bvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4d(int arg0,double arg1,double arg2,double arg3,double arg4)
  {
    printIndent();
    print("glVertexAttrib4d("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttrib4d(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttrib4dARB(int arg0,double arg1,double arg2,double arg3,double arg4)
  {
    printIndent();
    print("glVertexAttrib4dARB("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttrib4dARB(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttrib4dNV(int arg0,double arg1,double arg2,double arg3,double arg4)
  {
    printIndent();
    print("glVertexAttrib4dNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttrib4dNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttrib4dv(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4dv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4dv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4dv(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4dv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4dv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4dvARB(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4dvARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4dvARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4dvARB(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4dvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4dvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4dvNV(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4dvNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4dvNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4dvNV(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4dvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4dvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4f(int arg0,float arg1,float arg2,float arg3,float arg4)
  {
    printIndent();
    print("glVertexAttrib4f("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttrib4f(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttrib4fARB(int arg0,float arg1,float arg2,float arg3,float arg4)
  {
    printIndent();
    print("glVertexAttrib4fARB("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttrib4fARB(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttrib4fNV(int arg0,float arg1,float arg2,float arg3,float arg4)
  {
    printIndent();
    print("glVertexAttrib4fNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttrib4fNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttrib4fv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4fv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4fv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4fv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4fv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4fv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4fvARB(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4fvARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4fvARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4fvARB(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4fvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4fvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4fvNV(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4fvNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4fvNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4fvNV(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4fvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4fvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4hNV(int arg0,short arg1,short arg2,short arg3,short arg4)
  {
    printIndent();
    print("glVertexAttrib4hNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttrib4hNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttrib4hvNV(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4hvNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4hvNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4hvNV(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4hvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4hvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4iv(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4iv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4iv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4iv(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4iv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4iv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4ivARB(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4ivARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4ivARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4ivARB(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4ivARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4ivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4s(int arg0,short arg1,short arg2,short arg3,short arg4)
  {
    printIndent();
    print("glVertexAttrib4s("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttrib4s(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttrib4sARB(int arg0,short arg1,short arg2,short arg3,short arg4)
  {
    printIndent();
    print("glVertexAttrib4sARB("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttrib4sARB(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttrib4sNV(int arg0,short arg1,short arg2,short arg3,short arg4)
  {
    printIndent();
    print("glVertexAttrib4sNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttrib4sNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttrib4sv(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4sv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4sv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4sv(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4sv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4sv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4svARB(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4svARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4svARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4svARB(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4svARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4svARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4svNV(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4svNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4svNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4svNV(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4svNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4svNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4ubNV(int arg0,byte arg1,byte arg2,byte arg3,byte arg4)
  {
    printIndent();
    print("glVertexAttrib4ubNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttrib4ubNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttrib4ubv(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4ubv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4ubv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4ubv(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4ubv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4ubv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4ubvARB(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4ubvARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4ubvARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4ubvARB(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4ubvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4ubvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4ubvNV(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4ubvNV("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4ubvNV(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4ubvNV(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4ubvNV("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4ubvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4uiv(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4uiv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4uiv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4uiv(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4uiv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4uiv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4uivARB(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4uivARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4uivARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4uivARB(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4uivARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4uivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4usv(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4usv("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4usv(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4usv(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4usv("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4usv(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttrib4usvARB(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttrib4usvARB("+arg0+","+arg1+")");
    downstreamGL.glVertexAttrib4usvARB(arg0,arg1);
    println("");
  }
  public  void glVertexAttrib4usvARB(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttrib4usvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttrib4usvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribArrayObjectATI(int arg0,int arg1,int arg2,boolean arg3,int arg4,int arg5,int arg6)
  {
    printIndent();
    print("glVertexAttribArrayObjectATI("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+","+arg6+")");
    downstreamGL.glVertexAttribArrayObjectATI(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  public  void glVertexAttribI1iEXT(int arg0,int arg1)
  {
    printIndent();
    print("glVertexAttribI1iEXT("+arg0+","+arg1+")");
    downstreamGL.glVertexAttribI1iEXT(arg0,arg1);
    println("");
  }
  public  void glVertexAttribI1ivEXT(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexAttribI1ivEXT("+arg0+","+arg1+")");
    downstreamGL.glVertexAttribI1ivEXT(arg0,arg1);
    println("");
  }
  public  void glVertexAttribI1ivEXT(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttribI1ivEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttribI1ivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribI1uiEXT(int arg0,int arg1)
  {
    printIndent();
    print("glVertexAttribI1uiEXT("+arg0+","+arg1+")");
    downstreamGL.glVertexAttribI1uiEXT(arg0,arg1);
    println("");
  }
  public  void glVertexAttribI1uivEXT(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexAttribI1uivEXT("+arg0+","+arg1+")");
    downstreamGL.glVertexAttribI1uivEXT(arg0,arg1);
    println("");
  }
  public  void glVertexAttribI1uivEXT(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttribI1uivEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttribI1uivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribI2iEXT(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glVertexAttribI2iEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribI2iEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribI2ivEXT(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexAttribI2ivEXT("+arg0+","+arg1+")");
    downstreamGL.glVertexAttribI2ivEXT(arg0,arg1);
    println("");
  }
  public  void glVertexAttribI2ivEXT(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttribI2ivEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttribI2ivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribI2uiEXT(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glVertexAttribI2uiEXT("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribI2uiEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribI2uivEXT(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexAttribI2uivEXT("+arg0+","+arg1+")");
    downstreamGL.glVertexAttribI2uivEXT(arg0,arg1);
    println("");
  }
  public  void glVertexAttribI2uivEXT(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttribI2uivEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttribI2uivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribI3iEXT(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribI3iEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexAttribI3iEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribI3ivEXT(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexAttribI3ivEXT("+arg0+","+arg1+")");
    downstreamGL.glVertexAttribI3ivEXT(arg0,arg1);
    println("");
  }
  public  void glVertexAttribI3ivEXT(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttribI3ivEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttribI3ivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribI3uiEXT(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribI3uiEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexAttribI3uiEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribI3uivEXT(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexAttribI3uivEXT("+arg0+","+arg1+")");
    downstreamGL.glVertexAttribI3uivEXT(arg0,arg1);
    println("");
  }
  public  void glVertexAttribI3uivEXT(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttribI3uivEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttribI3uivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribI4bvEXT(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glVertexAttribI4bvEXT("+arg0+","+arg1+")");
    downstreamGL.glVertexAttribI4bvEXT(arg0,arg1);
    println("");
  }
  public  void glVertexAttribI4bvEXT(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttribI4bvEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttribI4bvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribI4iEXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glVertexAttribI4iEXT("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttribI4iEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttribI4ivEXT(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexAttribI4ivEXT("+arg0+","+arg1+")");
    downstreamGL.glVertexAttribI4ivEXT(arg0,arg1);
    println("");
  }
  public  void glVertexAttribI4ivEXT(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttribI4ivEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttribI4ivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribI4svEXT(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttribI4svEXT("+arg0+","+arg1+")");
    downstreamGL.glVertexAttribI4svEXT(arg0,arg1);
    println("");
  }
  public  void glVertexAttribI4svEXT(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttribI4svEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttribI4svEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribI4ubvEXT(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glVertexAttribI4ubvEXT("+arg0+","+arg1+")");
    downstreamGL.glVertexAttribI4ubvEXT(arg0,arg1);
    println("");
  }
  public  void glVertexAttribI4ubvEXT(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttribI4ubvEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttribI4ubvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribI4uiEXT(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glVertexAttribI4uiEXT("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttribI4uiEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttribI4uivEXT(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexAttribI4uivEXT("+arg0+","+arg1+")");
    downstreamGL.glVertexAttribI4uivEXT(arg0,arg1);
    println("");
  }
  public  void glVertexAttribI4uivEXT(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttribI4uivEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttribI4uivEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribI4usvEXT(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexAttribI4usvEXT("+arg0+","+arg1+")");
    downstreamGL.glVertexAttribI4usvEXT(arg0,arg1);
    println("");
  }
  public  void glVertexAttribI4usvEXT(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexAttribI4usvEXT("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexAttribI4usvEXT(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribIPointerEXT(int arg0,int arg1,int arg2,int arg3,java.nio.Buffer arg4)
  {
    printIndent();
    print("glVertexAttribIPointerEXT("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttribIPointerEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttribPointer(int arg0,int arg1,int arg2,boolean arg3,int arg4,java.nio.Buffer arg5)
  {
    printIndent();
    print("glVertexAttribPointer("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glVertexAttribPointer(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glVertexAttribPointer(int arg0,int arg1,int arg2,boolean arg3,int arg4,long arg5)
  {
    printIndent();
    print("glVertexAttribPointer("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glVertexAttribPointer(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glVertexAttribPointerARB(int arg0,int arg1,int arg2,boolean arg3,int arg4,java.nio.Buffer arg5)
  {
    printIndent();
    print("glVertexAttribPointerARB("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glVertexAttribPointerARB(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glVertexAttribPointerARB(int arg0,int arg1,int arg2,boolean arg3,int arg4,long arg5)
  {
    printIndent();
    print("glVertexAttribPointerARB("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glVertexAttribPointerARB(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  void glVertexAttribPointerNV(int arg0,int arg1,int arg2,int arg3,java.nio.Buffer arg4)
  {
    printIndent();
    print("glVertexAttribPointerNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttribPointerNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttribPointerNV(int arg0,int arg1,int arg2,int arg3,long arg4)
  {
    printIndent();
    print("glVertexAttribPointerNV("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexAttribPointerNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexAttribs1dvNV(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
    printIndent();
    print("glVertexAttribs1dvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribs1dvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribs1dvNV(int arg0,int arg1,double[] arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribs1dvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glVertexAttribs1dvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribs1fvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glVertexAttribs1fvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribs1fvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribs1fvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribs1fvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glVertexAttribs1fvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribs1hvNV(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
    printIndent();
    print("glVertexAttribs1hvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribs1hvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribs1hvNV(int arg0,int arg1,short[] arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribs1hvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glVertexAttribs1hvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribs1svNV(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
    printIndent();
    print("glVertexAttribs1svNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribs1svNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribs1svNV(int arg0,int arg1,short[] arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribs1svNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glVertexAttribs1svNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribs2dvNV(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
    printIndent();
    print("glVertexAttribs2dvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribs2dvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribs2dvNV(int arg0,int arg1,double[] arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribs2dvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glVertexAttribs2dvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribs2fvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glVertexAttribs2fvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribs2fvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribs2fvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribs2fvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glVertexAttribs2fvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribs2hvNV(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
    printIndent();
    print("glVertexAttribs2hvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribs2hvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribs2hvNV(int arg0,int arg1,short[] arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribs2hvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glVertexAttribs2hvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribs2svNV(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
    printIndent();
    print("glVertexAttribs2svNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribs2svNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribs2svNV(int arg0,int arg1,short[] arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribs2svNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glVertexAttribs2svNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribs3dvNV(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
    printIndent();
    print("glVertexAttribs3dvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribs3dvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribs3dvNV(int arg0,int arg1,double[] arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribs3dvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glVertexAttribs3dvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribs3fvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glVertexAttribs3fvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribs3fvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribs3fvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribs3fvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glVertexAttribs3fvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribs3hvNV(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
    printIndent();
    print("glVertexAttribs3hvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribs3hvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribs3hvNV(int arg0,int arg1,short[] arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribs3hvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glVertexAttribs3hvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribs3svNV(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
    printIndent();
    print("glVertexAttribs3svNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribs3svNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribs3svNV(int arg0,int arg1,short[] arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribs3svNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glVertexAttribs3svNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribs4dvNV(int arg0,int arg1,java.nio.DoubleBuffer arg2)
  {
    printIndent();
    print("glVertexAttribs4dvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribs4dvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribs4dvNV(int arg0,int arg1,double[] arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribs4dvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glVertexAttribs4dvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribs4fvNV(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print("glVertexAttribs4fvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribs4fvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribs4fvNV(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribs4fvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glVertexAttribs4fvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribs4hvNV(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
    printIndent();
    print("glVertexAttribs4hvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribs4hvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribs4hvNV(int arg0,int arg1,short[] arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribs4hvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glVertexAttribs4hvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribs4svNV(int arg0,int arg1,java.nio.ShortBuffer arg2)
  {
    printIndent();
    print("glVertexAttribs4svNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribs4svNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribs4svNV(int arg0,int arg1,short[] arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribs4svNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glVertexAttribs4svNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexAttribs4ubvNV(int arg0,int arg1,java.nio.ByteBuffer arg2)
  {
    printIndent();
    print("glVertexAttribs4ubvNV("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexAttribs4ubvNV(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexAttribs4ubvNV(int arg0,int arg1,byte[] arg2,int arg3)
  {
    printIndent();
    print("glVertexAttribs4ubvNV("+arg0+","+arg1+","+dumpArray(arg2)+","+arg3+")");
    downstreamGL.glVertexAttribs4ubvNV(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexBlendARB(int arg0)
  {
    printIndent();
    print("glVertexBlendARB("+arg0+")");
    downstreamGL.glVertexBlendARB(arg0);
    println("");
  }
  public  void glVertexBlendEnvfATI(int arg0,float arg1)
  {
    printIndent();
    print("glVertexBlendEnvfATI("+arg0+","+arg1+")");
    downstreamGL.glVertexBlendEnvfATI(arg0,arg1);
    println("");
  }
  public  void glVertexBlendEnviATI(int arg0,int arg1)
  {
    printIndent();
    print("glVertexBlendEnviATI("+arg0+","+arg1+")");
    downstreamGL.glVertexBlendEnviATI(arg0,arg1);
    println("");
  }
  public  void glVertexPointer(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print("glVertexPointer("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexPointer(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexPointer(int arg0,int arg1,int arg2,long arg3)
  {
    printIndent();
    print("glVertexPointer("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexPointer(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexStream1dATI(int arg0,double arg1)
  {
    printIndent();
    print("glVertexStream1dATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream1dATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream1dvATI(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glVertexStream1dvATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream1dvATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream1dvATI(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glVertexStream1dvATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexStream1dvATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream1fATI(int arg0,float arg1)
  {
    printIndent();
    print("glVertexStream1fATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream1fATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream1fvATI(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glVertexStream1fvATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream1fvATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream1fvATI(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glVertexStream1fvATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexStream1fvATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream1iATI(int arg0,int arg1)
  {
    printIndent();
    print("glVertexStream1iATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream1iATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream1ivATI(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexStream1ivATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream1ivATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream1ivATI(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexStream1ivATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexStream1ivATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream1sATI(int arg0,short arg1)
  {
    printIndent();
    print("glVertexStream1sATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream1sATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream1svATI(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexStream1svATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream1svATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream1svATI(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexStream1svATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexStream1svATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream2dATI(int arg0,double arg1,double arg2)
  {
    printIndent();
    print("glVertexStream2dATI("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexStream2dATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream2dvATI(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glVertexStream2dvATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream2dvATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream2dvATI(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glVertexStream2dvATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexStream2dvATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream2fATI(int arg0,float arg1,float arg2)
  {
    printIndent();
    print("glVertexStream2fATI("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexStream2fATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream2fvATI(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glVertexStream2fvATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream2fvATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream2fvATI(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glVertexStream2fvATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexStream2fvATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream2iATI(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glVertexStream2iATI("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexStream2iATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream2ivATI(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexStream2ivATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream2ivATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream2ivATI(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexStream2ivATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexStream2ivATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream2sATI(int arg0,short arg1,short arg2)
  {
    printIndent();
    print("glVertexStream2sATI("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glVertexStream2sATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream2svATI(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexStream2svATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream2svATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream2svATI(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexStream2svATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexStream2svATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream3dATI(int arg0,double arg1,double arg2,double arg3)
  {
    printIndent();
    print("glVertexStream3dATI("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexStream3dATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexStream3dvATI(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glVertexStream3dvATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream3dvATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream3dvATI(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glVertexStream3dvATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexStream3dvATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream3fATI(int arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glVertexStream3fATI("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexStream3fATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexStream3fvATI(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glVertexStream3fvATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream3fvATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream3fvATI(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glVertexStream3fvATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexStream3fvATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream3iATI(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glVertexStream3iATI("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexStream3iATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexStream3ivATI(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexStream3ivATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream3ivATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream3ivATI(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexStream3ivATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexStream3ivATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream3sATI(int arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glVertexStream3sATI("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexStream3sATI(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexStream3svATI(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexStream3svATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream3svATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream3svATI(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexStream3svATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexStream3svATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream4dATI(int arg0,double arg1,double arg2,double arg3,double arg4)
  {
    printIndent();
    print("glVertexStream4dATI("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexStream4dATI(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexStream4dvATI(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glVertexStream4dvATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream4dvATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream4dvATI(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glVertexStream4dvATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexStream4dvATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream4fATI(int arg0,float arg1,float arg2,float arg3,float arg4)
  {
    printIndent();
    print("glVertexStream4fATI("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexStream4fATI(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexStream4fvATI(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glVertexStream4fvATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream4fvATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream4fvATI(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glVertexStream4fvATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexStream4fvATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream4iATI(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print("glVertexStream4iATI("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexStream4iATI(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexStream4ivATI(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glVertexStream4ivATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream4ivATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream4ivATI(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glVertexStream4ivATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexStream4ivATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexStream4sATI(int arg0,short arg1,short arg2,short arg3,short arg4)
  {
    printIndent();
    print("glVertexStream4sATI("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+")");
    downstreamGL.glVertexStream4sATI(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  public  void glVertexStream4svATI(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glVertexStream4svATI("+arg0+","+arg1+")");
    downstreamGL.glVertexStream4svATI(arg0,arg1);
    println("");
  }
  public  void glVertexStream4svATI(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glVertexStream4svATI("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glVertexStream4svATI(arg0,arg1,arg2);
    println("");
  }
  public  void glVertexWeightPointerEXT(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print("glVertexWeightPointerEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexWeightPointerEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexWeightPointerEXT(int arg0,int arg1,int arg2,long arg3)
  {
    printIndent();
    print("glVertexWeightPointerEXT("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glVertexWeightPointerEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glVertexWeightfEXT(float arg0)
  {
    printIndent();
    print("glVertexWeightfEXT("+arg0+")");
    downstreamGL.glVertexWeightfEXT(arg0);
    println("");
  }
  public  void glVertexWeightfvEXT(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glVertexWeightfvEXT("+arg0+")");
    downstreamGL.glVertexWeightfvEXT(arg0);
    println("");
  }
  public  void glVertexWeightfvEXT(float[] arg0,int arg1)
  {
    printIndent();
    print("glVertexWeightfvEXT("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glVertexWeightfvEXT(arg0,arg1);
    println("");
  }
  public  void glVertexWeighthNV(short arg0)
  {
    printIndent();
    print("glVertexWeighthNV("+arg0+")");
    downstreamGL.glVertexWeighthNV(arg0);
    println("");
  }
  public  void glVertexWeighthvNV(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glVertexWeighthvNV("+arg0+")");
    downstreamGL.glVertexWeighthvNV(arg0);
    println("");
  }
  public  void glVertexWeighthvNV(short[] arg0,int arg1)
  {
    printIndent();
    print("glVertexWeighthvNV("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glVertexWeighthvNV(arg0,arg1);
    println("");
  }
  public  void glViewport(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glViewport("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glViewport(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glWeightPointerARB(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print("glWeightPointerARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glWeightPointerARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glWeightPointerARB(int arg0,int arg1,int arg2,long arg3)
  {
    printIndent();
    print("glWeightPointerARB("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glWeightPointerARB(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glWeightbvARB(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glWeightbvARB("+arg0+","+arg1+")");
    downstreamGL.glWeightbvARB(arg0,arg1);
    println("");
  }
  public  void glWeightbvARB(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glWeightbvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glWeightbvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glWeightdvARB(int arg0,java.nio.DoubleBuffer arg1)
  {
    printIndent();
    print("glWeightdvARB("+arg0+","+arg1+")");
    downstreamGL.glWeightdvARB(arg0,arg1);
    println("");
  }
  public  void glWeightdvARB(int arg0,double[] arg1,int arg2)
  {
    printIndent();
    print("glWeightdvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glWeightdvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glWeightfvARB(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print("glWeightfvARB("+arg0+","+arg1+")");
    downstreamGL.glWeightfvARB(arg0,arg1);
    println("");
  }
  public  void glWeightfvARB(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print("glWeightfvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glWeightfvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glWeightivARB(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glWeightivARB("+arg0+","+arg1+")");
    downstreamGL.glWeightivARB(arg0,arg1);
    println("");
  }
  public  void glWeightivARB(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glWeightivARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glWeightivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glWeightsvARB(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glWeightsvARB("+arg0+","+arg1+")");
    downstreamGL.glWeightsvARB(arg0,arg1);
    println("");
  }
  public  void glWeightsvARB(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glWeightsvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glWeightsvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glWeightubvARB(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print("glWeightubvARB("+arg0+","+arg1+")");
    downstreamGL.glWeightubvARB(arg0,arg1);
    println("");
  }
  public  void glWeightubvARB(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print("glWeightubvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glWeightubvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glWeightuivARB(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print("glWeightuivARB("+arg0+","+arg1+")");
    downstreamGL.glWeightuivARB(arg0,arg1);
    println("");
  }
  public  void glWeightuivARB(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print("glWeightuivARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glWeightuivARB(arg0,arg1,arg2);
    println("");
  }
  public  void glWeightusvARB(int arg0,java.nio.ShortBuffer arg1)
  {
    printIndent();
    print("glWeightusvARB("+arg0+","+arg1+")");
    downstreamGL.glWeightusvARB(arg0,arg1);
    println("");
  }
  public  void glWeightusvARB(int arg0,short[] arg1,int arg2)
  {
    printIndent();
    print("glWeightusvARB("+arg0+","+dumpArray(arg1)+","+arg2+")");
    downstreamGL.glWeightusvARB(arg0,arg1,arg2);
    println("");
  }
  public  void glWindowPos2d(double arg0,double arg1)
  {
    printIndent();
    print("glWindowPos2d("+arg0+","+arg1+")");
    downstreamGL.glWindowPos2d(arg0,arg1);
    println("");
  }
  public  void glWindowPos2dARB(double arg0,double arg1)
  {
    printIndent();
    print("glWindowPos2dARB("+arg0+","+arg1+")");
    downstreamGL.glWindowPos2dARB(arg0,arg1);
    println("");
  }
  public  void glWindowPos2dMESA(double arg0,double arg1)
  {
    printIndent();
    print("glWindowPos2dMESA("+arg0+","+arg1+")");
    downstreamGL.glWindowPos2dMESA(arg0,arg1);
    println("");
  }
  public  void glWindowPos2dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glWindowPos2dv("+arg0+")");
    downstreamGL.glWindowPos2dv(arg0);
    println("");
  }
  public  void glWindowPos2dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos2dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos2dv(arg0,arg1);
    println("");
  }
  public  void glWindowPos2dvARB(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glWindowPos2dvARB("+arg0+")");
    downstreamGL.glWindowPos2dvARB(arg0);
    println("");
  }
  public  void glWindowPos2dvARB(double[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos2dvARB("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos2dvARB(arg0,arg1);
    println("");
  }
  public  void glWindowPos2dvMESA(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glWindowPos2dvMESA("+arg0+")");
    downstreamGL.glWindowPos2dvMESA(arg0);
    println("");
  }
  public  void glWindowPos2dvMESA(double[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos2dvMESA("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos2dvMESA(arg0,arg1);
    println("");
  }
  public  void glWindowPos2f(float arg0,float arg1)
  {
    printIndent();
    print("glWindowPos2f("+arg0+","+arg1+")");
    downstreamGL.glWindowPos2f(arg0,arg1);
    println("");
  }
  public  void glWindowPos2fARB(float arg0,float arg1)
  {
    printIndent();
    print("glWindowPos2fARB("+arg0+","+arg1+")");
    downstreamGL.glWindowPos2fARB(arg0,arg1);
    println("");
  }
  public  void glWindowPos2fMESA(float arg0,float arg1)
  {
    printIndent();
    print("glWindowPos2fMESA("+arg0+","+arg1+")");
    downstreamGL.glWindowPos2fMESA(arg0,arg1);
    println("");
  }
  public  void glWindowPos2fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glWindowPos2fv("+arg0+")");
    downstreamGL.glWindowPos2fv(arg0);
    println("");
  }
  public  void glWindowPos2fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos2fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos2fv(arg0,arg1);
    println("");
  }
  public  void glWindowPos2fvARB(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glWindowPos2fvARB("+arg0+")");
    downstreamGL.glWindowPos2fvARB(arg0);
    println("");
  }
  public  void glWindowPos2fvARB(float[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos2fvARB("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos2fvARB(arg0,arg1);
    println("");
  }
  public  void glWindowPos2fvMESA(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glWindowPos2fvMESA("+arg0+")");
    downstreamGL.glWindowPos2fvMESA(arg0);
    println("");
  }
  public  void glWindowPos2fvMESA(float[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos2fvMESA("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos2fvMESA(arg0,arg1);
    println("");
  }
  public  void glWindowPos2i(int arg0,int arg1)
  {
    printIndent();
    print("glWindowPos2i("+arg0+","+arg1+")");
    downstreamGL.glWindowPos2i(arg0,arg1);
    println("");
  }
  public  void glWindowPos2iARB(int arg0,int arg1)
  {
    printIndent();
    print("glWindowPos2iARB("+arg0+","+arg1+")");
    downstreamGL.glWindowPos2iARB(arg0,arg1);
    println("");
  }
  public  void glWindowPos2iMESA(int arg0,int arg1)
  {
    printIndent();
    print("glWindowPos2iMESA("+arg0+","+arg1+")");
    downstreamGL.glWindowPos2iMESA(arg0,arg1);
    println("");
  }
  public  void glWindowPos2iv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glWindowPos2iv("+arg0+")");
    downstreamGL.glWindowPos2iv(arg0);
    println("");
  }
  public  void glWindowPos2iv(int[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos2iv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos2iv(arg0,arg1);
    println("");
  }
  public  void glWindowPos2ivARB(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glWindowPos2ivARB("+arg0+")");
    downstreamGL.glWindowPos2ivARB(arg0);
    println("");
  }
  public  void glWindowPos2ivARB(int[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos2ivARB("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos2ivARB(arg0,arg1);
    println("");
  }
  public  void glWindowPos2ivMESA(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glWindowPos2ivMESA("+arg0+")");
    downstreamGL.glWindowPos2ivMESA(arg0);
    println("");
  }
  public  void glWindowPos2ivMESA(int[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos2ivMESA("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos2ivMESA(arg0,arg1);
    println("");
  }
  public  void glWindowPos2s(short arg0,short arg1)
  {
    printIndent();
    print("glWindowPos2s("+arg0+","+arg1+")");
    downstreamGL.glWindowPos2s(arg0,arg1);
    println("");
  }
  public  void glWindowPos2sARB(short arg0,short arg1)
  {
    printIndent();
    print("glWindowPos2sARB("+arg0+","+arg1+")");
    downstreamGL.glWindowPos2sARB(arg0,arg1);
    println("");
  }
  public  void glWindowPos2sMESA(short arg0,short arg1)
  {
    printIndent();
    print("glWindowPos2sMESA("+arg0+","+arg1+")");
    downstreamGL.glWindowPos2sMESA(arg0,arg1);
    println("");
  }
  public  void glWindowPos2sv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glWindowPos2sv("+arg0+")");
    downstreamGL.glWindowPos2sv(arg0);
    println("");
  }
  public  void glWindowPos2sv(short[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos2sv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos2sv(arg0,arg1);
    println("");
  }
  public  void glWindowPos2svARB(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glWindowPos2svARB("+arg0+")");
    downstreamGL.glWindowPos2svARB(arg0);
    println("");
  }
  public  void glWindowPos2svARB(short[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos2svARB("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos2svARB(arg0,arg1);
    println("");
  }
  public  void glWindowPos2svMESA(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glWindowPos2svMESA("+arg0+")");
    downstreamGL.glWindowPos2svMESA(arg0);
    println("");
  }
  public  void glWindowPos2svMESA(short[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos2svMESA("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos2svMESA(arg0,arg1);
    println("");
  }
  public  void glWindowPos3d(double arg0,double arg1,double arg2)
  {
    printIndent();
    print("glWindowPos3d("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glWindowPos3d(arg0,arg1,arg2);
    println("");
  }
  public  void glWindowPos3dARB(double arg0,double arg1,double arg2)
  {
    printIndent();
    print("glWindowPos3dARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glWindowPos3dARB(arg0,arg1,arg2);
    println("");
  }
  public  void glWindowPos3dMESA(double arg0,double arg1,double arg2)
  {
    printIndent();
    print("glWindowPos3dMESA("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glWindowPos3dMESA(arg0,arg1,arg2);
    println("");
  }
  public  void glWindowPos3dv(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glWindowPos3dv("+arg0+")");
    downstreamGL.glWindowPos3dv(arg0);
    println("");
  }
  public  void glWindowPos3dv(double[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos3dv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos3dv(arg0,arg1);
    println("");
  }
  public  void glWindowPos3dvARB(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glWindowPos3dvARB("+arg0+")");
    downstreamGL.glWindowPos3dvARB(arg0);
    println("");
  }
  public  void glWindowPos3dvARB(double[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos3dvARB("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos3dvARB(arg0,arg1);
    println("");
  }
  public  void glWindowPos3dvMESA(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glWindowPos3dvMESA("+arg0+")");
    downstreamGL.glWindowPos3dvMESA(arg0);
    println("");
  }
  public  void glWindowPos3dvMESA(double[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos3dvMESA("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos3dvMESA(arg0,arg1);
    println("");
  }
  public  void glWindowPos3f(float arg0,float arg1,float arg2)
  {
    printIndent();
    print("glWindowPos3f("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glWindowPos3f(arg0,arg1,arg2);
    println("");
  }
  public  void glWindowPos3fARB(float arg0,float arg1,float arg2)
  {
    printIndent();
    print("glWindowPos3fARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glWindowPos3fARB(arg0,arg1,arg2);
    println("");
  }
  public  void glWindowPos3fMESA(float arg0,float arg1,float arg2)
  {
    printIndent();
    print("glWindowPos3fMESA("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glWindowPos3fMESA(arg0,arg1,arg2);
    println("");
  }
  public  void glWindowPos3fv(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glWindowPos3fv("+arg0+")");
    downstreamGL.glWindowPos3fv(arg0);
    println("");
  }
  public  void glWindowPos3fv(float[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos3fv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos3fv(arg0,arg1);
    println("");
  }
  public  void glWindowPos3fvARB(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glWindowPos3fvARB("+arg0+")");
    downstreamGL.glWindowPos3fvARB(arg0);
    println("");
  }
  public  void glWindowPos3fvARB(float[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos3fvARB("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos3fvARB(arg0,arg1);
    println("");
  }
  public  void glWindowPos3fvMESA(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glWindowPos3fvMESA("+arg0+")");
    downstreamGL.glWindowPos3fvMESA(arg0);
    println("");
  }
  public  void glWindowPos3fvMESA(float[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos3fvMESA("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos3fvMESA(arg0,arg1);
    println("");
  }
  public  void glWindowPos3i(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glWindowPos3i("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glWindowPos3i(arg0,arg1,arg2);
    println("");
  }
  public  void glWindowPos3iARB(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glWindowPos3iARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glWindowPos3iARB(arg0,arg1,arg2);
    println("");
  }
  public  void glWindowPos3iMESA(int arg0,int arg1,int arg2)
  {
    printIndent();
    print("glWindowPos3iMESA("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glWindowPos3iMESA(arg0,arg1,arg2);
    println("");
  }
  public  void glWindowPos3iv(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glWindowPos3iv("+arg0+")");
    downstreamGL.glWindowPos3iv(arg0);
    println("");
  }
  public  void glWindowPos3iv(int[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos3iv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos3iv(arg0,arg1);
    println("");
  }
  public  void glWindowPos3ivARB(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glWindowPos3ivARB("+arg0+")");
    downstreamGL.glWindowPos3ivARB(arg0);
    println("");
  }
  public  void glWindowPos3ivARB(int[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos3ivARB("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos3ivARB(arg0,arg1);
    println("");
  }
  public  void glWindowPos3ivMESA(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glWindowPos3ivMESA("+arg0+")");
    downstreamGL.glWindowPos3ivMESA(arg0);
    println("");
  }
  public  void glWindowPos3ivMESA(int[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos3ivMESA("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos3ivMESA(arg0,arg1);
    println("");
  }
  public  void glWindowPos3s(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glWindowPos3s("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glWindowPos3s(arg0,arg1,arg2);
    println("");
  }
  public  void glWindowPos3sARB(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glWindowPos3sARB("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glWindowPos3sARB(arg0,arg1,arg2);
    println("");
  }
  public  void glWindowPos3sMESA(short arg0,short arg1,short arg2)
  {
    printIndent();
    print("glWindowPos3sMESA("+arg0+","+arg1+","+arg2+")");
    downstreamGL.glWindowPos3sMESA(arg0,arg1,arg2);
    println("");
  }
  public  void glWindowPos3sv(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glWindowPos3sv("+arg0+")");
    downstreamGL.glWindowPos3sv(arg0);
    println("");
  }
  public  void glWindowPos3sv(short[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos3sv("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos3sv(arg0,arg1);
    println("");
  }
  public  void glWindowPos3svARB(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glWindowPos3svARB("+arg0+")");
    downstreamGL.glWindowPos3svARB(arg0);
    println("");
  }
  public  void glWindowPos3svARB(short[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos3svARB("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos3svARB(arg0,arg1);
    println("");
  }
  public  void glWindowPos3svMESA(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glWindowPos3svMESA("+arg0+")");
    downstreamGL.glWindowPos3svMESA(arg0);
    println("");
  }
  public  void glWindowPos3svMESA(short[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos3svMESA("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos3svMESA(arg0,arg1);
    println("");
  }
  public  void glWindowPos4dMESA(double arg0,double arg1,double arg2,double arg3)
  {
    printIndent();
    print("glWindowPos4dMESA("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glWindowPos4dMESA(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glWindowPos4dvMESA(java.nio.DoubleBuffer arg0)
  {
    printIndent();
    print("glWindowPos4dvMESA("+arg0+")");
    downstreamGL.glWindowPos4dvMESA(arg0);
    println("");
  }
  public  void glWindowPos4dvMESA(double[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos4dvMESA("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos4dvMESA(arg0,arg1);
    println("");
  }
  public  void glWindowPos4fMESA(float arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glWindowPos4fMESA("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glWindowPos4fMESA(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glWindowPos4fvMESA(java.nio.FloatBuffer arg0)
  {
    printIndent();
    print("glWindowPos4fvMESA("+arg0+")");
    downstreamGL.glWindowPos4fvMESA(arg0);
    println("");
  }
  public  void glWindowPos4fvMESA(float[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos4fvMESA("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos4fvMESA(arg0,arg1);
    println("");
  }
  public  void glWindowPos4iMESA(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print("glWindowPos4iMESA("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glWindowPos4iMESA(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glWindowPos4ivMESA(java.nio.IntBuffer arg0)
  {
    printIndent();
    print("glWindowPos4ivMESA("+arg0+")");
    downstreamGL.glWindowPos4ivMESA(arg0);
    println("");
  }
  public  void glWindowPos4ivMESA(int[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos4ivMESA("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos4ivMESA(arg0,arg1);
    println("");
  }
  public  void glWindowPos4sMESA(short arg0,short arg1,short arg2,short arg3)
  {
    printIndent();
    print("glWindowPos4sMESA("+arg0+","+arg1+","+arg2+","+arg3+")");
    downstreamGL.glWindowPos4sMESA(arg0,arg1,arg2,arg3);
    println("");
  }
  public  void glWindowPos4svMESA(java.nio.ShortBuffer arg0)
  {
    printIndent();
    print("glWindowPos4svMESA("+arg0+")");
    downstreamGL.glWindowPos4svMESA(arg0);
    println("");
  }
  public  void glWindowPos4svMESA(short[] arg0,int arg1)
  {
    printIndent();
    print("glWindowPos4svMESA("+dumpArray(arg0)+","+arg1+")");
    downstreamGL.glWindowPos4svMESA(arg0,arg1);
    println("");
  }
  public  void glWriteMaskEXT(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print("glWriteMaskEXT("+arg0+","+arg1+","+arg2+","+arg3+","+arg4+","+arg5+")");
    downstreamGL.glWriteMaskEXT(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  public  boolean isFunctionAvailable(java.lang.String arg0)
  {
    printIndent();
    print("isFunctionAvailable("+arg0+")");
    boolean _res = downstreamGL.isFunctionAvailable(arg0);
    println(" = "+_res);
    return _res;
  }
  public  boolean isExtensionAvailable(java.lang.String arg0)
  {
    printIndent();
    print("isExtensionAvailable("+arg0+")");
    boolean _res = downstreamGL.isExtensionAvailable(arg0);
    println(" = "+_res);
    return _res;
  }
  public  java.nio.ByteBuffer glAllocateMemoryNV(int arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print("glAllocateMemoryNV("+arg0+","+arg1+","+arg2+","+arg3+")");
    java.nio.ByteBuffer _res = downstreamGL.glAllocateMemoryNV(arg0,arg1,arg2,arg3);
    println(" = "+_res);
    return _res;
  }
  public  void setSwapInterval(int arg0)
  {
    printIndent();
    print("setSwapInterval("+arg0+")");
    downstreamGL.setSwapInterval(arg0);
    println("");
  }
  public  java.lang.Object getPlatformGLExtensions()
  {
    printIndent();
    print("getPlatformGLExtensions("+")");
    java.lang.Object _res = downstreamGL.getPlatformGLExtensions();
    println(" = "+_res);
    return _res;
  }
  public  java.lang.Object getExtension(java.lang.String arg0)
  {
    printIndent();
    print("getExtension("+arg0+")");
    java.lang.Object _res = downstreamGL.getExtension(arg0);
    println(" = "+_res);
    return _res;
  }
private PrintStream stream;
private int indent = 0;
protected String dumpArray(Object obj)
{
  if (obj == null) return "[null]";
  StringBuffer sb = new StringBuffer("[");
  int len  = java.lang.reflect.Array.getLength(obj);
  int count = Math.min(len,16);
  for ( int i =0; i < count; i++ ) {
    sb.append(java.lang.reflect.Array.get(obj,i));
    if (i < count-1)
      sb.append(',');
  }
  if ( len > 16 )
    sb.append("...").append(len);
  sb.append(']');
  return sb.toString();
}
protected void print(String str)
{
  stream.print(str);
}
protected void println(String str)
{
  stream.println(str);
}
protected void printIndent()
{
  for( int i =0; i < indent; i++) {stream.print(' ');}
}

  private GL downstreamGL;
} // end class TraceGL
