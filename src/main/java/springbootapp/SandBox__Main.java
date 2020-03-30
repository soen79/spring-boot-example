package springbootapp;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * Created by User on 1/16/2020.
 */
public class SandBox__Main {

    public static void main(String[] args) {

        // Create a lambda
        Consumer<String> convertToNumberLambda = (String val) -> {
            System.out.println("Executing convertToNumber("+val+")"  );
            Integer.parseInt(val);
            //Integer.parseInt("A");
            System.out.println("Persistence complete");
        };

        // Throwable VS Exception
        Function<Throwable, Number> exceptionHandlerLambda = (Throwable e) -> {
            System.out.println("Executing exceptionHandler()");
            System.out.println(e.getMessage());
            return Integer.parseInt("0");
        };

        /**
         * For each input in a list, get a corresponding value and persist the value as success otherwise record an error.
         */
        IntStream.range(1,5)
                .forEach(num -> {
                     SandBox__Main.retrieveCorrespondingString(num)
                        .thenAccept(convertToNumberLambda)
                        .exceptionally( e -> {reportFailure(num, e); return null;});
                        //.exceptionally(exceptionHandlerLambda);
                });
    }

    private static void reportFailure(int input, Throwable e) {
        System.out.println("Recording Exception case");
    }

    /**
     * If we experience an exception here, we'll need to absorb it to continue processing.
     * @param num
     * @return
     */
    public static CompletableFuture<String> retrieveCorrespondingString(int num) {
        // Supply the result of the completed execution
        return CompletableFuture.completedFuture(String.valueOf(num));
        //return CompletableFuture.supplyAsync((Integer x ) -> return String.valueOf(x));
    }
}