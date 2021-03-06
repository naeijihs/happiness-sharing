/* eslint-disable prettier/prettier */
export function jsQuickSort(array: any): any {
    if (array.length <= 1) {
        return array;
    }
    const pivotIndex = Math.floor(array.length / 2);
    const pivot = array.splice(pivotIndex, 1)[0]; //从数组中取出我们的"基准"元素
    const left: any = [],
        right: any = [];
    array.forEach((item: any) => {
        if (item.agreeCount < pivot.agreeCount) {
            //left 存放比 pivot 小的元素
            left.push(item);
        } else {
            //right 存放大于或等于 pivot 的元素
            right.push(item);
        }
    });
    //至此，我们将数组分成了left和right两个部分
    return jsQuickSort(left).concat(pivot, jsQuickSort(right)); //分而治之
}
