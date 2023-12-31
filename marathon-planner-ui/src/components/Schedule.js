import DaySchedule from "./DaySchedule";
import WeekSchedule from "./WeekSchedule";

const Schedule = ({ trainingPlan, isMetric }) => {


    return (
        <div>
            <table className="table table-hover">
                <thead>
                    <tr className="table-dark">
                        <td>Week</td>
                        <td>Sunday</td>
                        <td>Monday</td>
                        <td>Tuesday</td>
                        <td>Wednesday</td>
                        <td>Thursday</td>
                        <td>Friday</td>
                        <td>Saturday</td>
                        <td>Weekly Distance</td>
                    </tr>
                </thead>
                <tbody>
                    {trainingPlan.map((weeklyPlan, i) => {
                        return <WeekSchedule isMetric={isMetric} weekScheduleDetails={weeklyPlan} />
                    })}
                </tbody>
            </table>
        </div>
    )
}

export default Schedule;