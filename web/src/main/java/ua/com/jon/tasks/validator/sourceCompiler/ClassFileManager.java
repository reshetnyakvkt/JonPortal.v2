package ua.com.jon.tasks.validator.sourceCompiler;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import java.io.IOException;
import java.security.SecureClassLoader;
import java.util.ArrayList;

class ClassFileManager extends
        ForwardingJavaFileManager {
    /**
     * Instances of JavaClassObjects that will store the
     * compiled bytecode of our classes
     */
    private final ArrayList<JavaClassObject> javaClassObjects = new ArrayList<JavaClassObject>();


    @SuppressWarnings("unchecked")
    public ClassFileManager(StandardJavaFileManager standardManager) {
        super(standardManager);
    }

    /**
     * Will be used by us to get the class loader for our
     * compiled class. It creates an anonymous class
     * extending the SecureClassLoader which uses the
     * byte code created by the compiler and stored in
     * the JavaClassObject, and returns the Class for it
     */
//    @Override
//    public ClassLoader getClassLoader(Location location) {
//        return new SecureClassLoader() {
//            @Override
//            protected Class<?> findClass(String name)
//                    throws ClassNotFoundException {
//                byte[] b = jclassObject.getBytes();
//                return super.defineClass(name, jclassObject
//                        .getBytes(), 0, b.length);
//            }
//        };
//    }

    public SecureClassLoader getClassByName(String name) throws ClassNotFoundException {

        for (JavaClassObject javaClassObject : javaClassObjects) {
            if (javaClassObject.getName().equals("/"+name+".class")) {
                final JavaClassObject finJavaClassObject=javaClassObject;
                return new SecureClassLoader() {
                    @Override
                    protected Class<?> findClass(String name)
                            throws ClassNotFoundException {
                        byte[] b = finJavaClassObject.getBytes();
                        return super.defineClass(name, finJavaClassObject
                                .getBytes(), 0, b.length);
                    }
                };
            }
        }
        throw new ClassNotFoundException("Can not find class with name "+name);
    }


    /**
     * Gives the compiler an instance of the JavaClassObject
     * so that the compiler can write the byte code into it.
     */
    @Override
    public JavaFileObject getJavaFileForOutput(Location location,
                                               String className, JavaFileObject.Kind kind, FileObject sibling)
            throws IOException {
        JavaClassObject javaClassObject = new JavaClassObject(className, kind);
        javaClassObjects.add(javaClassObject);
        return javaClassObject;
    }
}