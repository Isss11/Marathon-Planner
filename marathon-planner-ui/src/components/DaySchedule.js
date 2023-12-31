const DaySchedule = ({ day, distance, isMetric, runTitle }) => {
    const REST_DAY_TOLERANCE = 0.00001;

    const isRestDay = () => {
        return distance < REST_DAY_TOLERANCE;
    }

    const getDistanceString = () => {
        return isRestDay() ? "Rest Day." : "Run " + Number.parseFloat(distance).toFixed(1) + (isMetric ? " kilometers" : " miles") + ".";
    }

    return (
        <div>
            <h5><strong>Day #{day}</strong> | {runTitle}</h5>
            <p>{getDistanceString()}</p>
        </div>
    )
}

export default DaySchedule;