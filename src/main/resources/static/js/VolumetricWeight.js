/**
 * 
 */
 
 function calculateAllWeight(){
	var sum=0.00;
	var volumetricWeight=0.00, height=0.00, width=0.00, length=0.00;
	var table_rows=document.getElementById("tableForOrder").rows.length-2;
	for(var i=1; i<=table_rows; i++){		
		if(document.getElementById("weight-"+i).value.trim()===''){
			//alert('Kindly Fill Out All Weight/Dimensions Text-Fields');
			return;
		}
		if(document.getElementById("length-"+i).value.trim()===''){
			//alert('Kindly Fill Out All Weight/Dimensions Text-Fields');
			return;
		}
		if(document.getElementById("height-"+i).value.trim()===''){
			//alert('Kindly Fill Out All Weight/Dimensions Text-Fields');
			return;
		}
		if(document.getElementById("width-"+i).value.trim()===''){
			//alert('Kindly Fill Out All Weight/Dimensions Text-Fields');
			return;
		}
		if(document.getElementById("courier_weight_unit").value.trim()===''){
			alert('Kindly Choose Weight unit first');
			return;
		}
		
		height= parseFloat(document.getElementById("height-"+i).value);
		width= parseFloat(document.getElementById("width-"+i).value);
		length= parseFloat(document.getElementById("length-"+i).value);
		
		// new code
		var weightUnit=document.getElementById("courier_weight_unit").value;
		if(weightUnit==="gm-gram"){			
			volumetricWeight+= ((height*width*length)/5000)*1000; // in gram			
		}
		else if(weightUnit==="kg-kilogram"){			
			volumetricWeight+= ((height*width*length)/5000);	// in kg		
		}
		else if(weightUnit==="mg-milligram"){
			volumetricWeight+= ((height*width*length)/5000)*1000000;	// in mg	
		}
		else if(weightUnit==="lb-pound"){
			volumetricWeight+= ((height*width*length)/5000)*2.20462;	// in pounds	0.001
		}
		else if(weightUnit==="t-metric ton"){
			volumetricWeight+= ((height*width*length)/5000)*0.001;	// in metric tons	
		}
		
		document.getElementById("volumetricWeight-"+i).value=volumetricWeight.toFixed(2);
		sum += parseFloat(document.getElementById("weight-"+i).value);	
	}
	
	
	document.getElementById("LabelContent").innerHTML=sum.toFixed(2)+" (Weight) | "+volumetricWeight.toFixed(2)+" (Volumetric Weight)";
	document.getElementById("total_weight").value= sum>volumetricWeight? sum.toFixed(2) :volumetricWeight.toFixed(2);
	
}



<tbody id="tableBodyForOrder">
												<tr>
													<td>1</td>
													<td>
														<div>
															<input type="text" id="length-1" name="length"
																placeholder="" onChange="calculateAllWeight();"
																class="form-control validate[required,custom[float]]"
																onkeypress="return onlyNos(event,this);">
														</div>
													</td>
													<td>
														<div>
															<input type="text" id="width-1" name="width"
																placeholder="" onChange="calculateAllWeight();"
																class="form-control validate[required,custom[float]]"
																onkeypress="return onlyNos(event,this);">
														</div>
													</td>
													<td>
														<div>
															<input type="text" id="height-1" name="height"
																placeholder="" onChange="calculateAllWeight();"
																class="form-control validate[required,custom[float]]"
																onkeypress="return onlyNos(event,this);">
														</div>
													</td>
													<td>
														<div>
															<input type="text" id="volumetricWeight-1"
																name="volumetricWeight" class="form-control" readonly>
														</div>
													</td>
													<td>
														<div>
															<input type="text" id="weight-1" name="weight"
																placeholder="" onChange="calculateAllWeight();"
																class="form-control validate[required,custom[float]]"
																onkeypress="return onlyNos(event,this);">
														</div>
													</td>
													<td>
														<div>
															<select name="fragile" id="fragile-1"
																class="form-control validate[required]">
																<option value="Yes">Yes</option>
																<option value="No" selected>No</option>
															</select>
														</div>
													</td>
												</tr>
											</tbody>




