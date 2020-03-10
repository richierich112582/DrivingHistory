import org.scalatest.funspec.AnyFunSpec

class DrivingHistoryReportGeneratorSpec extends AnyFunSpec {

  val _minSpeed = 5
  val _maxSpeed = 100
  val _input: List[String] = List("Driver Dan","Driver Kumi","Driver Lauren","Trip Dan 07:15 07:45 17.3",
    "Trip Dan 06:12 06:32 21.8","Trip Lauren 12:01 13:16 42.0")

  describe("A DrivingHistoryReportGenerator") {
    describe("when created") {
      it("should have an empty list of drivers if input is empty") {

        val report = DrivingHistoryReportGenerator(List(), _minSpeed, _maxSpeed)

        assert(report.input.isEmpty)
        assert(report.drivers.isEmpty)
      }
      it("should have an input with a list of commands, a minSpeed and a maxSpeed") {

        val report = DrivingHistoryReportGenerator(_input, _minSpeed, _maxSpeed)

        assert(!report.input.isEmpty)
        assert(report.minSpeed == _minSpeed)
        assert(report.maxSpeed == _maxSpeed)
      }
      it("should add drivers when it comes across the Driver command") {

        val report = DrivingHistoryReportGenerator(_input, _minSpeed, _maxSpeed)

        assert(report.drivers.length == 3)
      }
      it("should add drivers when it comes across the Trip command and the Driver command wasn't present") {

        val input = List("Trip Dan 06:12 06:32 21.8","Driver Lauren")
        val report = DrivingHistoryReportGenerator(input, _minSpeed, _maxSpeed)

        assert(report.drivers.length == 2)
      }
      it("should not create duplicate drivers") {

        val input = List("Driver Dan","Driver Dan","Driver Lauren", "Trip Dan 06:12 06:32 21.8")
        val report = DrivingHistoryReportGenerator(input, _minSpeed, _maxSpeed)

        assert(report.drivers.length == 2)
      }
      it("should not be case sensitive and create duplicate drivers") {

        val input = List("Driver DAn","Driver DaN","Driver Lauren", "Trip Dan 06:12 06:32 21.8")
        val report = DrivingHistoryReportGenerator(input, _minSpeed, _maxSpeed)

        assert(report.drivers.length == 2)
      }
      it("should add trips to a driver when it comes across the Trip command") {

        val report = DrivingHistoryReportGenerator(_input, _minSpeed, _maxSpeed)
        val dan:Driver = report.drivers.find(d => d.name.toUpperCase() == "Dan".toUpperCase()).toList(0)
        val lauren:Driver = report.drivers.find(d => d.name.toUpperCase() == "Lauren".toUpperCase()).toList(0)
        val kumi:Driver = report.drivers.find(d => d.name.toUpperCase() == "Kumi".toUpperCase()).toList(0)

        assert(dan.trips.length == 2)
        assert(lauren.trips.length == 1)
        assert(kumi.trips.isEmpty)
      }
    }
    describe("when generateReport is invoked") {
      it("should print out each driver with totals miles and avg speed sorted by miles (descending)") {

        val report = DrivingHistoryReportGenerator(_input, _minSpeed, _maxSpeed)
        val generatedReport = report.generateReport

        assert(generatedReport.length == 3)
        assert(generatedReport(0) == "Lauren: 42 miles @ 34 mph")
        assert(generatedReport(1) == "Dan: 39 miles @ 47 mph")
        assert(generatedReport(2) == "Kumi: 0 miles")
      }
    }
  }
}
