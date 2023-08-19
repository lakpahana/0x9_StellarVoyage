export interface UserInputModel{
    
    onChange:(value:string)=>void
}

export interface UserInputLableModel extends UserInputModel{
    lable:string,
}

export interface SwitchModel{
    lable:string,
    value?:boolean,
    onPressed?:()=>void

}