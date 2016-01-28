package ua.com.jon.validator.compiler;


import com.jon.tron.service.compiler.CompilationResult;
import com.jon.tron.service.compiler.InMemorySourceCompiler;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;


/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 9/3/13
 */

public class SourceCompilerTest {
    private InMemorySourceCompiler compiler;

    @Before
    public void setUp() {
        compiler = new InMemorySourceCompiler();
    }

    @Test
    public void testSimpleCompilation() throws Exception {
        String className = "Simple";
        String classCode = "public class Simple{}";
        CompilationResult compilationResult;
        compilationResult = compiler.compileSourceCode(className, classCode);
        assertTrue(compilationResult.isSuccess());
    }

    @Test
    public void testDiffClassName() throws Exception {
        String className = "Pimpimple";
        String classCode = "public class Simple{}";
        CompilationResult compilationResult;
        compilationResult = compiler.compileSourceCode(className, classCode);
        assertFalse(compilationResult.isSuccess());
    }

    @Test
    public void testEmptyClassName() throws Exception {
        String className = "";
        String classCode = "public class Simple{}";
        CompilationResult compilationResult;
        compilationResult = compiler.compileSourceCode(className, classCode);
        assertFalse(compilationResult.isSuccess());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyClassCode() throws Exception {
        String className = "Simple";
        String classCode = "";
        compiler.compileSourceCode(className, classCode);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullClassName() throws Exception {
        String className = null;
        String classCode = "public class Simple{}";
        compiler.compileSourceCode(className, classCode);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullClassCode() throws Exception {
        String className = "Pimpimple";
        String classCode = null;
        compiler.compileSourceCode(className, classCode);
    }
}

