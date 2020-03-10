import org.scalatest.funspec.AnyFunSpec

class DriverSpec extends AnyFunSpec {

  describe("A Driver") {
    describe("when created") {
      it("should have a name") {
        val name = "Richie"
        val driver = new Driver(name)
        assert(driver.name == name)
      }
    }
    describe("when adding a trip") {
      it("should add a trip when an average speed is >= minSpeed and <= maxSpeed") {
        val minSpeed = 5
        val maxSpeed = 100
        val driver = new Driver("Joe")

        driver.addTrip(new Trip("04:30", "05:00", 7.0), minSpeed, maxSpeed)
        assert(!driver.trips.isEmpty)
      }
      it("should store multiple trips if an average speed is  >= minSpeed and <= maxSpeed") {
        val minSpeed = 5
        val maxSpeed = 100
        val driver = new Driver("Joe")

        driver.addTrip(new Trip("04:30", "05:00", 7.0), minSpeed, maxSpeed)
        driver.addTrip(new Trip("04:10", "05:34", 13.2), minSpeed, maxSpeed)
        driver.addTrip(new Trip("02:50", "06:22", 30.6), minSpeed, maxSpeed)
        assert(driver.trips.length == 3)
      }
      it("should not add a trip when an average speed is < minSpeed") {
        val minSpeed = 5
        val maxSpeed = 100
        val driver = new Driver("Bob")

        driver.addTrip(new Trip("04:30", "05:01", 2.0), minSpeed, maxSpeed)
        assert(driver.trips.isEmpty)

        driver.addTrip(new Trip("04:30", "05:00", 7.0), minSpeed, maxSpeed)
        assert(driver.trips.length == 1)
      }
      it("should not add a trip when an average speed is > maxSpeed") {
        val minSpeed = 5
        val maxSpeed = 100
        val driver = new Driver("Richie")

        driver.addTrip(new Trip("04:34", "05:00", 60.0), minSpeed, maxSpeed)
        assert(driver.trips.isEmpty)

        driver.addTrip(new Trip("04:30", "05:00", 7.0), minSpeed, maxSpeed)
        driver.addTrip(new Trip("04:10", "05:34", 13.2), minSpeed, maxSpeed)
        assert(driver.trips.length == 2)
      }
    }
    describe("when totalMiles is invoked") {
      it("should return the total miles rounded to the nearest integer of all the trips in the trips collection") {
        val driver = new Driver("Bob")
        val driver2 = new Driver("John")
        val minSpeed = 5
        val maxSpeed = 100

        driver.addTrip(new Trip("04:30", "05:00", 7.0), minSpeed, maxSpeed)
        driver.addTrip(new Trip("04:10", "05:34", 13.2), minSpeed, maxSpeed)

        driver2.addTrip(new Trip("04:30", "05:00", 7.5), minSpeed, maxSpeed)
        driver2.addTrip(new Trip("04:10", "05:34", 20.2), minSpeed, maxSpeed)

        assert(driver.totalMiles == 20)
        assert(driver2.totalMiles == 28)
      }
    }
    describe("when averageTotalSpeed is invoked") {
      it("should return the average speed rounded to the nearest integer of all the trips in the trips collection") {
        val driver = new Driver("Bob")
        val driver2 = new Driver("John")
        val minSpeed = 5
        val maxSpeed = 100

        driver.addTrip(new Trip("04:30", "05:00", 7.0), minSpeed, maxSpeed)

        driver2.addTrip(new Trip("04:30", "05:00", 7.5), minSpeed, maxSpeed)
        driver2.addTrip(new Trip("04:10", "05:34", 24.7), minSpeed, maxSpeed)

        assert(driver.averageTotalSpeed == 14)
        assert(driver2.averageTotalSpeed == 17)
      }
    }
  }
}
