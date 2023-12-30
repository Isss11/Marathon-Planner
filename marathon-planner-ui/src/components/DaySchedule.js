const DaySchedule = ({ date, distance, isMetric }) => {
    const REST_DAY_TOLERANCE = 0.00001;

    const isRestDay = () => {
        console.log(distance);
        return distance < REST_DAY_TOLERANCE;
    }

    const getDistanceString = () => {
        return isRestDay() ? "Rest Day." : Number.parseFloat(distance).toFixed(1) + (isMetric ? " kilometers." : " miles");
    }

    return (
        <div>
            <h3>{date}</h3>
            <p>{getDistanceString()}</p>
        </div>
    )
}

export default DaySchedule;