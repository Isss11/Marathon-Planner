import DaySchedule from "./DaySchedule";

const Schedule = ({ trainingPlan, isMetric }) => {
    return (
        <div>
            {trainingPlan.map((dailyPlan, i) => {
                return <DaySchedule date={dailyPlan.date} weekDay={dailyPlan.weekDay} month={dailyPlan.month} day={dailyPlan.day}
                    year={dailyPlan.year} distance={dailyPlan.distance} isMetric={isMetric} />
            })}
        </div>
    )
}

export default Schedule;