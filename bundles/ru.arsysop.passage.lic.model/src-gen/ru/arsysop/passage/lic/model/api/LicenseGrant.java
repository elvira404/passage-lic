/**
 * 	Copyright (c) 2018 ArSysOp
 * 
 * 	Licensed under the Apache License, Version 2.0 (the "License");
 * 	you may not use this file except in compliance with the License.
 * 	You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * 	Unless required by applicable law or agreed to in writing, software
 * 	distributed under the License is distributed on an "AS IS" BASIS,
 * 	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 	See the License for the specific language governing permissions and
 * 	limitations under the License.
 * 
 * 	SPDX-License-Identifier: Apache-2.0
 * 
 * 	Contributors:
 * 		ArSysOp - initial API and implementation
 * 
 */
package ru.arsysop.passage.lic.model.api;

import org.eclipse.emf.ecore.EObject;

import ru.arsysop.passage.lic.registry.LicenseGrantDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>LicensePack Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ru.arsysop.passage.lic.model.api.LicenseGrant#getFeatureIdentifier <em>Feature Identifier</em>}</li>
 *   <li>{@link ru.arsysop.passage.lic.model.api.LicenseGrant#getMatchVersion <em>Match Version</em>}</li>
 *   <li>{@link ru.arsysop.passage.lic.model.api.LicenseGrant#getMatchRule <em>Match Rule</em>}</li>
 *   <li>{@link ru.arsysop.passage.lic.model.api.LicenseGrant#getConditionType <em>Condition Type</em>}</li>
 *   <li>{@link ru.arsysop.passage.lic.model.api.LicenseGrant#getConditionExpression <em>Condition Expression</em>}</li>
 *   <li>{@link ru.arsysop.passage.lic.model.api.LicenseGrant#getCapacity <em>Capacity</em>}</li>
 *   <li>{@link ru.arsysop.passage.lic.model.api.LicenseGrant#getLicensePack <em>License Pack</em>}</li>
 * </ul>
 *
 * @see ru.arsysop.passage.lic.model.meta.LicPackage#getLicenseGrant()
 * @model superTypes="ru.arsysop.passage.lic.model.api.LicenseGrantDescriptor"
 * @generated
 */
public interface LicenseGrant extends EObject, LicenseGrantDescriptor {
	/**
	 * Returns the value of the '<em><b>Feature Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feature Identifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature Identifier</em>' attribute.
	 * @see #setFeatureIdentifier(String)
	 * @see ru.arsysop.passage.lic.model.meta.LicPackage#getLicenseGrant_FeatureIdentifier()
	 * @model required="true"
	 * @generated
	 */
	String getFeatureIdentifier();

	/**
	 * Sets the value of the '{@link ru.arsysop.passage.lic.model.api.LicenseGrant#getFeatureIdentifier <em>Feature Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature Identifier</em>' attribute.
	 * @see #getFeatureIdentifier()
	 * @generated
	 */
	void setFeatureIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Match Version</b></em>' attribute.
	 * The default value is <code>"0.0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Match Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Match Version</em>' attribute.
	 * @see #setMatchVersion(String)
	 * @see ru.arsysop.passage.lic.model.meta.LicPackage#getLicenseGrant_MatchVersion()
	 * @model default="0.0.0" required="true"
	 * @generated
	 */
	String getMatchVersion();

	/**
	 * Sets the value of the '{@link ru.arsysop.passage.lic.model.api.LicenseGrant#getMatchVersion <em>Match Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Match Version</em>' attribute.
	 * @see #getMatchVersion()
	 * @generated
	 */
	void setMatchVersion(String value);

	/**
	 * Returns the value of the '<em><b>Match Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Match Rule</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Match Rule</em>' attribute.
	 * @see #setMatchRule(String)
	 * @see ru.arsysop.passage.lic.model.meta.LicPackage#getLicenseGrant_MatchRule()
	 * @model
	 * @generated
	 */
	String getMatchRule();

	/**
	 * Sets the value of the '{@link ru.arsysop.passage.lic.model.api.LicenseGrant#getMatchRule <em>Match Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Match Rule</em>' attribute.
	 * @see #getMatchRule()
	 * @generated
	 */
	void setMatchRule(String value);

	/**
	 * Returns the value of the '<em><b>Condition Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition Type</em>' attribute.
	 * @see #setConditionType(String)
	 * @see ru.arsysop.passage.lic.model.meta.LicPackage#getLicenseGrant_ConditionType()
	 * @model required="true"
	 * @generated
	 */
	String getConditionType();

	/**
	 * Sets the value of the '{@link ru.arsysop.passage.lic.model.api.LicenseGrant#getConditionType <em>Condition Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition Type</em>' attribute.
	 * @see #getConditionType()
	 * @generated
	 */
	void setConditionType(String value);

	/**
	 * Returns the value of the '<em><b>Condition Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition Expression</em>' attribute.
	 * @see #setConditionExpression(String)
	 * @see ru.arsysop.passage.lic.model.meta.LicPackage#getLicenseGrant_ConditionExpression()
	 * @model required="true"
	 * @generated
	 */
	String getConditionExpression();

	/**
	 * Sets the value of the '{@link ru.arsysop.passage.lic.model.api.LicenseGrant#getConditionExpression <em>Condition Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition Expression</em>' attribute.
	 * @see #getConditionExpression()
	 * @generated
	 */
	void setConditionExpression(String value);

	/**
	 * Returns the value of the '<em><b>Capacity</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Capacity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Capacity</em>' attribute.
	 * @see #setCapacity(int)
	 * @see ru.arsysop.passage.lic.model.meta.LicPackage#getLicenseGrant_Capacity()
	 * @model default="1"
	 * @generated
	 */
	int getCapacity();

	/**
	 * Sets the value of the '{@link ru.arsysop.passage.lic.model.api.LicenseGrant#getCapacity <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Capacity</em>' attribute.
	 * @see #getCapacity()
	 * @generated
	 */
	void setCapacity(int value);

	/**
	 * Returns the value of the '<em><b>License Pack</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ru.arsysop.passage.lic.model.api.LicensePack#getLicenseGrants <em>License Grants</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>License Pack</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>License Pack</em>' container reference.
	 * @see #setLicensePack(LicensePack)
	 * @see ru.arsysop.passage.lic.model.meta.LicPackage#getLicenseGrant_LicensePack()
	 * @see ru.arsysop.passage.lic.model.api.LicensePack#getLicenseGrants
	 * @model opposite="licenseGrants" required="true" transient="false"
	 * @generated
	 */
	LicensePack getLicensePack();

	/**
	 * Sets the value of the '{@link ru.arsysop.passage.lic.model.api.LicenseGrant#getLicensePack <em>License Pack</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>License Pack</em>' container reference.
	 * @see #getLicensePack()
	 * @generated
	 */
	void setLicensePack(LicensePack value);

} // LicenseGrant