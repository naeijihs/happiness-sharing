/* eslint-disable prettier/prettier */
export function readFile(file: File) {
    return new Promise((resolve) => {
        let data: any;
        const reader = new FileReader();
        reader.readAsArrayBuffer(file);
        reader.onload = (event) => {
            data = event.target?.result;
        };
        reader.onloadend = () => {
            resolve({
                data
            });
        };
    });
}
