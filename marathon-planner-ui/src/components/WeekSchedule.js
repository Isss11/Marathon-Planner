import DaySchedule from "./DaySchedule";
import { getDistanceString, roundDistance } from "./Utils";

const WeekSchedule = ({ isMetric, weekScheduleDetails }) => {

    return (
        <tr>
            <td>{weekScheduleDetails.week}</td>
            {weekScheduleDetails.daySchedules.map((daySchedule) => {
                return <DaySchedule day={daySchedule.day} distance={daySchedule.distance} isMetric={isMetric} runTitle={daySchedule.runTitle} />
            })}
            <td>{getDistanceString(weekScheduleDetails.distance, isMetric)}</td>
        </tr>
    )
}

export default WeekSchedule;