import java.math.BigInteger;





public class NegBinomDist {
    // Function to calculate binomial coefficient (n choose k)
    private static BigInteger choose(int n, int k) {
        BigInteger numerator = factorial(n);
        BigInteger denominator = factorial(k).multiply(factorial(n - k));
        return numerator.divide(denominator);
    }
    
    // Function to calculate factorial
    private static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
    
    // Function to calculate negative binomial probability
    private static double negativeBinomialProbability(double p, int r, int k) {
        BigInteger binomialCoefficient = choose(k - 1, r - 1);
        double probability = binomialCoefficient.doubleValue() * Math.pow(p, r) * Math.pow(1 - p, k - r);
        return probability;
    }
    
    // Function to calculate probability of winning the series
    public static double probabilityOfWinningSeries(double p, int r, int maxGames) {
        double probabilityOfWinningSeries = 0;
        
        // Calculate probability of winning series for each possible number of games
        for (int k = r; k <= maxGames; k++) {
            double probabilityOfWinningKGames = negativeBinomialProbability(p, r, k);
            probabilityOfWinningSeries += probabilityOfWinningKGames;
        }
        
        return probabilityOfWinningSeries;
    }
}