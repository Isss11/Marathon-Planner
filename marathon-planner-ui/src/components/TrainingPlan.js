import Schedule from "./Schedule";

const TrainingPlan = ({ trainingPlan, pdfOnClick, isMetric }) => {
    return trainingPlan.length > 0 ? (
        <div>
            <h2>Your Training Plan</h2>
            <button onClick={pdfOnClick}>Training Schedule PDF</button>
            <Schedule trainingPlan={trainingPlan} isMetric={isMetric}></Schedule>
        </div>
    ) : (
        <div>
            <h2>Your Training Plan</h2>
            <p>Click 'Generate Training Plan' above to create a customized training plan.</p>
        </div>
    )
}

export default TrainingPlan;