package study.java8to11;

public class Foo {

    public static void main(String[] args) {
      LambdaExample lambdaExample = new LambdaExample();
      FunctionExample functionExample = new FunctionExample();
      LambdaExpExample lambdaExpExample = new LambdaExpExample();
      MethodRefExample methodRefExample = new MethodRefExample();

      //lambdaExample.testLambda();
      //functionExample.testFunction();
      //lambdaExpExample.testLambdaExp();
      methodRefExample.testMethodRef();
    }

}
