export interface ServiceTemplate {
    /**
     * unique identifier
     * @type {string}
     * @memberof ServiceTemplate
     */
    id: string;
    /**
     * Name of the mission
     * @type {string}
     * @memberof ServiceTemplate
     */
    name: string;
    /**
     * When sub-classing, this defines the sub-class entity name.
     * @type {string}
     * @memberof ServiceTemplate
     */
    relatedType: string;
}
