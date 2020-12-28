SET ANSI_NULLS ON;
SET QUOTED_IDENTIFIER ON;
CREATE TABLE [dbo].[ClientsTourGroups]
(
    [ClientTourGroupId][int] IDENTITY (1,1) NOT NULL,
    [ClientId]         [int]                NOT NULL,
    [TourGroupId]      [int]                NOT NULL,
    CONSTRAINT [PK_ClientsTourGroups] PRIMARY KEY CLUSTERED
        (
         [ClientTourGroupId] ASC,
         [ClientId] ASC,
         [TourGroupId] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
ALTER TABLE [dbo].[ClientsTourGroups]
    WITH CHECK ADD CONSTRAINT [FK_ClientsTourGroups_Clients] FOREIGN KEY ([ClientId])
        REFERENCES [dbo].[Clients] ([ClientId]);
ALTER TABLE [dbo].[ClientsTourGroups]
    CHECK CONSTRAINT [FK_ClientsTourGroups_Clients];
ALTER TABLE [dbo].[ClientsTourGroups]
    WITH CHECK ADD CONSTRAINT [FK_ClientsTourGroups_TourGroups] FOREIGN KEY ([TourGroupId])
        REFERENCES [dbo].[TourGroups] ([TourGroupId])
        ON DELETE CASCADE
ALTER TABLE [dbo].[ClientsTourGroups]
    CHECK CONSTRAINT [FK_ClientsTourGroups_TourGroups];


