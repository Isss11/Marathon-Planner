import Chart from "chart.js/auto";
import { CategoryScale } from "chart.js";
import LineChart from "./LineChart.js";

Chart.register(CategoryScale);

const TrainingPlanSummary = ({ trainingPlan }) => {
    const getChartData = () => {
        return {
            labels: trainingPlan.map((data) => data.date),
            datasets: [
                {
                    data: trainingPlan.map((data) => data.distance),
                    borderColor: "purple",
                    borderWidth: 1
                }
            ]
        }
    }

    return (
        <div>
            <h3>Training Plan Summary</h3>
            <LineChart chartData={getChartData()} />
            <p><strong>Duration: </strong> {trainingPlan.length} days</p>
        </div>
    )
}

export default TrainingPlanSummary;