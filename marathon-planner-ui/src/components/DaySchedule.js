import { getDistanceString } from "./Utils";

const DaySchedule = ({ day, distance, isMetric, runTitle }) => {
    return (
        <td>{getDistanceString(distance, isMetric)}</td>
    )
}

export default DaySchedule;