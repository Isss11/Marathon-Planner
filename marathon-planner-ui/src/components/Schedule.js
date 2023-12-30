import DaySchedule from "./DaySchedule";

const Schedule = ({ trainingPlan, isMetric }) => {
    return (
        <div>
            {/* Convert to Calendar like below if time permits. */}
            {trainingPlan.map((dailyPlan, i) => {
                return <DaySchedule date={dailyPlan.date} weekDay={dailyPlan.weekDay} month={dailyPlan.month} day={dailyPlan.day}
                    year={dailyPlan.year} distance={dailyPlan.distance} isMetric={isMetric} />
            })}
            {/* <table>
                <thead>
                    <th>Sunday</th>
                    <th>Monday</th>
                    <th>Tuesday</th>
                    <th>Wednesday</th>
                    <th>Thursday</th>
                    <th>Friday</th>
                    <th>Saturday</th>
                </thead>
                <tbody>
                    <tr>
                        <td>5 km</td>
                    </tr>
                    <tr>
                        6km
                    </tr>
                </tbody>
            </table> */}
        </div>
    )
}

export default Schedule;