import DaySchedule from "./DaySchedule";

const Schedule = ({ trainingPlan, isMetric }) => {
    return (
        <div>
            {trainingPlan.map((dailyPlan, i) => {
                return <DaySchedule date={dailyPlan.date} distance={dailyPlan.distance} isMetric={isMetric} />
            })}
        </div>
    )
}

export default Schedule;