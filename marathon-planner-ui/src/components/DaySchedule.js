const DaySchedule = ({ date, weekDay, month, day, year, distance, isMetric }) => {
    const REST_DAY_TOLERANCE = 0.00001;

    const isRestDay = () => {
        return distance < REST_DAY_TOLERANCE;
    }

    const getDistanceString = () => {
        return isRestDay() ? "Rest Day." : "Run " + Number.parseFloat(distance).toFixed(1) + (isMetric ? " kilometers" : " miles") + ".";
    }

    return (
        <div>
            <h4>{weekDay}, {month} {day}, {year} | {date}</h4>
            <p>{getDistanceString()}</p>
        </div>
    )
}

export default DaySchedule;