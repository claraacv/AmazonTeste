describe('Comentários inválidos', () => {

    beforeEach(() => {
        Cypress.on('uncaught:exception', (err, runnable) => {
            return false;
        });
    })

    it('comentário inválido - com estrelas', () => {
        cy.visit('https://www.amazon.com.br/');
        cy.get('#nav-link-accountList').click();
        cy.get('input[name="email"]').type('testesudesc@gmail.com');
        cy.get('#continue').click();
        cy.get('input[name="password"]').type('TestesUDESC3!', { log: false });
        cy.get('#signInSubmit').click();
        cy.get('#nav-link-accountList').should('contain.text', 'Olá');

        cy.visit('https://www.amazon.com.br/Malibu-renasce-Taylor-Jenkins-Reid/dp/8584392165');
        cy.contains('a.a-button-text', 'Escreva uma avaliação').click();

        cy.get('img[alt*="5"]').click();
        cy.get('#reviewTitle').type('Ótimo livro');
        cy.get('input.a-button-input[value="Enviar"]').click();
        cy.contains('Selecione uma classificação para continuar').should('exist');
    });

    it('comentário inválido - sem estrelas', () => {
        cy.visit('https://www.amazon.com.br/');
        cy.get('#nav-link-accountList').click();
        cy.get('input[name="email"]').type('testesudesc@gmail.com');
        cy.get('#continue').click();
        cy.get('input[name="password"]').type('TestesUDESC3!', { log: false });
        cy.get('#signInSubmit').click();
        cy.get('#nav-link-accountList').should('contain.text', 'Olá');

        cy.visit('https://www.amazon.com.br/Malibu-renasce-Taylor-Jenkins-Reid/dp/8584392165');
        cy.contains('a.a-button-text', 'Escreva uma avaliação').click();

        cy.get('#reviewTitle').type('Ótimo livro');
        cy.get('input.a-button-input[value="Enviar"]').click();
        cy.contains('Adicione um vídeo, uma foto ou uma avaliação escrita').should('exist');
    });

    it('comentário sem login', () =>{
        cy.visit('https://www.amazon.com.br/Malibu-renasce-Taylor-Jenkins-Reid/dp/8584392165');
        cy.contains('a.a-button-text', 'Escreva uma avaliação').click();

        cy.contains("Fazer login").should('exist');
    });

    it('comentário inválido - sem requisitos mínimos', () => {
        cy.visit('https://www.amazon.com.br/');
        cy.get('#nav-link-accountList').click();
        cy.get('input[name="email"]').type('testesudesc@gmail.com');
        cy.get('#continue').click();
        cy.get('input[name="password"]').type('TestesUDESC3!', { log: false });
        cy.get('#signInSubmit').click();
        cy.get('#nav-link-accountList').should('contain.text', 'Olá');

        cy.visit('https://www.amazon.com.br/Malibu-renasce-Taylor-Jenkins-Reid/dp/8584392165');
        cy.contains('a.a-button-text', 'Escreva uma avaliação').click();

        cy.contains('Desculpe, você ainda não atende aos requisitos mínimos de qualificação para escrever uma avaliação na Amazon.').should('exist');
    });

    // it('comentário sem estrelas', () => {
    //
    // });
});
