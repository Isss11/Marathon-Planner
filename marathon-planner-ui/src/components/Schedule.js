import DaySchedule from "./DaySchedule";

const Schedule = ({ trainingPlan, isMetric }) => {


    return (
        <div>
            <div>
                {trainingPlan.map((dailyPlan, i) => {
                    return <DaySchedule weekDay={dailyPlan.weekDay} month={dailyPlan.month} day={dailyPlan.day}
                        year={dailyPlan.year} distance={dailyPlan.distance} isMetric={isMetric} runTitle={dailyPlan.runTitle} />
                })}
            </div>
        </div>
    )
}

export default Schedule;