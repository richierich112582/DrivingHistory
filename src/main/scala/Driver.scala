import scala.collection.mutable.ListBuffer

case class Driver(name: String){

  private val _listOfTrips: ListBuffer[Trip] = new ListBuffer[Trip]()

  def trips = _listOfTrips

  def totalMiles = this.calculateTotalMiles.round.toInt

  def averageTotalSpeed = this.calculateTotalAverageSpeed

  def addTrip(trip:Trip, minSpeed:Int, maxSpeed: Int): Unit = {

    val stopMinutes = (trip.stopTime.split(":")(0).toInt * 60) + trip.stopTime.split(":")(1).toInt
    val startMinutes = (trip.startTime.split(":")(0).toInt * 60) + trip.startTime.split(":")(1).toInt
    val totalMinutes = stopMinutes - startMinutes
    val avgSpeed = calculateAverageSpeed(totalMinutes,trip.miles)

    if(avgSpeed >= minSpeed && avgSpeed <= maxSpeed){
      _listOfTrips.addOne(trip)
    }
  }

  private def calculateTotalMiles = {
    var _totalMiles = 0.0

    _listOfTrips.foreach(trip => {
      _totalMiles += trip.miles
    })
    _totalMiles
  }

  private def calculateAverageSpeed(minutes:Int, miles:Double): Int = {
    (miles / minutes * 60).toInt
  }

  private def calculateTotalAverageSpeed = {
    var _totalMin = 0.0

    _listOfTrips.foreach(trip => {
      val hours = trip.stopTime.split(":")(0).toInt - trip.startTime.split(":")(0).toInt
      val minutes = trip.stopTime.split(":")(1).toInt - trip.startTime.split(":")(1).toInt

      _totalMin += (hours * 60) + minutes
    })

    (this.totalMiles.toInt / _totalMin * 60).round.toInt
  }
}
