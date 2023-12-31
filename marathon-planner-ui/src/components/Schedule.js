import DaySchedule from "./DaySchedule";

const Schedule = ({ trainingPlan, isMetric }) => {


    return (
        <div>
            <div>
                {trainingPlan.map((dailyPlan, i) => {
                    return <DaySchedule day={dailyPlan.day} distance={dailyPlan.distance} isMetric={isMetric} runTitle={dailyPlan.runTitle} />
                })}
            </div>
        </div>
    )
}

export default Schedule;