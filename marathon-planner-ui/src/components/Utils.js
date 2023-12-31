const REST_DAY_TOLERANCE = 0.00001;

export const roundDistance = (distance) => {
    return Number.parseFloat(distance).toFixed(1);
}

const isRestDay = (distance) => {
    return distance < REST_DAY_TOLERANCE;
}

export const getDistanceString = (distance, isMetric) => {
    return isRestDay(distance) ? "Rest" : roundDistance(distance) + (isMetric ? " KMs" : " Miles");
}