package study.java8to11.lambda;

public class LambdaMain {

    public void lambdaMain(){
        // 함수형 인퍼테이스와 람다
        LambdaExample lambdaExample = new LambdaExample();
        FunctionExample functionExample = new FunctionExample();
        LambdaExpExample lambdaExpExample = new LambdaExpExample();
        MethodRefExample methodRefExample = new MethodRefExample();

        // 인퍼테이스의 변화
        InfMethodExample infMethodExample = new InfMethodExample();
        APIExample apiExample = new APIExample();

        /*
        lambdaExample.testLamdba();
        functionExample.testFunction();
        lambdaExpExample.testLambdaExp();
        methodRefExample.testMethodRef();
        infMethodExample.testInfMethod();
        */
        apiExample.testAPI();
    }
}
