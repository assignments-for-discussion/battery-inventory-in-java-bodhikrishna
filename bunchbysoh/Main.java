package bunchbysoh;

public class Main {
  static class CountsBySoH {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  };

  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
    CountsBySoH counts = new CountsBySoH();
    //Looping through each of the battery's present capacity and further classifying based of SoH.
    for(int capacity: presentCapacities){
      double SOH=(100.0*capacity)/120.0; //assumption that rated capacity=120Ah
      if(SOH>83){
        counts.healthy++;
      }else if(SOH>=63){
        counts.exchange++;
      }
      else{
        counts.failed++;
      }
      
    }
    return counts;
  }

  static void testBucketingByHealth() {
    System.out.println("Counting batteries by SoH...\n");
    int[] presentCapacities = {113, 116, 80, 95, 92, 70};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);
    assert(counts.healthy == 2);
    assert(counts.exchange == 3);
    assert(counts.failed == 1);

    int[] testCase1 = {110,88,40};
    counts = countBatteriesByHealth(testCase1);
    assert(counts.healthy == 1); //110 is healthy
    assert(counts.exchange == 1); //88 is exchange
    assert(counts.failed == 1); //40 is failed
    System.out.println("Test Case 1 passed!");


    //testing for boundary conditions
    int[] boundaryCap={62,63,83,100};
    counts=countBatteriesByHealth(boundaryCap);
    //expected values for these edge cases are listed below
    assert(counts.healthy==1); //100 is healthy
    assert(counts.exchange==1); //83 are in exchange
    assert(counts.failed==2); //62 and 63 in failed
    System.out.println("Boundary testcases passed");
    System.out.println("Done counting :)\n");
  }

  public static void main(String[] args) {
    testBucketingByHealth();
  }
}
